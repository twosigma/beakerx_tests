#!/bin/bash

# all projects in one workspace
# usage:
# ./gradle_build_kernels.sh

(cd ../../beakerx_kernel_base; ./gradlew clean install)
(cd ../../beakerx_kernel_groovy; ./gradlew clean build)
(cd ../../beakerx_kernel_java; ./gradlew clean build)
(cd ../../beakerx_kernel_scala; ./gradlew clean install)
(cd ../../beakerx_kernel_kotlin; ./gradlew clean build)
(cd ../../beakerx_kernel_sql; ./gradlew clean build)
(cd ../../beakerx_kernel_clojure; ./gradlew clean build)

