#!/usr/bin/env bash

set -ef -o pipefail

image_dev() {
    docker build -t springmvc-jetty-spike:dev -f docker/Dockerfile.dev docker
}

image_run() {
    docker build -t springmvc-jetty-spike:run -f docker/Dockerfile.run docker
}

build() {
    image_dev
    docker run -v $(pwd):/app -v $HOME/.gradle:/root/.gradle springmvc-jetty-spike:dev gradle --no-daemon --stacktrace --info clean build
}

command() {
    image_dev
    docker run -v $(pwd):/app -v $HOME/.gradle:/root/.gradle springmvc-jetty-spike:dev $@
}

run() {
    image_run
    docker run -p 8081:8081 -v $(pwd)/build/libs:/app springmvc-jetty-spike:run java -jar /app/springmvc-jetty-spike.jar
}

case "$1" in
    build)
        build
        ;;
    cmd)
        COMMAND=("$@")
        unset COMMAND[0]
        command ${COMMAND[*]}
        ;;
    gradle)
        GRADLE_ARGS=("$@")
        unset GRADLE_ARGS[0]
        command gradle --no-daemon --stacktrace ${GRADLE_ARGS[*]}
        ;;
    run)
        run
        ;;
    *)
        echo $"Usage: $0 {build|cmd [arguments]|gradle [arguments]|run}"
        exit 1
esac
