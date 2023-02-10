<img src="https://www.igtb.com/wp-content/uploads/2018/04/igtb-logo-black.svg" width="48">

#  IGTB Assessment On Notification Dispatcher and SQL Problem with suggested techniques

This is a Notification Dispatcher application built using Java 8 and Spring Boot version 2.7.8. The application is designed to send notifications to users through multiple channels including Email, SMS, and WhatsApp.

## Features

#####Notification Dispatcher:

- Send notifications through multiple channels including email, SMS, and WhatsApp
- include a notification message
- include list of recipients for the notification based on channel

## Authorization:

- Basic authorization is used on the request headers to secure the application
- <b>username</b>: <span style="color:MediumSeaGreen;">iGTBUser</span> <b>password</b>: <span style="color:MediumSeaGreen;">iGTBPassword</span>  will allow you to access the application
- if postman is used, please use above credentials with Authorization type: Basic Auth 

## Sample request
##### end-point: /notifications
##### verb: POST
##### request body: 
###### for channel: Email
```json 
{
	"message": "dd",
	"recipients": ["igtb@igtb.com"],
	"channel": "Email"
}
```
###### for channel: SMS/Whatsapp
```json 
{
	"message": "dd",
	"recipients": ["+919393929393, "+31294084933"],
	"channel": "SMS"
}
```
##### Validations:
- none of the field in the request is expected to be empty
- recipients field in the request is expected to contain at least one item in it's list
- Channel is an enum but made to allow case insensitive values (the application properties is set with spring.jackson.mapper.ACCEPT_CASE_INSENSITIVE_ENUMS = true)
- if 'Email' channel used then the recipient list should contain proper email format
- if 'SMS' channel used then the recipient list should contain numbers(starts with +, minimum 1 digit, maximum 	  	  15 digits)
- if 'Whatsapp' channel used then the recipient list should contain numbers(starts with +, minimum 1 digit, maximum 	  	  15 digits)
- the request is custom validated(springframework) against the channel and recipient list
- custom validation message is returned if the request is wrongly framed


## Technical Details
The application is built using Java 8 and Spring Boot version 2.7.8. It uses Junit4, MockitTo/SpringRunner for testing and has been tested to ensure reliability and stability. 
<br>Code coverage is currently 90.9%, the channel adapters for Email, SMS, Whatsapp are missing code coverage as there are no actual implementation required.

## Installation and Execution
- clone the repository (visibility is public)

```sh 
$ git clone https://github.com/aprakash2704/notification-dispatcher.git
```
- import the project notification-dispatcher to eclipse/intelliJ, build the project with maven
- Running NotificationDispatcherApplication.java as java application should start the spring boot server, now the port would by default listening at 8080 for this application
- http://localhost:8080/notifications should work now, please use 'POST' verb with the endpoint
- setup the authorization with Basic Auth mentioned with details provided in Authorization section above

## SQL Problem on customers and accounts table

- Please refer to Top3HighNetCustomersQuery.sql under src/main/resources folder
- the sql file contains the query to solve the problem
- the sql file contains a section with suggested techniques for query performance