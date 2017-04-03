#!/bin/bash

# ec2 bash script executed by root on initialize

# install security updates
yum update -y

# create directory for application and log files
mkdir ~/app
cd ~/app
mkdir logs

# copy latest jar from s3
aws cp s3://j2clark/repo/tmail/latest/app.jar ~/app/app.jar

# run application
java -jar app.jar &