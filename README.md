# Insurance Company

## Run the application
Use Java 17 and run
**./gradlew bootRun**

##Use the application
In a HTTP client(postman or curl) make a POST call to http://localhost:8080/compute-cost 
###Body:
```yaml
{
  "number": "aaa",
  "status": "APPROVED",
  "objects": [
    {
      "name": "obj",
      "subObjects": [
        {
          "name": "subObj",
          "cost": 12,
          "riskType": "FIRE"
        },
        {
          "name": "subObj2",
          "cost": 488,
          "riskType": "FIRE"
        },
        {
          "name": "subObj3",
          "cost": 102.51,
          "riskType": "THEFT"
        }
      ]
    }
  ]
}
```

##CURL:
```shell
curl --location --request POST 'localhost:8080/compute-cost' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number": "aaa",
    "status": "APPROVED",
    "objects": [
        {
            "name": "aaa",
            "subObjects": [
                {
                    "name": "bb",
                    "cost": 12,
                    "riskType": "FIRE"
                },
                {
                    "name": "bb",
                    "cost": 488,
                    "riskType": "FIRE"
                },
                {
                    "name": "bb",
                    "cost": 102.51,
                    "riskType": "THEFT"
                }
            ]
        }
    ]
}'
```
## Entry point
The entry point of the application is **/compute-cost** endpoint 

