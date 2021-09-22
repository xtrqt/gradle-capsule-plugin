#!/bin/sh

set -e

./gradlew clean
./gradlew -i packageFatCapsule :module2:packageFatCapsule2
java -jar module1/build/libs/module1-capsule.jar
java -jar module2/build/libs/module2-all.jar
#java -jar module2/build/libs/module2-capsule.jar
