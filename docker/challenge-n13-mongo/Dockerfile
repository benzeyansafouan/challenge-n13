FROM mongo:4.4.2

EXPOSE 27017

ADD init.sh /init.sh
ADD file.key /file.key

RUN chmod +x /init.sh
RUN chmod 400 /file.key
RUN chown 999:999 file.key
