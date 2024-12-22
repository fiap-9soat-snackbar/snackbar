variable "bootstrap" {
    type = string
    default = <<EOF
        #!/bin/bash
        LOGFILE=/tmp/logfile
        echo "setting up the timezone America/Sao_Paulo" >> $LOGFILE
        sudo timedatectl set-timezone America/Sao_Paulo
        touch $LOGFILE
        echo "log file created at $(echo $LOGFILE)" >> $LOGFILE
        echo "start of script at $(date)" >> $LOGFILE
        echo "updating packages" >> $LOGFILE
        sudo yum update -y
        echo "updating kernel to 5.10" >> $LOGFILE
        sudo amazon-linux-extras install kernel-5.10 -
        echo "finish of script at $(date)" >> $LOGFILE
        EOF
}