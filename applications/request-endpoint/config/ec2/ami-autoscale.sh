#!/bin/bash

# bash script assumes AMI has java 1.8 installed
# and /app directory has been created
# For initialization from scratch, use bash.sh

export s3_base=j2clark/repo
export application=request-endpoint
export bucket=${s3_base}/${application}

#install awslogs [publish application logs to cloudwatch]
yum install awslogs -y

# copy appropriate awslogs configs from s3
# set region to us-west-1
aws s3 cp s3://${bucket}/conf/awscli.conf /etc/awslogs/awscli.conf
# set log group configuration [log groups auto-created, be careful]
aws s3 cp s3://${bucket}/conf/awslogs.conf /etc/awslogs/awslogs.conf
service awslogs start
chkconfig awslogs on

# copy latest jar from s3
aws s3 cp s3://${bucket}/jar/${application}-latest.jar /app/${application}.jar

# run application
# did not work until I changed into /app
# top showed as running, port used, but did nothing
cd /app
# debug mode
# debug mode - requires port 5005 to be opened using security groups
#java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 /app/request-endpoint.jar &
java -jar ${application}.jar &