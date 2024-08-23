#!/usr/bin/env bash

# Navigate to the server directory
cd /home/ec2-user/server 

JAR_FILE=$(ls -t *.jar | head -n 1)

# Step 4: Run the JAR file
output=$(java -jar "$JAR_FILE")
status=$?

# Step 5: Output results
echo "JAR output: $output"
if [ $status -eq 0 ]; then
    echo "JAR ran successfully"
else
    echo "JAR failed with status $status"
fi

