FROM mongo:8.0.1

# Set MongoDB database default port to 27017 for build and environment variables
ARG DB_PORT=27017
ENV DB_PORT=$DB_PORT
EXPOSE $DB_PORT

# Copy scripts last, as it changes the most
#COPY dbLoad.sh dbLoad.sh

# COPY *.js /docker-entrypoint-initdb.d/

#COPY *.js *.js

#COPY docker-healthcheck /usr/local/bin/

# HEALTHCHECK CMD ["docker-healthcheck"]

#RUN ["echo", "Loading database..."]
#CMD ["mongosh", "localhost:27017/snackbar", "dbLoadProducts.js"]

#RUN ["sh", "dbLoad.sh"]
#RUN ["mongod"]
#RUN ["/bin/bash", "-c", "/dbLoad.sh"]