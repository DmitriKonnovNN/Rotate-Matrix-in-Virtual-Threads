#!/bin/bash -xe

python --version
python3 --version
# mvn --version
java --version
javac --version
docker --version
git --version
terraform --version
vault --version
vagrant --version
packer --version
aws --version
echo "==================== eksctl ================================"
sudo curl -fsSL "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" -o "eksctl.tar.gz" && \
    sudo curl -sL "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_checksums.txt" | grep $(uname -s)_amd64 | sha256sum --check && \
    sudo tar -xzf "eksctl.tar.gz" -C /tmp && \ 
    sudo rm "eksctl.tar.gz" && \ 
    sudo mv /tmp/eksctl /usr/local/bin
eksctl version
kubectl version --output=json



# aws configure list
# aws --profile EKS configure
# aws --profile EKS list
# aws configure list
# export AWS_PROFILE=EKS
# aws configure list
# kubectl get nodes
# kubectl cluster-info
# cat cluster/eks-test-cluster to /home/ubuntu/.kube/config

# aws eks update-kubeconfig --name eks-test-cluster
