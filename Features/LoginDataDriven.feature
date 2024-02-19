Feature: Login Data Driven Test 

Scenario Outline: Successful Login with Valid Credentials 
	Given User Launch Chrome browser 
	When User opens URL "http://admin-demo.nopcommerce.com/login" 
	And User enters Email as "<email>" and Password as "<password>" 
	And Click on Login 
	Then Page Title should be "Dashboard / nopCommerce administration" 
	When User click on Log out link 
	Then Page Title should be "Your store. Login" 
	And close browser 
	
	Examples: 
		| email | password | 
		| admin@yourstore.com | admin | 
		| Rahulkumar2020@gmail.com | Abcde@22222 | 
		| AmanRathore4040@gmail.com | Abcde@44444 |
