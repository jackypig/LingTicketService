#Ling's Ticket Service
A ticket service management for a theatre.

##Running the application
1. mvn clean
2. mvn package
3. Change the directory to /target folder
4. run "**java -jar Walmart-ticket-service.jar**"

###Seat holding process
Start from the first row and first seat.


###Example
*****  Welcome to the Ling's ticket service for Walmart. *****
Please enter the number of rows in the theater:
5
Please enter the number of seats per row in the theater:
5

   1 2 3 4 5

A  O O O O O
B  O O O O O
C  O O O O O
D  O O O O O
E  O O O O O

O: Available   -: Held   X: Reserved

Please choose from the choices below.
1. Show all seats information.
2. Show the number of all available seats.
3. Hold seats.
4. Reserve seats.
5. Show reserved seats.
6. Exit the program.

3
Please enter your email address:
ling@walmart.com
Please select number of seats to hold:
6
You have successfully held [A1 A2 A3 A4 A5 B1], your seat hold ID is 1000.It will only hold for you for 10 seconds, please reserve before it expires.

Please choose from the choices below.
1. Show all seats information.
2. Show the number of all available seats.
3. Hold seats.
4. Reserve seats.
5. Show reserved seats.
6. Exit the program.

1

   1 2 3 4 5

A  - - - - -
B  - O O O O
C  O O O O O
D  O O O O O
E  O O O O O

O: Available   -: Held   X: Reserved

Please choose from the choices below.
1. Show all seats information.
2. Show the number of all available seats.
3. Hold seats.
4. Reserve seats.
5. Show reserved seats.
6. Exit the program.

4
Please enter your seat hold ID:
1000
Congratulations! You have successfully reserved [A1 A2 A3 A4 A5 B1], your confirmation code is d804cbbf-9f79-41a4-8dbf-4fdf202a6a41. Enjoy the show!

Please choose from the choices below.
1. Show all seats information.
2. Show the number of all available seats.
3. Hold seats.
4. Reserve seats.
5. Show reserved seats.
6. Exit the program.

1

   1 2 3 4 5

A  X X X X X
B  X O O O O
C  O O O O O
D  O O O O O
E  O O O O O

O: Available   -: Held   X: Reserved

Please choose from the choices below.
1. Show all seats information.
2. Show the number of all available seats.
3. Hold seats.
4. Reserve seats.
5. Show reserved seats.
6. Exit the program.

5
Please enter your confirmation code:
d804cbbf-9f79-41a4-8dbf-4fdf202a6a41
Your reserved seats are: A1 A2 A3 A4 A5 B1 . Enjoy your show!
