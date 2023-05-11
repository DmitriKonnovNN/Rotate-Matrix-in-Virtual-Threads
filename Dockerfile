FROM ghcr.io/distroless/base
RUN gu install native-image
RUN mkdir /app
COPY target/scala-2.13/graalvm-native-image-example-assembly-0.1.0-SNAPSHOT.jar /app
WORKDIR /app
RUN native-image --no-server -cp graalvm-native-image-example-assembly-0.1.0-SNAPSHOT.jar
CMD ["./graalvm-native-image-example-assembly-0.1.0-SNAPSHOT"]


