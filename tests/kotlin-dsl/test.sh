#!/bin/sh

set -e

./gradlew clean
./gradlew -i packageFatCapsule
java -jar build/libs/kotlin-dsl-capsule.jar
