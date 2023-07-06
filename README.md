# book-sales-project-with-SpringBoot
Book Sales Spring Boot Project
-Proje 8082 portunda çalışmaktadır. Tercihe göre değiştirilebilir.
  Bu Projede:
    1->Tüm Verileri Listeleme,
    2->Verileri Güncelleme veya Kaydetme,
    3->Stok Azaltma,
    4->Veri Silme,
    5->Belirlenen Fieldlara Göe Sorgulama Yapma İşlemleri Yapılabilmektedir.

-Bu projede veriler Elasticsearch Kibana arayüzünden ya da Rest API'den oluşturulan Post metodu ile veriler eklenebilir.
-Ayrıca Proje İçerisinde kullanılabilecek Alternatif metodlar yorum satırına alınmıştır.    
-Bu projede veriler Elasticsearch'te tutulmaktadır. Elasticsearch için kullanılan Docker-Compose File içeriği aşağıda verilmiştir. 
  
#Elasticsearch Docker-Compose File
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


