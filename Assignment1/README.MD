# Instructions
Write a program that places 8 queens on an 8x8 board where none of the queens are in conflict with each other.  You 
are to implement the solution by using the Hill-Climbing algorithm with random restarts.

# Problem Overview & Algorithm Description
The 8-Queens problem requires that 8 queens be placed on a board with 8 rows and columns so that no queen occupies 
the same row, column or diagonal as another queen.  To solve this problem using the Hill-Climbing with random restart 
algorithm, we must first generate a random starting state which places a queen in a random row of each column.  From 
there, we first check to see if the state is a goal state (no queens are in conflict). If not, we evaluate all of the possible 
neighbor states by moving each column’s queen through the rows of its column and generating a heuristic value for 
each of those states.  When all of the neighbor states have been generated, we check to see if any states were 
generated that have a lower heuristic value than the current state.  If a better state was not found, then we have 
reached the local minima and must perform a random restart.  If a better (lower heuristic) state was found, then that 
state becomes the current state and the above process is repeated on that state. 
Remember:  your heuristic function is a representation of how close you are to the goal state.  Unlike Pathfinding 
heuristics, we are not evaluating how close a particular node is to the goal node, but rather how close the current state 
(overall configuration) is to the goal state

# Program Requirements
No graphics are required for this program.  Instead, use a series of 0s (empty) and 1s (queen) in a grid style to represent 
each state.  Every state generated should be output in this manner along with the current state’s heuristic, the number 
of neighboring states with lower heuristics, and the action taken (restart or generate neighbor state).  When a solution is 
reached, your program should display the number of restarts and the total number of state changes that have occurred.  
