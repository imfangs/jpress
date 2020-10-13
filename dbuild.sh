#!/bin/bash

version="$1"

if [[ "$version" == "" ]]; then
	echo "./please designated docker image version"
	exit 0
fi


mvn clean install --settings /Users/fangshuai/.m2/settings.aliyun.xml -Dmaven.test.skip=true


echo "exec : docker build . -t fangs2046/jpress:"${version}
docker build . -t fangs2046/jpress:${version}


echo "exec : docker push fangs2046/jpress:"${version}
docker push fangs2046/jpress:${version}


echo "exec : docker push fangs2046/jpress:latest"
docker tag fangs2046/jpress:${version} fangs2046/jpress:latest
docker push fangs2046/jpress:latest


#echo "exec : docker push jpressio/jpress:"${version}
#docker tag fangs2046/jpress:latest jpressio/jpress:${version}
#docker push jpressio/jpress:${version}
#
#
#echo "exec : docker push jpressio/jpress:latest"
#docker tag jpressio/jpress:${version} jpressio/jpress:latest
#docker push jpressio/jpress:latest


