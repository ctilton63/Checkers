# Checkers
The game of checkers with a few rule adjustments. In order to run the code, please run project.java.
The Class main begins by drawing the initial game board. There are ten rows and ten columns with a black and white checkerboard pattern. Two arrays of Chips of length 10 are then initialized to represent the players chips and the computers chips. The Class Chips is explained further below. An array of arrays holding doubles (it should have been integers but I changed the program later on) called board is then initialized to keep track of which squares on the board are occupied by chips. If there is a chip at row three, column four, board [3][4] = 1. If there is no chip on that square, board[3][4] = 0. Main then draws all the chips on the board based on what their instance variables for x location (xL) and y location (yL). xL and yL are both integers between 0 and 9, inclusive. These numbers are also used to manage and manipulate board[][]. The instructions are then displayed.

Instructions:
	The player's (user's) chips are white, and the player will always make the first move. To make a move, the player must use their mouse to click on one of their chips. The possible new locations of that chip will appear in green. A chip can move up and to the right, up and to the left, down and to the right, or down and to the left, as long as there are no chips on the new squares. If there is a chip on one of these options but the square that is two squares away diagonally is free, the selected chip can skip over the other. If the other chip is one of the opposition's chips, that chip will "die" and disappear. After seeing the possible new locations of the selected chip, the player will then use the mouse to click on the green square where they want to move their chip. The computer will then randomly choose one of its chips and move it to one of the possible new locations for that chip, if the computer has the option to skip a chip, even if it is the computer's chip, it will do so; only the players chips will die if the computer skips them. The game ends when one of the players eliminates all of the other player'c chips. The player that still has chips is the winner. 

Players Move in main:
	After displaying the instructions, main runs a while loop that runs while both players still have chips. Within the while loop, there are while loops that wait for the player to click on one of their chips. When the player presses on a chip, the computer runs through the array of the players chips to see which chip was selected. When the program finds the chip that was selected, the options and move instance methods are run to move the selected chip to its new position; this will be discussed in depth below. Because selected chip was found, so the while loop waiting for a valid chip to be selected breaks. Still within the larger while loop, main iterates though all of the computers chips to count how many are still alive. If all of the computers chips have died and the player has won, the larger while loop will break. If not, the computer will calculate its move.

Computers Move in main:
	Within the larger while loop that runs while no one has won, main generates a random number between 0 and 9 to select one of the computers chips. If that chip is not alive or not mobile (instance method that returns if the chip can move), a new random number is generated and checked again. Main will then run the instance methods options and compOptions which will be discussed more in depth below. Options displays all the new location options of the selected chip, and compOptions will evaluate the options for that chip and randomly choose one of the options unless the computer has the ability to skip a chip in that turn. If the selected chip can skip another chip, even if it skips its own, it will do so; if the computer skips its own chip, that chip will remain alive in the game. Main then calculates how many chips each player has, and the conditions for the larger while loop are checked again. If one of the players has no more chips, the while loop breaks, and the game ends. Main then finds the winner by calculating how many chips each player has, and a message will display the results of the game.

Chips Class:
	Chips Constructor: Chips have four instance variables: a double for x location (xL, a double for y location (yL), a boolean for alive or dead (alive), and a boolean for player or computer (player).
	Draw instance method: Draws the chip based on its xL and yL if the chip is alive.
	Mobile instance method: Mobile runs through all the possible new locations of a selected chip and returns true if there is at least one square that is open and that the chip can move to.
	CompOptions instance method: CompOptions takes in all three of the arrays that were initialized in main (player, comp, and board), and it does not return anything. The main purpose of this instance method is to generate a move for the computer. The method, checks all the possible new locations to see if they are free spaces, and then randomly generates a number between 0 and 3 inclusive to decide which new location option the computer should choose. The method will also see if there is an option to skip a chip with the computers move. If the chip can skip, the new location will be chosen from the four further options for movement (the squares that are two squares away diagonally). Once the computer generates a random number that matches the number assigned to a valid move, that specific move will run, the old square will be set to 0 in board[][], and the new square will be set to 1. If the opponents chip is skipped, that square will also go to 0 in board[][], and the skipped chip will die. The method then redraws all chips in their new locations and states.
	Options instance method: The options instance method takes in all the arrays initialized in main and does not return anything. However, the method does run through all the new possible locations of a chip and colors in the squares representing those new locations in green. This method is run for both the players chips and the computers chips.
	Move instance method: The move instance method takes in all the arrays initialized in main, and does not return anything. Move is responsible for finding out which new location the player selected and moving the selected chip to that new location. There are while loops that wait for the player to select one of the green option squares, and once the mouse is clicked, the x and y locations of the mouse click will be compared with each of the new location options. If the mouse click is within one of the new location options, the old square will be set to 0 in board[][], and the new square will be set to 1. The x and y locations of the selected chip will also be changed to represent the chips new location. if the player skips one of the computers chips, the computer chip will die and that chips spot on the board will be set to 0 in board[][].
	dieChip instance method: The instance method dieChip sets the boolean alive to false for a chip. 
