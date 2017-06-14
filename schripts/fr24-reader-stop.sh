#!/bin/bash

PID=`ps -ef | grep fr24 | awk '{ print $2 }'`
kill -9 $PID
