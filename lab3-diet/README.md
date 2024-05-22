# OOP Lab 3 - Diet and Restaurants

(The Italian version is available in file [README_it.md](README_it.md)).


Write an application to manage a diet by means of nutritional values computation.

The application must allow the definition of raw materials, their use as ingredients for recipes, the definition of products and menus.

All the classes must be in the package `diet`.


## R1 - Raw Materials

The system works though the facade class Food.

To define a raw material, we can use the method `defineRawMaterial()` that accepts as arguments the name, the kilo-calories, the amount of proteins, carbohydrates (carbs), and fat; all the values refer to 100 grams of raw material. The name of the raw material can be considered unique.

To retrieve some information about the raw materials we can use the method `rawMaterials()` which returns a list of raw materials, sorted by name in alphabetic order. To get info about a specific material, we can use the method `getRawMaterial()` that accepts the name of the raw material and returns the corresponding raw material.

The raw materials returned by the above methods are objects implementing the interface `NutritionalElement`, which provides the getter methods `getName()`, `getCalories()`, `getProteins()`, `getCarbs()`, `getFat()`. Calories are expressed in KCal, while proteins, carbs, and fat are expressed in grams.

Moreover, the interface includes the method `per100g()` that indicates whether the values refer to 100 grams of nutritional element or represent an absolute value. For raw materials the nutritional values are always expressed per 100 grams, therefore the method returns true.


## R2 - Products

The diet may include also pre-packaged products (e.g. an ice cream or a snack). Products are defined by means of the method `defineProduct()` of class `Food` accepting as arguments the name, the kilo-calories, the amount of proteins, carbohydrates (carbs), and fat. Such values express the value for the whole product, therefore the method `per100g()` returns false. The name of the product can be considered unique.

To retrieve information about the products we can use the method `products()` of class Food that returns a collection of products sorted by name. To get information about a specific product, method `getProduct()` is available that accepts the name of the product and returns the corresponding object.

Both methods return the products as object implementing the interface `NutritionalElement` (described in the previous requirement); the values are expressed for the whole product (i.e. the method `per100g()` returns false).


## R3 - Recipes

Raw materials can be combined as ingredients of recipes. To define a recipe we can use the method `createRecipe()`, from class `Food`, that accepts as argument the name of the recipe. The name of the recipe can be considered unique.

A recipe is represented by an object of class `Recipe` that allows adding new ingredients by means of its method `addIngredient()` accepting as arguments the name of the raw material and the relative amount in grams.

Class `Recipe` implements the interface `NutritionalElement` and the values are expressed per 100 grams.

To retrieve the information about the recipes we can use the method `recipes()`, of class Food, that returns a collection of recipes sorted by name. To get information regarding a specific recipe we can use the method `getRecipe()` that accepts as argument the name of the recipe and return the corresponding recipe. Both methods return recipes as `NutritionalElement`.

Warning: While the sum of the amounts of ingredients (in grams) of a recipe is not necessarily equal to 100g, the nutritional values of the recipe must refer to an ideal portion of 100 grams.


## R4 - Menu

A menu consists of either portions of recipes or pre-packaged products.

Menus can be created with method `createMenu()` of class `Food`, that accepts as argument the name of the menu.

A menu is represented by class `Menu` that allows to add a portion of a recipe to the menu through method `addRecipe()` that accepts as argument the name of the recipe and the size of the portion, in grams.

To add an item of a pre-packaged product, class Menu provides the method `addProduct()` that accepts as argument the name of the product.

Class Menu implements the NutritionalElement interface; in this case the values are referred to the whole menu.

---- 
**Additional requirements**


## R5 - Restaurant

The management of restaurants is performed through the class `Takeaway` that represents a restaurant chain.
The constructor requires a reference to a `Food` object so that all the ingredients, products, and recipes can be defined once for all.

Restaurants can be created using the method `addRestaurant()` that accepts the name of the restaurant and returns a `Restaurant` object. Getter `getName()` returns a restaurant's name. The method `restaurants()` of class `Takeaway` returns the names of the registered restaurants.

Through the method `setHours()` working hours can be set for the restaurant. The method accepts an array of strings (with even number of elements) in the format `"HH:MM"`, so that the closing hours follow the opening hours (e.g., for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00, arguments would be `"08:15", "14:00", "19:00", "00:00"`).
It is possible to know whether a restaurant is open at a given time using the method `isOpenAt()` that accepts a time (with the previous format).

Restaurants offer different menus, which can be added to the list of menus offered by a restaurant with the method `addMenu()` accepting as argument the `Menu` object. Such menus are linked to the restaurant and can be later used to make orders. It is possible to retrieve a menu of a restaurant given its nane using the method `getMenu()`.


## R6 - Customers

A customer is defined by providing their first name, last name, email and phone number to the method `registerCustomer()` that returns a User object. Getters are provided for all the fields (`getFirstName()`, `getLastName()`, `getEmail()`, `getPhone()`), while setters are provided for the email and phone number only (`setEmail()`, `setPhone()`). The string representation of a `User` object returns the first name separated by a space and followed by the last name.

To retrieve information about the customers we can use the method `customers()` of class `Takeaway` that returns a collection of customers sorted by their last name and first name.


## R7 - Orders

A registered customer can make an order at one of the available restaurants. For such purpose method `createOrder()` of class `Takeaway` accepts as arguments the `User` object making the order, restaurant's name, and the desired delivery time (with the same format as in [R5](#r5-restaurant)). Furthermore, if for the given order delivery time is outside the working hours for the restaurant, delivery time is set to the first successive opening hour (e.g., making an order for a restaurant having working hours from *8:15* until *14:00* and from *19:00* until *00:00*, and asking for a delivery at 15:30, would result in having the delivery time set for *19:00*).

An order can have three statuses: `ORDERED`, `READY`, `DELIVERED` accessible through setter and getter `setStatus()` and `getStatus()` (`ORDERED` by default). Furthermore, payment type for an order can be: `PAID`, `CASH`, `CARD`, accessible through setter and getter `setPaymentMethod()` and `getPaymentMethod()` (`CASH` by default).

Orders are made up of a set of menus with the relative quantity.
Menus can be added to an order by calling the method `addMenus()` and specifying the menu name and the quantity.

When an order is printed to a string, it should be formatted like:

```
"RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : (DELIVERY_HH:MM):
	MENU_NAME_1->MENU_QUANTITY_1
	...
	MENU_NAME_k->MENU_QUANTITY_k
"
```

The menu names are sorted alphabetically and are printed on different lines, each preceded by a tab (`'\t'`).

Warning: If a certain menu has been previously added to an order, using it again as an argument in `addMenus()`, should overwrite the previously defined quantity


## R8 - Information

To retrieve some information about the restaurants we can use the method `openRestaurants()` that has one string argument with the format "`HH:MM`" and returns a collection of `Restaurant`s that are opened at the given time, sorted by their name alphabetically. A restaurant is opened if there is at least one working hour segment such that the defined time is inside the range `[open, close)`.

Information about certain orders for a restaurant can be obtained through method `ordersWithStatus()` of the class `Restaurant`. Such method returns a `String` obtained by concatenating all orders satisfying the criteria.
E.g.

```
Napoli, Judi Dench : (19:00):
	M6->1
Napoli, Ralph Fiennes : (19:00):
	M1->2
	M6->1
```

The list is sorted by name of restaurant, name of the customer, and delivery time.

---

Version 1.0.2 - 2024-04-04
