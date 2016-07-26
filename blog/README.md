Blog
====

Grails REST API and Angular UX


# Setup 
> only do setup steps if you want to build this app from the scratch)

> follow http://alvarosanchez.github.io/grails-angularjs-springsecurity-workshop/
>  http://grailsblog.ociweb.com/posts/2016/07/06/angular-scaffolding.html

```bash
cd cloud-native-apps
grails create-app blog --profile angular  --features hibernate5,security,json-views

grails
grails> s2-quickstart User Role
grails> create-domain-class Todo
grails> create-restful-controller Todo
grails> generate-views blog.Todo
grails> create-domain-resource Post
grails> create-domain-class PostContent
grails> create-domain-resource Tag
grails> create-domain-resource Comment

grails> ngGenerateAll blog.Post true
grails> ngGenerateAll blog.Tag true
grails> ngGenerateAll blog.Comment true
grails> ngGenerateAll blog.Todo true
```

# Run
```bash
cd blog
grails

grails> run
```

To get a token, make a request to the login endpoint provided by the plugin:

```bash
curl -i -H "Content-Type: application/json" --data '{"username":"user","password":"pass"}' 0:8080/api/login
```
Verify access_token with https://jwt.io/
> default secret : qrD6h8K6S9503Q06Y6Rfk21TErImPYqa

Copy the access_token part of the response, and make a request to the original endpoint, passing the token in a header:

```bash
curl -i -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...." 0:8080/api/todos
```

curl -i -H "Authorization: Bearer " 0:8080/api/todos