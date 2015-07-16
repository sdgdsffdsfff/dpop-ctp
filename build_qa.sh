#!/bin/bash
    
      
FE_PROJECT="https://svn.baidu.com/app/ecom/dpop/trunk/ctp-fe"
export FE_PROJECT
FE_OUTPUT_DIR="./target/dpop-ctp"
export FE_OUTPUT_DIR

JAVA_HOME=$JAVA_HOME_1_6
export JAVA_HOME
PATH=$JAVA_HOME/bin:$PATH

export MAVEN_HOME=$MAVEN_3_0_4
PATH=$MAVEN_HOME:$PATH
export PATH
mvn clean

        echo "**********************************************"
        echo "It's going to build fe"
		echo "**********************************************"

#tool/build_fe_qa.sh

        echo "**********************************************"
        echo "It's going to mvn install "
		echo "**********************************************"

mvn install -Dmaven.test.skip=true -Pqa



#
#
#
if [ -d "output" ]; then
    printf '%s\n' "Removing output"
    rm -rf output
fi

mkdir -p output

cp target/dpop-ctp.war output
