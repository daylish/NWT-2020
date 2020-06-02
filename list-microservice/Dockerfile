FROM openjdk:11

ARG APP_FILE=list-microservice-0.0.1-SNAPSHOT.jar
ARG PORT=8083

ENV VERTICLE_PATH target/$APP_FILE

# Set the location of the verticles
ENV APP_HOME /opt/app

# for the HTTP server
EXPOSE $PORT

COPY $VERTICLE_PATH $APP_HOME/

RUN mkdir -p $APP_HOME/config

WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]

RUN echo "#!/bin/sh">launcher.sh

RUN echo -n "java -XX:+UseContainerSupport -XX:+UseParallelGC -jar  $APP_FILE">>launcher.sh
RUN chmod +x ./launcher.sh

CMD ["/opt/app/launcher.sh"]
