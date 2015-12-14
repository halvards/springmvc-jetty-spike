# springmvc-jetty-spike [![Travis CI Build Status](https://travis-ci.org/halvards/springmvc-jetty-spike.svg?branch=master)](https://travis-ci.org/halvards/springmvc-jetty-spike)

Spike to work out what's required to run a Spring MVC application using embedded Jetty, where the application is configured only with code and annotations, no XML files.

After launch, you should be able to go to <http://localhost:8081/app/hello.txt> and see "Hello World"

The application port can be controlled using the `PORT` environment variable.

Run the tests using `./gradlew clean check`.
