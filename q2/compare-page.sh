#!/bin/bash

./get-page.sh $1 | telnet > /tmp/telnet.original.out
sed '1,3d' < /tmp/telnet.original.out > /tmp/telnet.out

if ! [ diff /tmp/telnet.out ./testing.out ]
then
    echo "TEST OK"
else 
    echo "TEST FAILED"
fi
