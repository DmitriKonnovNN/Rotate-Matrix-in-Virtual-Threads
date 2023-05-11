plan=create-plan.tfplan

plan:
	terraform init
	terraform fmt
	terraform validate
	terraform plan -out=$(plan)
create:
	terraform apply -auto-approve $(plan)
destroy:
	terraform destroy
