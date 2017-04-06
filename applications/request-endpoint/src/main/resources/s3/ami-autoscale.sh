#!/bin/bash

# bash script assumes AMI has java 1.8 installed
# and /app directory has been created
# For initialization from scratch, use bash.sh

s3_base=j2clark/repo
application=request-endpoint
bucket=${s3_base}/${application}

#install awslogs [publish application logs to cloudwatch]
yum install awslogs -y

# copy appropriate awslogs configs from s3
aws s3 cp s3://${bucket}/conf/awscli.conf /etc/awslogs/awscli.conf
aws s3 cp s3://${bucket}/conf/tmail/awslogs.conf  /etc/awslogs/awslogs.conf
service awslogs start
chkconfig awslogs on

# copy latest jar from s3
aws s3 cp s3://${bucket}/jar/${application}-latest.jar /app/${application}.jar

# run application - we should still be /app
# debug mode
#java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 /app/request-endpoint.jar &
java -jar /app/${application}.jar &

