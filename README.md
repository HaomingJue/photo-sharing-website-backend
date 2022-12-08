# Photo Sharing Website Backend
This project is for GNG5300 Individual Project Backend
# Tech Stack
**Database:** MongoDB \
**Framework:** Spring Boot \
**CI/CD**: Github Actions\
**Cloud Platform:** AWS EC2\
**API:** GraphQL\
*Notice: **SSL Certificate** is applied for secure https API url (disabled due to extra requirement for Chrome configuration)

Please check (disabled now) https://ec2-18-212-2-78.compute-1.amazonaws.com/graphiql \
(Alternative) https://ec2-18-212-2-78.compute-1.amazonaws.com/graphiql \
## Dilemma and Solution
Originally, the reason I self signed SSL certificate and created Https API url is that Azure Static Web app accepts HTTPS communication only. \
There are some methods to make Azure static web app accept http request using Azure Gateway. But since I have limited free credits in my Azure account, it is not a good idea. \
After I finished the project and successfully tested it on my own PC's browser, I got some feedbacks from the customer that the website doesn't work. \
By carefully checking all possible factors, I found that it is because Chrome and other modern browser automatically blocks self-signed https packets. \
Therefore, users need to configure their browsers first before using the website. \
To ease the burden of user, I decided to disable the certificate and make the backend using Http API URL. And deploy the frontend on other platforms instead of Azure Static Web App

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

**All avialble APIs**:
```
type Query {
    allUsers: [User]
    allPhotos: [Photo]
    userByUsername(username: String!): User
    photoByTitle(title: String!): Photo
    login(username: String!, password: String!): User
}

type Mutation {
    addUser(username: String!, password: String!): User
    addPhoto(title: String!, description: String!, uploadedUser: String!, imgBase64: String!): Photo
    likePhoto(username: String!, photoTitle: String!): User
    unLikePhoto(username: String!, photoTitle: String!): User
    deletePhoto(username: String!, photoTitle: String!): Photo
}

type User {
    id: ID!
    username: String!
    photos: [String]
    likedPhotos: [String]
}

type Photo {
    id: ID!
    title: String!
    description: String
    uploadUser: String
    imgBase64: String
    likedUsers: [String]
}
```