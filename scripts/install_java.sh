#!/usr/bin/env bash

# Define download and extraction locations
DOWNLOAD_URL="https://corretto.aws/downloads/latest/amazon-corretto-19-x64-linux-jdk.tar.gz"
TEMP_DIR="/tmp/amazon-corretto"
JDK_DIR="/usr/lib/jvm/java-19-amazon-corretto"

echo "Installing Amazon Corretto 19..."

# Create a temporary directory for the download
mkdir -p $TEMP_DIR

# Download Amazon Corretto JDK
wget -O $TEMP_DIR/amazon-corretto-19-x64-linux-jdk.tar.gz $DOWNLOAD_URL

# Extract the JDK
tar -xzf $TEMP_DIR/amazon-corretto-19-x64-linux-jdk.tar.gz -C $TEMP_DIR

# Create the JDK directory and move the extracted files
sudo mkdir -p $JDK_DIR
sudo mv $TEMP_DIR/amazon-corretto-19.0.2.7.1-linux-x64/* $JDK_DIR

# Clean up temporary files
rm -rf $TEMP_DIR

# Create and configure the environment variables in ~/.bash_profile
echo 'export JAVA_HOME=/usr/lib/jvm/java-19-amazon-corretto' >> ~/.bash_profile
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bash_profile

# Source the updated .bash_profile
source ~/.bash_profile

# Verify Java installation
java -version
