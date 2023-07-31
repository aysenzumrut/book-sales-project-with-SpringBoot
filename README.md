# Book Sales Project with Spring Boot and Elasticsearch
## Purpose 

- This project was written to develop a Spring Boot Rest API using elasticsearch and docker. An elasticsearch container has been created with the docker-compose.yaml file. And an index named books has been created in this elasticsearch. The project entity is linked to this index. Data viewing, updating, deleting and new data recording operations are performed in this index.
- In this project, while the java file is connected to the localhost port 8082, elasticsearch and kibana are running on the virtual machine over linux ubuntu.

## Technologies
- Java 17
- Spring Boot 2.4.8
- Maven
- Elasticsearch
- Docker

## Features
1->Tüm Verileri Listeleme, 2->Verileri Güncelleme veya Kaydetme, 3->Stok Azaltma, 4->Veri Silme, 5->Belirlenen Fieldlara Göe Sorgulama Yapma İşlemler
- Get All Books
- Update or Save Book
- Stock Decrease by Name
- Delete Book By Id
- Search Books Acording to Name, Author or Category

## docker-compose.yaml
- Elasticsearch and kibana containers are started by typing 'docker compose up' command in the terminal. After that, the project is up and running.
```
version: "3.0"
services:
  elasticsearch:
    container_name: es-container
    image: docker.elastic.co/elasticsearch/elasticsearch:8.7.0
    environment:
      - xpack.security.enabled=false
      - "discovery.type=single-node"
    networks:
      - es-net
    volumes:
      - es-data:/usr/share/elasticsearch/data
      - es-logs:/usr/share/elasticsearch/logs
    ports:
      - 9200:9200
  kibana:
    container_name: kb-container
    image: docker.elastic.co/kibana/kibana:8.7.0
    environment:
      - ELASTICSEARCH_HOSTS=http://es-container:9200
    networks:
      - es-net
    depends_on:
      - elasticsearch
    ports:
      - 5601:5601
volumes:
  es-data:
  es-logs:
networks:
  es-net:
    driver: bridge
```


