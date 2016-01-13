#!/bin/sh

echo javac -classpath ./lib/*:./build/classes -d $1 $2
javac -classpath ./lib/*:./build/classes -d $1 $2

exit
