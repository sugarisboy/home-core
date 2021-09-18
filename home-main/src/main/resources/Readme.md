start connection:
```json
{
    "conversationToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJnbGFnb2wiLCJleHAiOjE2Mjk3OTI0NTUsImlzcyI6InF1YXNhci1iYWNrZW5kIiwicGx0IjoieWFuZGV4c3RhdGlvbiIsInN1YiI6IjU0MjA3ODk2ODMwODA4MGYwMzEwIn0.9FuUpbfsfSDaglf8r-CDSxPeDfjuASvBku5L1CCYlZE"
}
```

____________________

send command:
```json
{
    "conversationToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJnbGFnb2wiLCJleHAiOjE2Mjk4MzEyNjgsImlzcyI6InF1YXNhci1iYWNrZW5kIiwicGx0IjoieWFuZGV4c3RhdGlvbiIsInN1YiI6IjU0MjA3ODk2ODMwODA4MGYwMzEwIn0.qFXa3uQ4NTYwq-oq8_qTrbudrFvRnZhzVBCNd924oJw",
    "id": "{{$guid}}",
    "sentTime": {{$timestamp}}000000000,
    "payload": {
	    "command" : "ping"
    }
}
```

Command list: https://documenter.getpostman.com/view/525400/SWLfd8et?version=latest#47df2ffc-0210-49f9-be6e-d4e8772392cc
