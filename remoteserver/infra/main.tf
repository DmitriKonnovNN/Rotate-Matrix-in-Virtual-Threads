terraform {

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.64.0"
    }
  }
}

provider "aws" {
  profile = var.local_profile
  region  = var.aws_region
}

data "aws_ami" "backed_image" {
  // executable_users = ["self"]
  most_recent = true
  owners      = ["self"]
  filter {
    name   = "name"
    values = ["my-remote-aws-server-*"]
  }
}

resource "aws_instance" "remote_server" {
  instance_type = "t2.xlarge"
  ami           = data.aws_ami.backed_image.id
  key_name      = aws_key_pair.ec2-key-pair.key_name
  user_data     = templatefile("user-data.tftpl", { user_name = var.user_name })
  tags = {
    CreatedBy = var.owner
    Name      = var.app_name
  }

  vpc_security_group_ids = [aws_security_group.sg_server.id]
  subnet_id              = local.sn_web_c_id
}


resource "aws_security_group" "sg_server" {
  name        = var.app_name
  description = "sg-${var.app_name} in- and outbound traffic"
  vpc_id      = data.aws_vpcs.vpcs.ids[0]

  ingress {
    cidr_blocks = var.user_ip != null || var.user_ip != "" ? ["${local.admin_ip.ip}/32"] : ["${local.admin_ip.ip}/32", join("/", var.user_ip, "32")]
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    description = "ssh"
  }

  egress {
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
    from_port        = 0
    protocol         = "-1"
    to_port          = 0
    description      = "allow all egress traffic"
  }
  tags = {
    Name  = "sg-${var.app_name}"
    Owner = var.owner

  }
}
resource "aws_eip_association" "eip_assoc" {
  instance_id   = aws_instance.remote_server.id
  allocation_id = aws_eip.elastic_ip.id
}

resource "aws_eip" "elastic_ip" {
  vpc = true
}

data "aws_vpcs" "vpcs" {
  tags = {
    Name = var.vpc_name
  }
}
data "aws_subnets" "sn-web-c" {

  filter {
    name   = "vpc-id"
    values = [data.aws_vpcs.vpcs.ids[0]]
  }
  tags = {
    Name = "sn-web-C"
  }
}

locals {

  sn_web_c_id = tostring(data.aws_subnets.sn-web-c.ids[0])
}

data "aws_availability_zones" "available" {
  state = "available"
}


resource "aws_key_pair" "ec2-key-pair" {
  key_name   = "ec2-key-pair"
  public_key = tls_private_key.rsa-4096-ec2.public_key_openssh
}

resource "tls_private_key" "rsa-4096-ec2" {
  algorithm = "RSA"
  rsa_bits  = 4096
}

resource "local_file" "ec2-rsa-keys" {
  content         = tls_private_key.rsa-4096-ec2.private_key_pem
  file_permission = 400
  filename        = "${var.app_name}-key.pem"
}



data "http" "my_public_ip" {
  url = "https://ifconfig.co/json"
  request_headers = {
    Accept = "application/json"
  }
}
locals {
  admin_ip = jsondecode(data.http.my_public_ip.response_body)
}
output "server_public-ip" {
  value = aws_instance.remote_server.public_ip
}

output "ec2-ami-info" {
  value = ({
    ami_id = aws_instance.remote_server.ami
  volume_size = aws_instance.remote_server.root_block_device[0].volume_size })

}
output "admin_ip" {
  value       = local.admin_ip.ip
  description = "admin's ip"
}

output "user_ip" {
  value = var.user_ip
}
#output "aws_default_vpc_info" {
#  value       = data.aws_vpc.aws_default_vpc
#  description = "default vpc of my aws account"
#}
#
#output "aws_default_vpc_subnet_ids" {
#  description = "aws_default_vpc_subnet_ids"
#  value       = data.aws_subnets.default_subnets
#}

output "elastic_ip" {
  value = aws_eip.elastic_ip.public_dns
}


output "ssh_connection" {
  value = "ssh -i '${var.app_name}-key.pem' ${var.server_admin_name}@${aws_eip.elastic_ip.public_dns}"
}