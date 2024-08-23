#!/bin/bash

# Print the current working directory
echo "Current working directory: $(pwd)"

# Print the PATH variable
echo "Current PATH: $PATH"

# Print Java version
JAVA_PATH=$(which java)
echo "Java path: $JAVA_PATH"
$JAVA_PATH -version

# Find the JAR file in the current directory
JAR_FILE=$(ls *.jar 2>/dev/null)

# Check if the JAR file exists
if [ -z "$JAR_FILE" ]; then
  echo "JAR file not found!"
  exit 1
fi

# Run the JAR file
echo "Starting application from $JAR_FILE"
$JAVA_PATH -jar "$JAR_FILE"
