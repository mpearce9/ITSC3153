# Instructions
Write a program that implements the A* algorithm to find a path from any two given nodes.  You may use any of the 
following languages: C++, C#, Java, ActionScript.

# Problem Overview & Algorithm Description
In a fully-observable environment where there are both pathable and blocked nodes, an agent must find a good path 
from their starting node to the goal node.  The agent must use the A* algorithm to determine its path.  For this program, 
you must use the Manhattan method for calculating the heuristic.
Remember:  your heuristic function is a representation of how good or close you are to the goal state.  

# Program Requirements
No graphics are required for this program but using them will help you with debugging and problem solving.  Your 
environment should be a 15x15 tile-based world that randomly generates nodes that are unpathable (blocks) in 10% of 
the nodes.  This should be done each time the program compiles ensuring that there are different environment makeups 
each run.  The program should display the generated environment when the program runs, and should allow the user to 
select a starting node and goal node.  This can be done via text input into the console or with a GUI.  Once the start and 
goal nodes have been defined, the program should run the A* algorithm to find a path.  The path should be displayed 
(series of [x,y] nodes, highlighting nodes, or actually moving the agent) if one exists, or a message indicating that a path 
could not be found.  The user should be able to continue specifying starting and goal nodes after paths have been found.
