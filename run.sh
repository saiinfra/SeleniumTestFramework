#!/bin/sh

java -cp "./lib/*:./build/classes" org.junit.runner.JUnitCore $1
exit
