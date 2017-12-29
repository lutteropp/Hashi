A Hashiwokakero game

To run the game under Linux, open a terminal in this folder, then type "java -jar Hashi.jar" (without the quotes) into the command line.
I don't have Windows or a Mac, but I guess there you only have to double-click the Hashi.jar file in order to run it.

Rules:
1. All nodes must form a connected graph.
2. Each node must have as exactly as many connections to other nodes as specified by its number.
3. Connection wires are straight-line only and can not cross each other.

Generator settings:
- Width: The width of the generated game grid.
- Height: The height of the generated game grid.
- Filling: Percentage of the grid area to be filled with nodes.
- Outer extension probability: Probability to create a new node during the generation process, as opposed to connecting two already existing nodes.

Controls:
- Press the ESC key to return to the main menu.
- Double-click on a node to fully connect it to all reachable neighbors.
- Click on a connection wire to remove it.
- To connect two nodes, first click on one node, then on the other node.

Copyright: Sarah Lutteropp (2017)
