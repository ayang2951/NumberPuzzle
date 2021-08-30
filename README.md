# NumberPuzzle
Data Structures Project 4 — "Wayfinder"

This project was created for the NYU Data Structures class with professor Joanna Klukowska. All code here was written by Audrey Yang.

Explanation of the project:

A number puzzle (with certain requirements) is given to the program through the command line. It consists of a list of integers bewteen 0 and 99. The game begins on the first number -- this is the position of the player. The number in the list that corresponds with the position of the player is the number of placements the player can move either forward or backward. The player may not move off the list of numbers at any point. The game is won when the player lands on the last number in the list, which is required to be a 0, and is lost when there is absolutely no way for the player to win. The objective of the program is to determine all solutions to this number puzzle using recursion.

Here is an example of the game:

If the list: "4 7 1 4 2 5 0" is fed through the command line, the number puzzle is a 7 position long puzzle with the respective allowed moves. The player, begninning at the number 4, can only move 4 places to the right, since moving to the left is invalid. The player then lands on the number 2. Here, to move backwards to the left is legitimate, but it results in no possible solution. Moving to the right 2 positions results in the player landing on the last number, a 0, which is a winning move.
