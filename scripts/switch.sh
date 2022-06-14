#!/bin/bash
num=$1
docker restart springboot$num
echo "set \$service_springboot http://springbootweb$num;" | tee /var/jenkins_home/nginx/conf.d/service-springboot.inc
docker exec nginx service nginx reload