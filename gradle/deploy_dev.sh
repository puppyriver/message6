cd /home/smas/apache-tomcat-8.0.0-RC5/bin
./kill.sh
echo 'TOMCAT KILLED'

rm -Rf ../logs/*
echo 'LOG CLEANED'

rm -Rf ../webapps/idas*

echo 'OLD VERSION REMOVED'

cp $SMAS_BUILD/smas_build_dev/idas.war /home/smas/apache-tomcat-8.0.0-RC5/webapps

echo 'NEW VERSION INSTALLED'
echo 'STARTING...'
./startup.sh
sleep 2

tail -f -n 100 ../logs/catalina.out


