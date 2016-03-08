# springmvc-jetty-spike [![Travis CI Build Status](https://travis-ci.org/halvards/springmvc-jetty-spike.svg?branch=master)](https://travis-ci.org/halvards/springmvc-jetty-spike) [![Circle CI Build Status](https://circleci.com/gh/halvards/springmvc-jetty-spike.svg?style=svg)](https://circleci.com/gh/halvards/springmvc-jetty-spike) [![Drone.io Build Status](https://drone.io/github.com/halvards/springmvc-jetty-spike/status.png)](https://drone.io/github.com/halvards/springmvc-jetty-spike/latest)

Spike to work out what's required to run a Spring MVC application using embedded Jetty, where the application is configured only with code and annotations, no XML files.

After launch, you should be able to go to <http://localhost:8081/app/hello.txt> and see "Hello World"

The application port can be controlled using the `PORT` environment variable.

Run the tests using `./gradlew clean check`.
