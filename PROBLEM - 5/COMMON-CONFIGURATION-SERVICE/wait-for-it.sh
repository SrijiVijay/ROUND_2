#!/bin/sh
while ! nc -z serviceregistry 80 ; do
    echo 'waiting for serviceregistry ...';
    sleep 2
done
java -jar app.jar