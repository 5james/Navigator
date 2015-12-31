# Navigator
University project for Basics of Artificial Intelligence subject

##Update 1.0 - commit GUI 
####(2015-12-31)

###Added packages (with classes):

- view (View, ViewInterface, SettingsPanel, LogPanel, GraphPanel, MenuBar, ButtonAction, MenuAction)
- controller (Controller)
    
###Additional changes:

- class Node moved outside from class Graph (now it's `public`)
- class AStar - added no-argument constructor
- class Graph - added methods: getNodes() and getNodeAtPoint, removed class Node
- class Main - mvc-like structure
 
##How to use (basic)

- run program
- write in rate and number of nodes
- click 'Generate!' button
- click 'Start' button, then click on one of nodes (to make it starter point)
- click 'End' button, then click on one of nodes (to make it end point)
- click 'Solve!' button

More info at Menu -> Help


