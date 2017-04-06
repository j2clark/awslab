#!/bin/bash

s3_base=j2clark/repo
application=request-endpoint
bucket=${s3_base}/${application}

# ec2 bash script executed by root on ec2 instance initialization

# install security updates
yum update -y
yum install java-1.8.0 -y
yum remove java-1.7.0-openjdk -y

# Post AMI Config from here on

#install awslogs [publish application logs to cloudwatch]
yum install awslogs -y

# copy appropriate awslogs configs from s3
aws s3 cp s3://${bucket}/conf/awscli.conf /etc/awslogs/awscli.conf
aws s3 cp s3://${bucket}/conf/awslogs.conf /etc/awslogs/awslogs.conf
service awslogs start
chkconfig awslogs on

# create directory for application and log files
mkdir /app

# copy latest jar from s3
aws s3 cp s3://${bucket}/jar/${application}-latest.jar /app/${application}.jar

# run application - we should still be /app
# debug mode
#java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 /app/${application}.jar &
java -jar /app/${application}.jar &

