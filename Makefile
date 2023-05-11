image-plan:
	cd ./remoteserver && make image-plan

image:
	cd ./remoteserver && make image

tf-plan:
	cd ./remoteserver && make tf-plan

tf-apply:
	cd ./remoteserver && make tf-apply

tf-destroy:
	cd ./remoteserver && make tf-destroy