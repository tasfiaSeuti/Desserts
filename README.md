# Desserts


The Recipe App is an Android app made using Android Studio. It is created so that users can create new account, sign in & out, learn new dessert recipes, according to theire cravings and taste and make them.

### Technical
1. activities folder contans activities i.e. log in activity, main activity after login, details activity after clicking on a specific item
2. adapters folder contains adapters for: database - directions - ingredients - main page - recipes
3. models folder contains classes to define a: database - direction - ingredient - recipe - user
4. networking folder contains code relatedd to network call/api call

### Programming Language : Kotlin

### Markup Language : XML

### Authentication: Firebase
  
### Api used

https://www.themealdb.com/api.php

### Endpoints utilized here:

1. https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert for fetching the list of meals in the Dessert category.
2. https://www.themealdb.com/api/json/v1/1/lookup.php?i=MEAL_ID for fetching the meal details by its ID.

### Description

The user is shown the list of desserts in the Main activity category, sorted alphabetically, with image of that dessert. When the user selects a dessert, a detailed view is shown of that selecteditem that includes:

● Dessert name
● Instructions
● Ingredients/measurements

### Exrtra Features

● Clicking on 'Watch On Youtube' button takes the user directly to a youtube tutorial of his/her selected dessert
● Clicking on 'Share Recipe' button allows the user to share the recipe via message, drive or mail
● Clicking on 'Source' button takes the user t the internet page that has insights of that recipe

## Screenshots

### Log In/ Sign Up page

![Screenshot 2022-08-27 at 10 18 42 PM](https://user-images.githubusercontent.com/31209824/187039184-67640cb9-13de-4b79-818d-143d0e612bf0.png)
![Screen![Screenshot 2022-08-27 at 10 19 33 PM](https://user-images.githubusercontent.com/31209824/187039211-edabb42f-4a86-4cb6-96a5-bf88ec0298ea.png)
shot 2022-08-27 at 10 18 57 PM](https://user-images.githubusercontent.com/31209824/187039191-9db7fbaf-0b53-4792-be90-1803e055fffe.png)

### Main Activity
![Screenshot 2022-08-27 at 10 23 33 PM](https://user-images.githubusercontent.com/31209824/187039226-dc822ac8-662d-403e-82ab-71fa2a752cc2.png)
![Screenshot 2022-08-27 at 10 23 49 PM](https://user-images.githubusercontent.com/31209824/187039230-fb8988e3-2814-4290-944e-282950922125.png)


### Detailed page
![Screenshot 2022-08-27 at 10 24 00 PM](https://user-images.githubusercontent.com/31209824/187039242-9c9a9017-b1b4-4a97-bead-12cf69730742.png)
![Screenshot 2022-08-27 at 10 24 12 PM](https://user-images.githubusercontent.com/31209824/187039248-5ad5f93d-d9c8-4c72-b012-9b58a6a844e2.png)
![Screenshot 2022-08-27 at 10 24 37 PM](https://user-images.githubusercontent.com/31209824/187039261-c7e703d4-663a-41fc-b671-f690db36de0d.png)
