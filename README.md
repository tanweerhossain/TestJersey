#For intsall package

mvn install

#For server starting

mvn package exec:java

#Look GET Response

URL : http://localhost:8080/api/:id

#Look POST Response

URL : http://localhost:8080/api
Content-Type: application/json
BODY : {
"name" : "xyz"
}

#Look DELETE Response

URL : http://localhost:8080/api/:id

