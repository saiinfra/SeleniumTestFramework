#!/bin/sh

echo javac -classpath ./lib/*.jar:./build/classes -d $1 $2
javac -classpath ./lib/*.jar:./build/classes -d $1 $2

exit
