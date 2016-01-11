#!/bin/sh

echo javac -classpath ./lib/*.jar:./bin -d $1 $2
javac -classpath ./lib/*.jar:./bin -d $1 $2

exit
