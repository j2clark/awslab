# EC2 Setup

The following steps need to be done in order to deploy this application


## Region
Note: All config files assume a region of "us-west-1"
grep and replace al instances with desired region if different

## EC2 IAM Roles
This application requires the following EC2 Roles:
* AmazonSQSFullAccess
* AmazonS3ReadOnlyAccess
* CloudWatchLogsFullAccess

## A Public IP must be assigned

## Security Groups
At a minimum, port 20 should be open for SSH access
Port 8080, should be open by default (todo: confirm)
Port 5005 should be opened only for remote debugging, otherwise do not open

## Log Groups
Cloudwatch Log Group should be created - see awslogs.conf 
request-app
request-endpoint-access

## S3 Files
All files under s3/ 

### Configuration
### Application
