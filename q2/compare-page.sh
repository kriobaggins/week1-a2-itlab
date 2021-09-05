#!/bin/bash

./get-page.sh $1 $2 $3 | telnet | sed -e '1,/^\s*$/d' > /tmp/telnet.out
# ./get-page.sh $1 $2 $3 | telnet > /tmp/telnet.original.out
# sed -e '1,3d' < /tmp/telnet.original.out > /tmp/telnet.out
echo "$f" | cat >> /tmp/telnet.out
diff -q /tmp/telnet.out ./testing.out 1> /dev/null
if [ $? -eq "0" ]
then
    echo "TEST OK"
else 
    echo "TEST FAILED"
fi
