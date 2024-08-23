#!/usr/bin/env bash
echo "Installing Amazon Corretto 19..."
wget https://corretto.aws/downloads/latest/amazon-corretto-19-x64-linux-jdk.tar.gz
tar -xzf amazon-corretto-19-x64-linux-jdk.tar.gz
sudo mkdir -p /usr/lib/jvm/
sudo mv amazon-corretto-19.0.2.7.1-linux-x64 /usr/lib/jvm/java-19-amazon-corretto

# Create the java.sh file properly
echo 'export JAVA_HOME=/usr/lib/jvm/java-19-amazon-corretto' | sudo tee /etc/profile.d/java.sh > /dev/null
echo 'export PATH=$JAVA_HOME/bin:$PATH' | sudo tee -a /etc/profile.d/java.sh > /dev/null

# Source the new profile script
source /etc/profile.d/java.sh

# Verify Java installation
java -version
