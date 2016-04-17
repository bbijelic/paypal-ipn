FROM java:8-jre-alpine

MAINTAINER Bojan Bijelic <bojan.bijelic.os@gmail.com>

ADD target/paypal-ipn-0.0.1.jar /app/bin/
ADD target/config.yml /app/config/

EXPOSE 8080

VOLUME ["/app/config", "/app/logs"]

ENTRYPOINT [ "java", "-jar", "/app/bin/paypal-ipn-0.0.1.jar", "server", "/app/config/config.yml" ]

