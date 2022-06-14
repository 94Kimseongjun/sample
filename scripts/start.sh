#!/bin/bash
JAR_NAME=$(ls -tr /root/spring_project/target/*.jar | tail -n 1)
DIR_PATH=/root/spring_project
NUMBER=$PROFILE
java -jar -Dspring.config.location=classpath:/application.properties,classpath:/application-real$NUMBER.properties,$DIR_PATH/properties/application-oauth.properties,$DIR_PATH/properties/application-real-db.properties -Dspring.profiles.active=real $JAR_NAME