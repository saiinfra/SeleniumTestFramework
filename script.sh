#!/bin/sh
mkdir testsrc1
cd testsrc1
git init
git config core.sparseCheckout true
echo src > .git/info/sparse-checkout
git remote add -f origin $1
git pull origin master

