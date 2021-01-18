#!/bin/sh
while ! nc -z configserver 8888 ; do
    echo 'waiting for serviceregistry ...';
    sleep 2
done
java -jar app.jar