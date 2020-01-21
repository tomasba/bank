# Money transfer

sample urls:

curl -X GET http://localhost:8080/accounts 

curl -X GET http://localhost:8080/transactions

curl -X POST \
  http://localhost:8080/accounts/ \
  -H 'Accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{"iban":"44444444444","currency":"USD","balance":1000.00}'
  
curl -X POST \
  http://localhost:8080/payments/ \
  -H 'Accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
	"fromIban" : 111,
	"toIban" : 222,
	"amount" : 45.60}'
  
curl -X PUT \
  http://localhost:8080/payments/7080cdbf-2e43-442b-a5e2-b3eab3499f58 \
  -H 'Accept: */*' \
  -H 'Content-Type: application/json' \
  
  
  
