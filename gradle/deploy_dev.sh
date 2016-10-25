cd /home/app/apache-tomcat-8.0.24/bin
./kill.sh
echo 'TOMCAT KILLED'

rm -Rf ../logs/*
echo 'LOG CLEANED'

rm -Rf ../webapps/ROOT*

echo 'OLD VERSION REMOVED'

cp /home/github/message6/gradle/message6_build/ROOT.war /home/app/apache-tomcat-8.0.24/webapps

echo 'NEW VERSION INSTALLED'
echo 'STARTING...'
./startup.sh
sleep 2

tail -f -n 100 ../logs/catalina.out


