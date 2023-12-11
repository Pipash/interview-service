

Interview Service


## Get request
- url http://localhost:8862/interview-service/api/interview/booking-list

## Post request
- http://localhost:8862/interview-service/api/interview/book-slot
## Request Body
```JSON
{
  "applicantName": "someone",
  "applicantEmail": "abc@abc.com",
  "interviewTime": "2023-12-11 08:00"
}

```
## Response
```JSON
{
  "data": {
    "createdOn": "2023-12-11T13:50:04.219592",
    "updatedOn": "2023-12-11T13:50:04.219634",
    "id": 1,
    "applicantName": "someone",
    "applicantEmail": "abc@abc.com",
    "interviewTime": "2023-12-11T08:00:00"
  },
  "message": "created"
}					

```

## Error Response
```JSON
{
  "timestamp": "2023-12-11T07:50:50.167+00:00",
  "status": 400,
  "error": "Bad Request",
  "path": "/interview-service/api/interview/book-slot"
}				

```

## Get request
- http://localhost:8862/interview-service/api/interview/view-booking/1
## Response
```JSON
{
  "data": {
    "createdOn": "2023-12-11T13:50:04.219592",
    "updatedOn": "2023-12-11T13:50:04.219634",
    "id": 1,
    "applicantName": "someone",
    "applicantEmail": "abc@abc.com",
    "interviewTime": "2023-12-11T08:00:00"
  },
  "message": "single data"
}				

```

## Put request
- http://localhost:8862/interview-service/api/interview/edit-booking/1
- ## Request Body
```JSON
{
  "applicantName": "someone2",
  "applicantEmail": "abc@abc.com",
  "interviewTime": "2023-12-11 08:00"
}

```
## Response
```JSON
{
  "data": {
    "createdOn": "2023-12-11T13:50:04.219592",
    "updatedOn": "2023-12-11T13:50:04.219634",
    "id": 1,
    "applicantName": "someone2",
    "applicantEmail": "abc@abc.com",
    "interviewTime": "2023-12-11T08:00:00"
  },
  "message": "update data"
}			

```

## Delete request
http://localhost:8862/interview-service/api/interview/delete-booking/1
## Response
```JSON
{
  "data": null,
  "message": "data deleted"
}		

```

## Deployment

- run mvn clean compile install
- run the docker file
- port is 8862
