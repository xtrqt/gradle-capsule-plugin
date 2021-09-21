#!/bin/sh

set -e

rm -rf ../subproject1/my-api/build ../subproject2/my-impl/build
./gradlew clean
./gradlew -i packageFatCapsule
java -jar my-app/build/libs/my-app-capsule.jar
