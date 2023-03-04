# fetchward

#Endpoint: Process Receipts

.Path: /receipts/process

.Method: POST

.Payload: Receipt JSON

.Response: JSON containing an id for the receipt


curl --header "Content-Type: application/json" --request POST --data 

{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}

Example Response:

{ "id": "7fb1377b-b223-49d9-a31a-5a02701dd310" }


#Endpoint: Get Points

.Path: /receipts/{id}/points

.Method: GET

.Response: A JSON object containing the number of points awarded. 

.request GET http://localhost:8081/receipts/{id}/points
