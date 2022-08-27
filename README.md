# Desserts


The Recipe App is an Android app made using Android Studio. It is created so that users can create new account, sign in & out, learn new dessert recipes, according to theire cravings and taste and make them.

### Technical
1. activities folder contans activities i.e. log in activity, main activity after login, details activity after clicking on a specific item
2. adapters folder contains adapters for: database - directions - ingredients - main page - recipes
3. models folder contains classes to define a: database - direction - ingredient - recipe - user
4. networking folder contains code relatedd to network call/api call

### Programming Language : Kotlin

### Markup Language : XML
  
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
