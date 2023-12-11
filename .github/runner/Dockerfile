FROM debian:latest

RUN apt-get update -y \
    && apt-get install -y \
        curl \
        build-essential

WORKDIR /app


RUN curl -o actions-runner-linux-x64-2.311.0.tar.gz -L \
    https://github.com/actions/runner/releases/download/v2.311.0/actions-runner-linux-x64-2.311.0.tar.gz \
    && tar xzf ./actions-runner-linux-x64-2.311.0.tar.gz
RUN ./bin/installdependencies.sh

COPY start.sh ./

CMD ["/app/start.sh"]
