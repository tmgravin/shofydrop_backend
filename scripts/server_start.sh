#!/usr/bin/env bash

# Navigate to the server directory
cd /home/ec2-user/server/

# Define the location of the JAR file
JAR_FILE=$(ls target/*.jar)

# Check if the JAR file exists
if [ -z "$JAR_FILE" ]; then
  echo "JAR file not found!"
  exit 1
fi

# Run the JAR file
echo "Starting application from $JAR_FILE"
java -jar "$JAR_FILE"


