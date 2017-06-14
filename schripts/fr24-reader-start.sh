#!/bin/bash

java -jar /home/pi/fr24-reader-0.0.2.jar & echo $! > /run/fr24.pid
