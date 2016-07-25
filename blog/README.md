Blog
====

Grails REST API and Angular UX


# Setup

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
```

# Run
```bash
grails

grails> run
```

To get a token, make a request to the login endpoint provided by the plugin:

```bash
curl -i -H "Content-Type: application/json" --data '{"username":"user","password":"pass"}' 0:8080/api/login
```
Copy the access_token part of the response, and make a request to the original endpoint, passing the token in a header:
```bash
curl -i -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...." 0:8080/api/todos
```
curl -i -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJwcmluY2lwYWwiOiJINHNJQUFBQUFBQUFBSlZTdTA3Y1FCUzkzaXdDQlNrQnBDQlJRQVBwSXE5RXlxMTRpOGhaVUpadGlBU2F0UzltWUR6anpJeGh0NG0yU2dvS0loNVNsUHdDZndJTkh4Q0ZncGFhbGp1R3hSc2FsS25zTzhmbmRYMTJBMzFHd1wvdFlNeTZNbjRvczV0STNxZVl5TmhobW10dTJueG5VRWRvY3NaUURHelNCKytPVndBdWd4Q01MSThFTzIyTVZ3V1JjV1dudVlHaXJMUTNUU3NjUGpGdWFKYml2OUs3XC95QjBxamY4SUZOVGVyeEwwcjhNd0MwT1ZTVnRUY3FHVmNvM1JPZ3dWczBDRnUyNzBKcVFibEpZellYcWhcL1NoWlUyQVV3Q0RMN0xZaVZZN0d3dXQ3czVubG9sSkhXdzFnSUdYR2tMc25TZXJXV1hmM3pxYWtCRlwvZ0s1UmJxVWVIdW52cm9MN2o4ZWVVRUpTYUsybW1HakpSRWRcL2lUcHo0TytOSGw0ZVwvTzQwU0FIWHk3dmx2aXZuWUxIVE9OMjRuOHFLOTBNSm9qXC9VQ1ZtMmw1R2E0WUY3VDZKVFwvXC9GdzlQcjM1XC92a0ZLVHZFNHZcL3ZZMnJtb2JuMm5FcFNwcGxWUFRzaTJ2MnlleWJ5MmVmSnUxdG8rM1dlcEFMcGo1SVdvMGVKZ3BqaWxyVVMzYjR0dlB5MEVpeHN6c3hcL1hLNjUxN0x6U2FLdjhzeHVXWDZnYUZVSDF6OHVEaWZcL0VzRUg2TnRqSWtPcWZLZ0ExYktraWZyYjJlbjQ0TW5WUVI2Zyt6UGZBZVdZUzdjUUF3QUEiLCJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJleHAiOjE0Njk0NDQ5NTAsImlhdCI6MTQ2OTQ0MTM1MH0.lG4e6O3iUTY1mcPaUPsBrmXbmJ7gs2wshW2gqpszFOo" 0:8080/api/todos