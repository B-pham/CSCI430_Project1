Project is located in Jake Paschke’s CourseFiles, in the folder labeled Proj2Stage1.

MAIN FILES

NewUI.java: The entry point of the program. 
This is where you will find the main() method of the program and UI code. 
The user interaction is handled there but the operations are proxied to the Warehouse system.

HOW TO COMPILE THE PROGRAM

The program does not require additional files to compile. 
Compile the NewUI.java file and the other files should be linked automatically.

>> javac NewUI.java

Once the compilation is terminated, you could run compiled NewUI.class to start the program. 
At start, the program will display a list of options for starting an operation.

The menus and submenus have a similar structure. 
They should be self-explanatory to navigate, choosing your desired option. 
Option 0 usually takes you to the upper-level menu options. 
In the program’s current state, we can only view the menus for each type of user. 
We can also move down the user rankings to view the client menu as a clerk, for example.

To login as a client, you must input a client id. 
>> We have populated a test client with id “123”. <<

We tried to be as explicit as possible in the logs to help user navigate through the program.

If the class files are added to the submission, they may prevent the program to run if the compilation does not replace them. 
So in case it fails, that may be one issue.