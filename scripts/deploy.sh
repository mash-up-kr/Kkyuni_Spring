#!/bin/bash
BUILD_JAR=$(ls /home/ec2-user/mash/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo "> build 파일명: $JAR_NAME" >> /home/ec2-user/mash/deploy.log

echo "> build 파일 복사" >> /home/ec2-user/mash/deploy.log
DEPLOY_PATH=/home/ec2-user/mash/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ec2-user/mash/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ec2-user/mash/deploy.log
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 배포"    >> /home/ec2-user/mash/deploy.log
sudo sh /home/ec2-user/mash/mash.sh
#nohup java -jar /home/ec2-user/mash/ggyuni-spring-0.0.1-SNAPSHOT.jar --GOOGLE_ID=924686911312-si9k02cacnc39djbj86ofh47rj1mo706.apps.googleusercontent.com >> /home/ec2-user/deploy.log 2>/home/ec2-user/mash/deploy_err.log &