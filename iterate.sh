#!/bin/bash

mvn clean install
echo "Copying to directory"
cp ./target/tube-1.0-SNAPSHOT.war ../glassfish4/glassfish/domains/domain1/autodeploy/
sleep 1
ls -l ../glassfish4/glassfish/domains/domain1/autodeploy/
