#!/bin/bash

# ec2 bash script executed by root on ec2 instance initialization

# install security updates
yum update -y
yum install java-1.8.0 -y
yum remove java-1.7.0-openjdk -y

# create directory for application and log files
mkdir /app
cd /app
#log dir auto created by app
#mkdir logs

# copy latest jar from s3
aws s3 cp s3://j2clark/repo/tmail/latest/latest.jar /app/app.jar

# run application - we should still be /app
# debug mode
#java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 app.jar &
java -jar app.jar &