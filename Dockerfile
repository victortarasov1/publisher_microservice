FROM debian:bullseye


# prepare system
RUN apt-get update -y && apt-get install -y openjdk-17-jdk wget zip jq


# Download and install gradle
RUN LATEST_GRADLE_VERSION=$(wget -qO - https://services.gradle.org/versions/current | sed -n 's/.*"version" : "\([^"]*\)".*/\1/p') \
    && wget "https://services.gradle.org/distributions/gradle-${LATEST_GRADLE_VERSION}-bin.zip" -O /tmp/gradle.zip \
    && unzip -d /opt /tmp/gradle.zip \
    && ln -s /opt/gradle-${LATEST_GRADLE_VERSION} /opt/gradle \
    && rm /tmp/gradle.zip


ENV PATH="/opt/gradle/bin:${PATH}"


WORKDIR /app


COPY . .

RUN gradle build