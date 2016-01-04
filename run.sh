#!/bin/sh

java -cp "./lib/*:./bin" org.junit.runner.JUnitCore $1
exit
