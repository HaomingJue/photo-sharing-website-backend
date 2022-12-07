# Photo Sharing Website Badckend
This project is for GNG5300 Individual Project Backend
# Tech Stack
**Database:** MongoDB \
**Framework:** Spring Boot \
**CI/CD**: Github Actions\
**Cloud Platform:** AWS EC2\
**API:** GraphQL\
*Notice: **SSL Certificate** is applied for secure https API url

Please check https://ec2-18-212-2-78.compute-1.amazonaws.com/graphiql \
Example query command:
```
query {
    allUsers {
        id
        username
        likedPhotos
    }
}
```