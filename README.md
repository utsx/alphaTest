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
gif_url

{
base='USD',
askedExchange='{exchange}',
todayCost={course},
yesterdayCost={course},
type='{type}'
}
```
Где type
* rich 
* broke
* invalid
