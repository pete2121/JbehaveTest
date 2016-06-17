Scenario: User should login properly in Chesar

When user navigates to chesar
When user enters 'User_Name' as 'admin'
When user enters 'User_Password' as 'admin'
When user clicks on 'Login_Button'
Then user should see the 'Login_User' as 'Logged in as admin'