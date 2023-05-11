packer {
  required_plugins {
    amazon = {
      version = ">= 0.0.2"
      source  = "github.com/hashicorp/amazon"
    }
  }
}

source "amazon-ebs" "amznubuntu" {
  ami_name      = "my-remote-aws-server-${var.version}"
  instance_type = "t2.micro"
  region        = "us-east-1"
  profile       = "micro-terraform"
  source_ami_filter {
    filters = {
      name                = "ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*"
      root-device-type    = "ebs"
      virtualization-type = "hvm"
    }
    most_recent = true
    owners      = [var.base_image_owner]
  }
  ssh_username = "ubuntu"
}

build {
  name = "my-remote-aws-server"
  sources = [
  "source.amazon-ebs.amznubuntu"]
  provisioner "shell" {
    script = "baking-image-script.sh"
  }
}

variable "version" {
  type    = string
  default = "004"
}
variable "base_image_owner" {
  type        = string
  default     = "099720109477"
  description = "ubuntu_canonical"
}