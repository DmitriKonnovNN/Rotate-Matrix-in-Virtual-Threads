#!/bin/bash -e

echo "============= baking an image for remote server ==============="

PYTHON_VERSION='3.12'
MAVEN_VERSION='3.9.1'
GRAALVM_VERSION='22.3.1'
JAVA_VERSION='19'
MAVEN_URL="https://dlcdn.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz"
GRAALVM_URL="https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-${GRAALVM_VERSION}/graalvm-ce-java${JAVA_VERSION}-linux-amd64-${GRAALVM_VERSION}.tar.gz"
CLOUD_WATCH_AGENT_URL='https://s3.amazonaws.com/amazoncloudwatch-agent/ubuntu/amd64/latest/amazon-cloudwatch-agent.deb'
KUBECTL_VERSION='v1.25.9'
AWS_CLI_VERSION='2.0.30'


echo "====== System Update and Upgrade ======="

#sudo apt-get clean
sudo apt-get update -y
sudo apt-get upgrade -y
sudo apt install -y software-properties-common

echo "======= installing the latest available Python version =="

sudo apt-get -y install python3
#sudo apt-get -y install python
sudo apt-get -y install python3-pip
sudo pip install --upgrade pip
sudo add-apt-repository -y ppa:deadsnakes/ppa
sudo apt-get update && sudo apt-cache search python3.1
sudo apt-get install python${PYTHON_VERSION} -y
sudo ln -s /usr/bin/python${PYTHON_VERSION} /usr/bin/python
python --version

echo "============= install CloudWatch Agent ================="

sudo curl -fsSL ${CLOUD_WATCH_AGENT_URL} -o /tmp/amazon-cloudwatch-agent.deb && \
  dpkg -i -E /tmp/amazon-cloudwatch-agent.deb && \
  rm -f /tmp/amazon-cloudwatch-agent.deb

echo "============= install git ================="

sudo apt-get install -y git

echo "============== install maven ${MAVEN_VERSION} =========================="

sudo curl -fsSL ${MAVEN_URL} -o /tmp/apache-maven.tar.gz && \
  tar -xvf /tmp/apache-maven.tar.gz -C /tmp && \
  sudo mv /tmp/apache-maven-${MAVEN_VERSION} /opt/apache-maven-${MAVEN_VERSION} && \
  sudo rm -f /tmp/apache-maven.tar.gz
export M2_HOME="/opt/apache-maven-${MAVEN_VERSION}" && \
echo "export M2_HOME="/opt/apache-maven-${MAVEN_VERSION}"" | tee -a ~/.bashrc >> ~/.profile

echo "== install graalvm community edition ${GRAALVM_VERSION} and native image =="

sudo curl -fsSL ${GRAALVM_URL} -o /tmp/graalvm.tar.gz && \
  tar -xvf /tmp/graalvm.tar.gz -C /tmp && \
  sudo mkdir /usr/lib/jvm && \
  sudo mv /tmp/graalvm-ce-java${JAVA_VERSION}-${GRAALVM_VERSION} /usr/lib/jvm/graalvm-ce-java${JAVA_VERSION}-${GRAALVM_VERSION} && \
  sudo rm -f /tmp/graalvm.tar.gz

export JAVA_HOME="/usr/lib/jvm/graalvm-ce-java${JAVA_VERSION}-${GRAALVM_VERSION}"
sudo echo "export JAVA_HOME="/usr/lib/jvm/graalvm-ce-java${JAVA_VERSION}-${GRAALVM_VERSION}"" | tee -a ~/.bashrc >> ~/.profile
export PATH="${M2_HOME}/bin:${JAVA_HOME}/bin:${PATH}"
sudo echo "export PATH="${M2_HOME}/bin:${JAVA_HOME}/bin:"${PATH}""" | tee -a ~/.bashrc >> ~/.profile
export PATH=${PATH}:${JAVA_HOME}/lib/installer/bin
sudo echo "export PATH=""${PATH}":${JAVA_HOME}/lib/installer/bin"" | tee -a ~/.bashrc >> ~/.profile
export PATH=${PATH}:${JAVA_HOME}/lib/svm/bin
sudo echo "export PATH=""${PATH}":${JAVA_HOME}/lib/svm/bin"" | tee -a ~/.bashrc >> ~/.profile
sudo update-alternatives --install /usr/bin/java java ${JAVA_HOME}/bin/java 1
sudo update-alternatives --install /usr/bin/javac javac ${JAVA_HOME}/bin/javac 1
java -version
javac -version
gu install native-image

echo "================= install docker ========================"

sudo apt-get install -y docker.io

echo "============== install terraform =========================="
sudo apt-get install -y gnupg software-properties-common curl
sudo curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo apt-key add -
sudo apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com $(lsb_release -cs) main"
sudo apt-get update
sudo apt-get install -y terraform

echo "============== install packer ============================="
sudo apt-get install -y packer
echo "============== install vagrant ============================"
sudo apt-get install -y vagrant

echo "================= install vault ==========================="
sudo apt-get install -y vault
echo "================== install unzip =========================="
sudo apt-get install unzip
echo "================== install aws cli ========================"
sudo curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64-${AWS_CLI_VERSION}.zip" -o "awscliv2.zip"
sudo unzip awscliv2.zip
sudo ./aws/install
echo "=================== install kubectl ======================="
sudo curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo curl -LO https://dl.k8s.io/release/$KUBECTL_VERSION/bin/linux/amd64/kubectl
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl

