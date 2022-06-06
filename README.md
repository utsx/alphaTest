# alphaTest
Для того чтобы запустить проект требуется ввести в Terminal:
```java
java -jar AlphaTest.jar
```
Запрос отправляется по адресу
```java
http://localhost:8080/{exhange}
```
или отправить запрос
```java
GET localhost:8080/{exchange}
```
Ответ будет таким 
```
HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8
Content-Length: 292
Date: date
Keep-Alive: timeout=60
Connection: keep-alive

<img src="{src}">
{
base='USD',
askedExchange={exchange},
todayCost={course},
yesterdayCost={course},
type='rich'
}
```
Где type
* rich 
* broke
* invalid

