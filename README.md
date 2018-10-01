# Shopper

  - SpringBoot Rest API
  
  - H2 database used in test & runtime

####starts the app with dummy data:
```java
gradle bootRun
```  

####cart at checkout with discounts applied:  
```java
curl http://localhost:8080/carts/7/checkout

```   
####cart contents
```json
{
    "lineItemList": [
        {
            "product": {
                "itemName": "Speakers",
                "type": "AUDIO",
                "price": 85
            },
            "number": 1,
            "discountTotal": 59.5,
            "total": 85
        },
        {
            "product": {
                "itemName": "AAA Batteries",
                "type": "POWER",
                "price": 0.85
            },
            "number": 5,
            "discountTotal": 3.4,
            "total": 4.25
        },
        {
            "product": {
                "itemName": "Protein Bars (Box)",
                "type": "FOOD",
                "price": 25
            },
            "number": 2,
            "discountTotal": 0,
            "total": 50
        }
    ]
}
```
     
 
