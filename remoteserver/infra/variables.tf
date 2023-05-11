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
  default = "ubuntu_user"
}
