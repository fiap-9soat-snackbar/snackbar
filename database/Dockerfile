FROM mongo:latest

# Set MongoDB database default port to 27017 for build and environment variables
ARG DB_PORT=27017
ENV DB_PORT=$DB_PORT
EXPOSE $DB_PORT

# Copy scripts last, as it changes the most
#COPY dbLoad.sh dbLoad.sh
COPY dbLoadProducts.js dbLoadProducts.js

COPY docker-healthcheck /usr/local/bin/

HEALTHCHECK CMD ["docker-healthcheck"]

RUN ["echo", "Loading database..."]
CMD ["mongosh", "localhost:27017/snackbar", "dbLoadProducts.js"]

#CMD ["sh", "dbLoad.sh"]