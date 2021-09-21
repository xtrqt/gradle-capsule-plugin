#!/bin/sh

set -e

./gradlew clean
./gradlew -i packageFatCapsule
java -jar build/libs/groovy-dsl-capsule.jar
