#!/usr/bin/env bash
echo "Installing Amazon Corretto 19..."
wget https://corretto.aws/downloads/latest/amazon-corretto-19-x64-linux-jdk.tar.gz
tar -xzf amazon-corretto-19-x64-linux-jdk.tar.gz
sudo mkdir -p /usr/lib/jvm/
sudo mv amazon-corretto-19.0.2.7.1-linux-x64 /usr/lib/jvm/java-19-amazon-corretto
sudo tee /etc/profile.d/java.sh <<EOF
  export JAVA_HOME=/usr/lib/jvm/java-19-amazon-corretto
  export PATH=\$JAVA_HOME/bin:\$PATH
EOF
source /etc/profile.d/java.sh
java -version  # Verify Java installation
