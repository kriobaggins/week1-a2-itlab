#!/bin/bash

./get-page.sh $1 $2 $3 | telnet | sed -e '1,/^\s*$/d' > /tmp/telnet.out
# ./get-page.sh $1 $2 $3 | telnet > /tmp/telnet.original.out
# sed -e '1,3d' < /tmp/telnet.original.out > /tmp/telnet.out

if [ $(diff /tmp/telnet.out ./testing.out) ]
then
    echo "TEST FAILED"
else 
    echo "TEST OK"
fi
