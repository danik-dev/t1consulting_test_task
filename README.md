# Test task for t1-consulting
**Task:** Спроектировать(продумать формат и ограничения входящих/исходящих параметров) и реализовать REST API, вычисляющее частоту встречи символов по заданной строке. Результат должен быть отсортирован по убыванию количества вхождений символа в заданную строку.

**Требования к решению:**
* Java 8+
* Spring boot 2+
* Решение должно быть покрыто тестами
* У решения должна быть документация по запуску и формату входящих/исходящих параметров
* Код решения необходимо разместить в публичном Github репозитории.

Пример входной строки: “aaaaabcccc”
Пример выходного результата: “a”: 5, “c”: 4, “b”: 1

## START UP INSTRUCTIONS

### For production environment 
This application is dockerized so that you only need to run:

        docker-compose up

under project directory.

### For development perposes
You need to have installed:
* jdk
* maven

Under project directory run following commands:
                   
        mvn clean install
        java -jar target/t1consulting_test_task-0.0.1-SNAPSHOT.jar

## API Documentation
You can access it after application run with this link:
        
        http://localhost:8080/swagger-ui/index.html# 
       



