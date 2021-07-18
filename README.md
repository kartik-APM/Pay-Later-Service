# Pay-Later-Service
**_Pay Later Service(Virtual Credit Card)_**

**API Contracts**

```
Mapping: Post
URL: http://localhost:8080/users
Input JSON:
{
"firstName":"Kartik",
"lastName":"Panday",
"emailId":"abc@xyz.com",
"phoneNumber":"12345",
"address":{
"street":"Sonia",
"city":"VNS",
"state":"U. P.",
"pin":"224152"
}
}
Result:
{
"userId": 1,
"firstName": "Kartik",
"lastName": "Panday",
"emailId": "abc@xyz.com",
"phoneNumber": "12345",
"address": {
"street": "Sonia",
"city": "VNS",
"state": "U. P.",
"pin": "224152"
},
"account": null
}


Mapping: Get
URL: http://localhost:8080/users
Result: Returns all users in the system.


Mapping: Get
URL: http://localhost:8080/users/{id}
Result: Returns the specified user.


Mapping: Get
URL: http://localhost:8080/users/firstName{id}
Result: Returns the specified user.


Mapping: Get
URL: http://localhost:8080/users/lastName/{id}
Result: Returns the specified user.


Mapping: Get
URL: http://localhost:8080/users/emailId/{id}
Result: Returns the specified user.


Mapping: Put
URL: http://localhost:8080/users/{id}
Input JSON:
{
"userId": "{id}",		       //Throws Error
"firstName": "Karan",
"lastName": "Panday",
"emailId": "abc2dgf@xyz.com",   //Won’t be updated as for emailId, updatable = false.
"phoneNumber": "7309376077",
}
Result: Update the updateable fields corresponding to the user.


Mapping: Put
URL: http://localhost:8080/users/{id}/address
Input JSON:
{
"phoneNumber":"9898964",
"address":{
"street":"BHU",
"city":"Varanasi",
"state":"D&D",
"pin":"221001"
}
}
Result: Update the updateable fields corresponding to the user Address.


Mapping: Post
URL: http://localhost:8080/users/{id}/accounts
Input JSON:
{
"authorisedCreditLimit":"20000",		//Just Pass this parameter
}
Result: Add account details corresponding to the userId {id}.


Mapping: Post
URL: http://localhost:8080/users/{id}/accounts/{id2}/debit
Input JSON:
{
"amount":18000,
"orderId":"3234567"		//Unique Order Id
}
Result: Add Debit Transaction details corresponding to the accountId {id2}.


Mapping: Get
URL: http://localhost:8080/users/{id}/accounts/{id2}/debit
Result:  Returns the debit Transaction details of the specified user for its specified account.


Mapping: Post
URL: http://localhost:8080/users/{id}/accounts/{id2}/refund
Input JSON:
{
"amount":18000,
"originalTransactionId":"3234567"   //An Id which already exist in either debit or repayment table
}
Result: Add refund details corresponding to the accountId {id2}.