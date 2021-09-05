#!/bin/bash

STATUS=`./get-page.sh $1 $2 $3 | telnet | sed -n '4'p | cut -d" " -f 2`
STATUS=${STATUS:0:1}

if [ $STATUS -eq 2 ]
then
    echo "SERVER UP"
else 
    if [ $STATUS -eq 3 ]
    then
        echo "REQUEST REDIRECTED"
    else 
        if [ $STATUS -eq 4 ]
        then
            echo "CLIENT SIDE ERROR"
        else
            if [ $STATUS -eq 5 ]
            then
                echo "SERVER SIDE ERROR"
            fi
        fi
    fi
fi
