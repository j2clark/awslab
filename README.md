# aws-lab



Remote debugging into EC2 using intellij

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