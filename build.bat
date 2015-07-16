echo JAVA_HOME: %JAVA_HOME%
echo M2_HOME: %MAVN_HOME%

call mvn clean 
call mvn install -Dmaven.test.skip=true


rd output /s
mkdir output


copy target\dpop-ctp.war output
