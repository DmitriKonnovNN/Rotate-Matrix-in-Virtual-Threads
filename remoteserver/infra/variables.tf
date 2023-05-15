variable "owner" {
  type = string
}

variable "app_name" {
  description = "Enter name of the server"
  type        = string
  default     = "aws-remote-server"
}
variable "aws_region" {
  type        = string
  description = "AWS region"

}
variable "local_profile" {
  description = "local aws cli profile"
  type        = string
}

variable "app_main_port" {
  description = "port our server's listening on"
  type        = number
  default     = 22
}

variable "user_ip" {
  description = "enter user ip for SSH connection"
  type        = string
}

variable "user_name" {
  type    = string
  default = "ubuntu"
}

variable "vpc_name" {
  type = string
}

variable "server_admin_name" {
  type    = string
  default = "ubuntu"
}

variable "ubuntu_version" {
  type    = string
  default = "focal-20.04"
}

variable "filesystem" {
  type    = string
  default = "xfs"
}
variable "ubuntu_canonical" {
  type    = string
  default = "099720109477"
}
variable "vpc_az_id" {
  type    = string
  default = "use1-az1"
}

variable "az" {
  type    = string
  default = "a"
}