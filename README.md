# Movies

This is a Spring Boot app. So it should be initialized as so.

For testing the API, you could use a REST client such as Postman.

You could also check it's API documentation accessing the URL (considering that the APP was deployed on localhost:8080):
http://localhost:8080/swagger-ui/

In order to get access to the movies API, you should be authorized. Which is made by generating a token through JWT API. Follow the steps:

### 1 on Postman access the URL
POST		http://localhost:8080/auth

body<br/>
{<br/>
   "email": "usuario@email.com",<br/>
   "senha": "123456"<br/>
}

(there are two users: admin and usuario, with the same password. The admin user has write permissions, while the usuario has only read permission)

### 2 Copy the generated token

### 3 In the other requests, use the following headers:
Authorization = Bearer COPIED_TOKEN<br/>
Content-Type = application/json

## Movies API:

#### GET		  http://localhost:8080/movies/
#### DELETE	http://localhost:8080/movies/<MOVIE_ID>
#### POST	  http://localhost:8080/movies/
Body<br/>
{<br/>
   "title": "title",<br/>
   "release‌": "yyyy-mm-aa",<br/>
   "genre": "genre",<br/>
   "overview": "overview",<br/>
   "rarating": rarating // int<br/>
}
#### PUT	    http://localhost:8080/movies/<MOVIE_ID>
Body<br/>
{<br/>
   "title": "title",<br/>
   "release": "yyyy-mm-aa",<br/>
   "genre": "genre",<br/>
   "overview": "overview",<br/>
   "rarating": rarating // int<br/>
}

## Stats API
(you don’t have to be authenticated to access this):

#### GET	 	http://localhost:8080/stats/movie-count
#### POST 	http://localhost:8080/stats/movie-count-by-genre
body<br/>
{<br/>
    "genre": "fantasy"<br/>
}
