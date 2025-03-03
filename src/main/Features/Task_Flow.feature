@Regression
  Feature: As Admin, I want to add user then delete it to make sure records are counted correctly

    Scenario: Verify records number is calculated correctly when adding and deleting user.

      When user logs-in with valid username and valid password
#      And enters valid password
#      And clicks login
      And clicks "Admin" link in nav menu
      And get records number
      And Click "Add" button
      And Opens "User Role" DDL
      And Select "Admin" option
      And Write employee name "Charles Carter"
      And Choose "Charles Carter" option
      And Opens "Status" DDL
      And Choose status option
      And Enter username
      And Enter password
      And Enter password confirmation
      And Click "Save" button
      And get records number
      Then records number is increased by one
      When filters with username
      And Deletes user
      And click "yes,delete" button
      And get records number
      Then records number is decreased by one