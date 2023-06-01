#!/bin/bash
address=$1
port=$2
dir=$3

# Check if arguments are provided
if [ -z "$address" ] || [ -z "$port" ] || [ -z "$dir" ]; then
    echo "Usage: ./client.sh <localhost> <port> <directory>"
    exit 1
fi

java -jar client/target/client-1.0-SNAPSHOT.jar "$address" "$port" "$dir"