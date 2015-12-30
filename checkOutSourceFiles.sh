#!/bin/sh
if [ ! -d $1 ]
then
   echo "making dir"
   mkdir $1
else
   echo "deleting and making dir"
   rm -rf $1
   mkdir $1
fi
cd $1
git init
git config core.sparseCheckout true
echo src > .git/info/sparse-checkout
git remote add -f origin $2
git pull origin master

