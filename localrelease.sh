#/bin/sh!

mvn11 clean install -DskipTests

if [ $? -eq 0 ]; then
        export REPO=$(mvn -q -Dexec.executable=echo  -Dexec.args='${project.artifactId}' --non-recursive exec:exec)
        export VERSION=$(mvn -q -Dexec.executable=echo  -Dexec.args='${project.version}' --non-recursive exec:exec)
        export USER=setupnet
        echo $VERSION
        docker build --build-arg version=$VERSION -t $USER/$REPO:local .
#        if [ $? -eq 0 ]; then
#                docker push $USER/$REPO:local
#        fi
fi



