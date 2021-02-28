# Coffee Machine
coffee machine service

# Technologies

JDK 1.8.0


# external jar

To parse the json file `json-simple-1.1’ Jar has been used.


# Build, Test and Run application 

run Main.java

provide the absolute path of test json file.

Existing test files are in test folder.


# Output
Output will be similar to below one.

Press D to disperse/ R to refill/ any other key to exit
D
Please enter the input file location
/Users/kamras/Downloads/sumedha/study_material/dunzo_project/src/test/coffeeMachineInput.json
hot_coffee is prepared.
green_tea cannot be prepared because green_mixture is not available.
black_tea is prepared.
hot_tea cannot be prepared because sugar_syrup is not sufficient.
Warning: ginger_syrup quantity = 40 is running low. Please refill.
Warning: sugar_syrup quantity = 0 is running low. Please refill.
Warning: tea_leaves_syrup quantity = 40 is running low. Please refill.
Press D to disperse/ R to refill/ any other key to exit
R
Enter Ingredient Name to refill : 
sugar_syrup
Enter Ingredient Quantity to refill : 
100
Quantity before refill: 0
Successfully Refilled sugar_syrup
Quantity after refill: 100
Do you want to Refill another item? Press 'Y' to continue or any other button to exit
N
Press D to disperse/ R to refill/ any other key to exit
E
Exiting.
THANK YOU !

# Assumption
Input is not valid if it has duplicate keys.






