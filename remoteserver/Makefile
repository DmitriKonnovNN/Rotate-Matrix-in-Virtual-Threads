image-plan:
	packer init .
	packer fmt .
	packer inspect .
	packer validate .

image:
	packer build .

tf-plan:
	cd ./infra && make plan

tf-apply:
	cd ./infra && make create

tf-destroy:
	cd ./infra && make destroy