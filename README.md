# Card Game
A GUI-based card game designed for an assignment in COMP2396: Object Oriented Programming & Java. 

### Rules
The computer simulates the dealer. When the game starts, 52 playing cards will be shuffled and you will be given $100. At each round of the game, both the player and the dealer will be given 3 cards, drawn from the top of the deck. Player will place his/her bet (the amount must be an integer). Then before the dealer opens the cards, the player has a chance to draw _ _at most_ _ two cards from the top of the deck, to replace any two of the cards on hand. Each card on hand can only be replaced once. Player will lose the bet if the dealer gets a better hand, and vice versa.

J, Q, K are considered as special cards.  
__Rule 1__: The one with more special cards wins.   
__Rule 2__: If both have the same number of special cards, add the face values of the other cared(s) and take the remainder after dividing the sum by 10. The one with a bigger remainder wins. (Note: Ace = 1)  
__Rule 3__: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.   

### Running the game
To run the game from an IDE:
- Navigate to CardGame/src/GambleGame.java  
- Compile and run GambleGame.java  

To run from the terminal, execute the following commands:
```
cd CardGame
cd src
javac *.java
java GambleGame
