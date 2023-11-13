#!/bin/bash

if [ -z $BRANCH -o -z $URL -o -z $PORT ]; then
    echo "please specify enviroments for repo URL, repo BRANCH, service PORT"
    exit
fi

# Dependencies
mkdir -p /dew
cd /dew
if [ ! -d /dep/.git ];then #clone only the first time
    git clone --depth=1 --branch master https://github.com/giosil/wcollections.git ||  { echo "fatal: failed to clone branch 'master' of 'wcollections'" && exit 1; }
fi
cd /dew/wcollections
git config pull.ff only
git pull || { echo "fatal: failed to pull branch 'master' of 'wcollections'" && exit 1; }
mvn clean install

# Application
mkdir -p /app
cd /app
if [ ! -d /app/.git ];then #clone only the first time
    git clone --depth=1 --branch $BRANCH $URL . ||  { echo "fatal: failed to clone branch '$BRANCH' at url '$URL'" && exit 1; }
    git config pull.ff only
fi
git pull || { echo "fatal: failed to pull branch '$BRANCH' at url '$URL'" && exit 1; }

echo "running: mvn spring-boot:run -Dspring-boot.run.profiles=docker -Dspring-boot.run.arguments=\"--management.server.port=$PORT --server.port=$PORT\""
exec mvn spring-boot:run -Dspring-boot.run.profiles=docker -Dspring-boot.run.arguments="--management.server.port=$PORT --server.port=$PORT"