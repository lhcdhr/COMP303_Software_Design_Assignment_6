# WALL-E Robot
This is the final assignment of COMP303 Software Design.  
## Deliverables
Unit test is implemented to cover possible situations. The branch converage reached 100%.  
A demo is provided in driver.java, and it will show how the robot works.  
## Details of the Project
### Basic Actions
The robot have 6 basic actions, and these actions have some pre-conditions that needs to be fulfilled (in the sqaure brackets):  
1. move a certain distance （given by user）forward [arms should be retracted]  
2. turn 90 degree left or right [arms should be retracted]  
3. grab an object [arms should be retracted]  
4. release an object [arms should be retracted]  
5. compact an object [gripper should be holding an object]  
6. empty the compactor  
### Charging and Complex Actions  
The robot has a battery. When its charge is less than or equal to 5 units, it needs to recharge before performing actions. The charging can also be forced by the client.  
The client can design complex actions which contains series of basic actions.  
### Programs
The client can create a program that is composed of basic and/or complex actions action(s).  
Some computations can be done and data will be recorded. For example, the total number of compacted objects. The result of such calculations can be get before the execution of the program.  
Actions will not be executed if the current state of robot violates the pre-conditions.   
There is a logging system to generate log in order to keep track of the actions of the robot. The log can be saved to a file or print to the console。  

