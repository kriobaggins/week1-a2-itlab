#!/bin/bash

GOOGLE_URL="/tmp/google.size"
YAHOO_URL="/tmp/yahoo.size"

if ! [ -f $YAHOO_URL ]
then
    curl https://www.google.com/ > /tmp/google.size
fi

if ! [ -f $YAHOO_URL ]
then
    curl https://www.yahoo.com/ > /tmp/yahoo.size
fi

URL=$GOOGLE_URL
GOOGLE=`ls -al $URL | cut -d" " -f 5`
GOOGLE_READ=`ls -alh $URL | cut -d" " -f 5`

URL=$YAHOO_URL
YAHOO=`ls -al $URL | cut -d" " -f 5`
YAHOO_READ=`ls -alh $URL | cut -d" " -f 5`

if [ $GOOGLE -ge $YAHOO ]
then
    echo "google site size ($GOOGLE_READ) greater than yahoo ($YAHOO_READ)"
else
    echo "google site size ($GOOGLE_READ) lesser than yahoo ($YAHOO_READ)"
fi
