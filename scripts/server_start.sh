#!/usr/bin/env bash

# Navigate to the server directory
cd /home/ec2-user/server || exit

# Find the JAR file in the directory
JAR_FILE=$(ls *.jar 2>/dev/null)

# Check if a JAR file was found
if [ -n "$JAR_FILE" ]; then
    # Ensure the JAR file has the correct permissions
    sudo chmod +x "$JAR_FILE"

    # Run the JAR file on port 80 in the background
    sudo java -jar -Dserver.port=80 "$JAR_FILE" > /dev/null 2> /dev/null < /dev/null &
    
    echo "Application started successfully with $JAR_FILE."
else
    echo "No JAR file found in /home/ec2-user/server."
fi
sudo java -jar ShofyDrop_Backend-0.0.1-SNAPSHOT.jar
