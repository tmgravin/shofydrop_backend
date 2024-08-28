#!/bin/bash

# Set JAVA_HOME and PATH
export JAVA_HOME=/usr/lib/jvm/java-19-amazon-corretto
export PATH=$JAVA_HOME/bin:$PATH

# Print the current working directory
echo "Current working directory: $(pwd)"

# Print the PATH variable
echo "Current PATH: $PATH"

# Print Java version
JAVA_PATH=$(which java)
echo "Java path: $JAVA_PATH"
if [ -z "$JAVA_PATH" ]; then
  echo "Java is not in the PATH!"
  exit 1
fi
$JAVA_PATH -version
find /home/ec2-user/server/target/ -name "*.jar" -exec mv {} /home/ec2-user/server/ \;

cd /home/ec2-user/server/
# Find the JAR file in the current directory
JAR_FILE=$(ls *.jar 2>/dev/null)

# Check if the JAR file exists
if [ -z "$JAR_FILE" ]; then
  echo "JAR file not found!"
  exit 1
fi

# Run the JAR file
echo "Starting application from $JAR_FILE"
nohup $JAVA_PATH -jar "$JAR_FILE" > /tmp/java_app.log 2> /tmp/java_app.err < /dev/null &
