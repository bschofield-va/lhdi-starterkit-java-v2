#!/usr/bin/env bash

./gradlew clean build check docker
./gradlew :app:dockerComposeDown :app:dcPrune :app:dockerComposeUp
