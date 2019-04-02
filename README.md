# crypto-currency-pricing
Spring Boot + Angular application to get the most profitable daily transaction

Tech stack:

  Client: Angular 7, Angular Material, Bootstrap
  
  Server: Spring Boot, Spring Data, H2


Getting started

Requirement:

  Node.js, Visual Studio Code or other editor, Eclipse IDE


Environment setup:   

  Back-end service:
  
    1. import 'server' folder as existing maven project in Eclipse IDE
    
    2. run mvn clean package
    
    3. in Eclipse, open CryptoCurrencyPricingApplication.java and run as Spring Boot App
    
    4: verify url services:
    
      http://localhost:8080/historical/currencies - getting unique currencies
      
      http://localhost:8080/historical/{currency}/dates - getting unique transaction dates for the specified currency
      
      http://localhost:8080/historical/{currency}/{date} - getting daily transaction for the specified currency
      
      http://localhost:8080/historical/{currency}/{date}/principal - getting the most profitable transaction for the specified currency
      
     
  Client app:
  
    1. open 'client' folder in Visual Studio Code
    
    2. run npm install
    
    3. run ng serve
    
    4. open the page in browser using the url http://localhost:4200


Notes:

   1. Sample data is inserted in H2 database via file \server\src\main\resources\data.sql
   
