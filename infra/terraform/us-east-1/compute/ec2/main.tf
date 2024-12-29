
# Criar chave SSH
resource "tls_private_key" "eks_bastion_key" {
  algorithm = "RSA"
  rsa_bits  = 2048
}

resource "local_file" "eks_bastion_pem" {
  content  = tls_private_key.eks_bastion_key.private_key_pem
  filename = "${path.module}/eks-bastion.pem"
}

resource "aws_key_pair" "eks_bastion" {
  key_name   = "eks-bastion"
  public_key = tls_private_key.eks_bastion_key.public_key_openssh
}

resource "aws_security_group" "allow_ssh" {
  name        = "allow_ssh"
  description = "Allow SSH inbound traffic"
  vpc_id      = "vpc-0dd5fdb9e85af9abc"

  ingress {
    description = "SSH from your IP"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["72.14.201.202/32"]  # MEU IP
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

#resource "aws_iam_instance_profile" "lab_instance_profile" {
#  name = "LabInstanceProfile"
#  role = "LabRole"
#}


resource "aws_instance" "ec2_ubuntu" {
  ami           = "ami-08c40ec9ead489470" # Ubuntu 22.04
  instance_type = "t3.medium"
  subnet_id     = "subnet-0156c11dd1aa30cd4"

    vpc_security_group_ids = [
    aws_security_group.allow_ssh.id, 
  ]

  key_name = aws_key_pair.eks_bastion.key_name

  associate_public_ip_address = true

  iam_instance_profile = "LabInstanceProfile" 


  user_data = <<-EOF
              #!/bin/bash
              sudo apt-get update -y
              sudo apt-get install -y apt-transport-https ca-certificates curl unzip git

              # Instalar AWS CLI
              curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
              unzip awscliv2.zip
              sudo ./aws/install

              # Verificar instalação da AWS CLI
              aws --version

              # Instalar kubectl
              curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
              curl -LO "https://dl.k8s.io/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl.sha256"
              sudo echo "$(cat kubectl.sha256)  kubectl" | sha256sum --check
              sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl


              # Instalar Helm
              curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash

              # Configurar acesso ao cluster EKS snackbar-mithrandir
              aws eks update-kubeconfig --region us-east-1 --name snackbar-mithrandir
              EOF

  tags = {
    Name = "EC2-Ubuntu-Bastion"
  }
}

output "instance_public_ip" {
  value = aws_instance.ec2_ubuntu.public_ip
}

# Output do caminho da chave PEM
output "eks_bastion_pem_path" {
  value = local_file.eks_bastion_pem.filename
}

# Output do conteúdo da chave PEM
output "eks_bastion_pem_content" {
  value     = tls_private_key.eks_bastion_key.private_key_pem
  sensitive = true 
}
