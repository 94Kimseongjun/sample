#!/bin/bash
# 현재 동작중인 springboot container 찾기
# nginx 설정으로 찾아야함

springboot=$(cat /var/jenkins_home/nginx/conf.d/service-springboot.inc)
number=($(echo $springboot | tr " " "\n"))
if [ ${number[2]} == 'http://springbootweb1;' ]
then
        echo "springbootweb1"
else
        echo "springbootweb2"
fi