# Ling's Ticket Service
A ticket service management for a theatre.

## Running the application
1. mvn clean
2. mvn package
3. Change the directory to /target folder
4. run "**java -jar Walmart-ticket-service.jar**"

### Seat holding process
- The venue is divided into six sections as follows:

| 2 | 1 | 2 |  
| 4 | 3 | 4 |

- The ticket system will assign the seats in the order of section 1, 2, 3, then 4.
- To hold seats, you need to provide a valid email and the number of seats you like to hold. Once you hold some seats, you will be provided a seat hold ID, and you have to reserve it within 15 seconds, or they will be released.
- To reserve the seats, you need to provide the seat hold ID. Then you will get a confirmation code for your reservation.
- **If you forget your reserved seats, you can provide the confirmation code, and the system will show you where your seats are.**

### Example (Might not display properly)
*****  Welcome to the Ling's ticket service for Walmart. *****
Please enter the number of rows in the theater: 5  
Please enter the number of seats per row in the theater: 8       

    Stage       
==============  
O O O O O O O O   
O O O O O O O O  
O O O O O O O O   
O O O O O O O O   
O O O O O O O O 

O: Available  -: Held  X: Reserved

Please choose from the choices below.
1. Show me all seats information.
2. Show me the number of available seats.
3. I want to hold seats.
4. I want to reserve my seats.
5. I forget my reserved seats.
6. Leave.

3  
Please enter your email address: ling@walmart.com  
Please select number of seats to hold: 8  
You have successfully held seats 0-5,1-4,1-3,1-5,0-2,1-2,0-3,0-4
as shown below and your seat hold ID is 0. 
It will hold for you for **15 seconds**, please reserve before it expires.
   
     Stage  
================  
|- - X X X X - -|    
|- - X X X X - -|   
|- - - - - - - -|  
|- - - - - - - -|   
|- - - - - - - -|   

X: Your seats   -: Other seats

Please choose from the choices below.
1. Show me all seats information.
2. Show me the number of available seats.
3. I want to hold seats.
4. I want to reserve my seats.
5. I forget my reserved seats.
6. Leave.

4
Please enter your seat hold ID:0
Congratulations! You have successfully reserved 0-5,1-4,1-3,1-5,0-2,1-2,0-3,0-4
, your confirmation code is 8077. Enjoy the show!

Please choose from the choices below.
1. Show me all seats information.
2. Show me the number of available seats.
3. I want to hold seats.
4. I want to reserve my seats.
5. I forget my reserved seats.
6. Leave.

3  
Please enter your email address: ling2@walmart.com  
Please select number of seats to hold: 8  
You have successfully held seats 0-1,1-6,0-7,1-1,0-6,1-7,1-0,0-0 
as shown below and your seat hold ID is 1. 
It will hold for you for 15 seconds, please reserve before it expires.  
     
     Stage  
================  
|X X - - - - X X|   
|X X - - - - X X|   
|- - - - - - - -|   
|- - - - - - - -|  
|- - - - - - - -|   

X: Your seats   -: Other seats

Please choose from the choices below.
1. Show me all seats information.
2. Show me the number of available seats.
3. I want to hold seats.
4. I want to reserve my seats.
5. I forget my reserved seats.
6. Leave.

4  
Please enter your seat hold ID: 1  
Congratulations! You have successfully reserved 0-1,1-6,0-7,1-1,0-6,1-7,1-0,0-0
, your confirmation code is 1a71. Enjoy the show!

Please choose from the choices below.
1. Show me all seats information.
2. Show me the number of available seats.
3. I want to hold seats.
4. I want to reserve my seats.
5. I forget my reserved seats.
6. Leave.

5  
Please enter your confirmation code: 1a71
Your reserved seats are: 
0-1,1-6,0-7,1-1,0-6,1-7,1-0,0-0 as shown below. Enjoy your show!

     Stage
================  
|X X - - - - X X|   
|X X - - - - X X|   
|- - - - - - - -|  
|- - - - - - - -|  
|- - - - - - - -|  

X: Your seats   -: Other seats

Please choose from the choices below.
1. Show me all seats information.
2. Show me the number of available seats.
3. I want to hold seats.
4. I want to reserve my seats.
5. I forget my reserved seats.
6. Leave.

3  
Please enter your email address: ling3@walmart.com
Please select number of seats to hold: 12  
You have successfully held seats 3-5,4-4,4-3,4-5,2-2,3-2,2-5,4-2,2-4,3-3,3-4,2-3 as shown below, 
your seat hold ID is 2. It will hold for you for 15 seconds, please reserve before it expires.  
     
     Stage
================  
|- - - - - - - -|   
|- - - - - - - -|   
|- - X X X X - -|   
|- - X X X X - -|  
|- - X X X X - -|  

X: Your seats   -: Other seats

Please choose from the choices below.
1. Show me all seats information.
2. Show me the number of available seats.
3. I want to hold seats.
4. I want to reserve my seats.
5. I forget my reserved seats.
6. Leave.

4  
Please enter your seat hold ID: 2  
Congratulations! You have successfully reserved 3-5,4-4,4-3,4-5,2-2,3-2,2-5,4-2,2-4,3-3,3-4,2-3
, your confirmation code is 8943. Enjoy the show!  

Please choose from the choices below.
1. Show me all seats information.
2. Show me the number of available seats.
3. I want to hold seats.
4. I want to reserve my seats.
5. I forget my reserved seats.
6. Leave.

5
Please enter your confirmation code: 8943  
Your reserved seats are: 
3-5,4-4,4-3,4-5,2-2,3-2,2-5,4-2,2-4,3-3,3-4,2-3
. Enjoy your show!

     Stage
================  
|- - - - - - - -|  
|- - - - - - - -|  
|- - X X X X - -|  
|- - X X X X - -|  
|- - X X X X - -|  

X: Your seats   -: Other seats

Please choose from the choices below.
1. Show me all seats information.
2. Show me the number of available seats.
3. I want to hold seats.
4. I want to reserve my seats.
5. I forget my reserved seats.
6. Leave.

6
Bye~ Hope to see you again!
