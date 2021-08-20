#/bin/sh!
mvn11 -B release:prepare release:perform  -Darguments="-DskipTests -Dmaven.javadoc.skip=true" 
if [ $? -eq 0 ]; then
	cd target/checkout
	export REPO=$(mvn11 -q -Dexec.executable=echo  -Dexec.args='${project.artifactId}' --non-recursive exec:exec)
	echo REPO=$REPO 
	export VERSION=$(mvn11 -q -Dexec.executable=echo  -Dexec.args='${project.version}' --non-recursive exec:exec)
	echo VERSION=$VERSION
	export USER=setupnet
	echo USER=$USER
	echo $VERSION
	docker build --build-arg version=$VERSION -t $USER/$REPO:$VERSION .
	if [ $? -eq 0 ]; then
		docker push $USER/$REPO:$VERSION
	fi
fi


