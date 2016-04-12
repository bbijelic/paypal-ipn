# Paypal IPN Listener Service
Instant Payment Notification (IPN) is a message service that automatically notifies merchants of events related to PayPal transactions. Merchants can use it to automate back-office and administrative functions, including automatically fulfilling orders and providing customers with order status.

## Prerequisites
* Java Runtime Environment 7
* MySQL database
* Maven

## Clone project
```
# git clone https://github.com/bbijelic/paypal-ipn.git
# cd paypal-ipn
# mvn package
```

## Database schema import
Execute following command to create user and database:
```
# mysql -u root -proot < schema/mysql.sql
```

## Running service
To run paypal-ipn service execute following command
```
# java -jar target/paypal-ipn-0.0.1.jar server target/config.yml
```

## TODO
* Prepare a Docker image for the service
