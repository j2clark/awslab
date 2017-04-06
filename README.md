# aws-lab

## References

AWS Java API http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/index.html

## TODO:

### Features
* enable Email messages
* enable SMS notifications
* enable application callback using SNS
* disable endpoint from alarm, using SNS 

### Metrics
* Send metrics to CloudWatch, 
  see http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/customize-containers-cw.html
  
  Of note, from here: https://aws.amazon.com/blogs/aws/amazon-cloudwatch-user-defined-metrics/ 
  The Custom Metrics are priced on a per-metric basis. You’ll pay $0.50 per metric per month regardless of the number of values that you store for the metric. We are also reducing the price of EC2 Detailed Monitoring to $3.50 per month (7 metrics * $0.50 / metric). You can store 10 metrics per month at no charge; these can be used for both Custom Metrics and EC2 Detailed Monitoring metrics.

  http://docs.aws.amazon.com/AmazonCloudWatch/latest/APIReference/API_PutMetricData.html
* create dashboard for mwetrics

### SDLC
* publish fresh builds to respective S3 bucket, with appropriate name, e.g.
    https://github.com/bazaarvoice/s3-upload-maven-plugin
    https://github.com/spring-projects/aws-maven

    repo/
    |-- appName/
    |       |-app-1.0.jar
    |       |-app-2.0.jar
    |       |-app-latest.jar (->app-2.0.jar)

### AMI 
* create custom java 8 AMI

### AutoScale Group 
* Implement AutoScale using custom AMI (above)
* Autoscale handler based on number of messages pending (Cloudwatch Alarm)
* Disable endpoint if pending messages > X (Cloudwatch Alarm)
todo: detailed setup of autoscale, including creating an alarm

## Logging

### Using CloudWatch Logs
http://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/WhatIsCloudWatchLogs.html
http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/quickref-cloudwatchlogs.html
https://aws.amazon.com/blogs/devops/getting-started-with-cloudwatch-logs/
todo: detail how awslogs used to centralize logs in CloudWatch
todo: investigate streaming logs to Elasticpath


## Remote debugging into EC2 using intellij

Under EC2 - Security Groups
1. Create Security Group:
    Create obvious name, e.g. Java Debug 5005
    Type            | Protocol  | Port  | Source
    Custom TPC Rule | TCP       | 5005  | 0.0.0.0/0
    
2. Under EC2 -> Instances
    Select EC2 instance to edit
    Actions -> Networking -> Change Security Groups
    Select checkbox next to Security Group [Java Debug 5005] to add to EC2 instance
    Click on Assign Security Group
   
Meanwhile, back in IntelliJ...
3. Create new Remote Debugging Configuration
    Under Host, add your EC2 instance public IP address
    
4. Restart java application in EC2 using debug settings
    e.g. java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 app.jar
    
5. Now run IntelliJ remote debug config - you should be connected!!!

6. I haven't done this yet, but if using an ssh bastion this may be of interest: http://arjon.es/2014/03/27/remote-debug-java-app-using-ssh-tunneling-without-opening-server-ports/