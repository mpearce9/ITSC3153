/**
 * Michael Pearce
 */

import java.util.*;
import java.lang.*;

public class Main {

    public static int row = 15; //number of rows
    public static int col = 15; //number of columns
    public static int [][] mainboard = new int [row][col]; //this will hold the main board
    public static Node [][] node = new Node[row][col];
    public static Node start;
    public static Node goal;
    public static void main(String[]args) {
        Driver myDriver = new Driver();
        myDriver.set10percent(mainboard);
        myDriver.writeToNode(mainboard, node);
        start = myDriver.getStart(node); //gets the start node
        goal = myDriver.getGoal(node); //gets the goal node
        Driver.getHeuristic(node, goal);
        myDriver.displayNode(node);
        myDriver.displayHeuristic(node);
        start.setG(0);
        start.setH(node[start.getRow()][start.getCol()].getH());
        start.setF();

        //copy board to display the path later
        Node [][] copy = new Node[row][col];

        for (int i=0; i < node.length; i++){
            for (int j=0; j < node[0].length; j++) {
                copy[i][j] = node[i][j];
            }
        }
        boolean continueSearching = true;
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();
        openList.add(start); //add start node to openlist
        System.out.println("Adding Node: " + start.toString()+" to the openList");
        while(continueSearching) {
            //remove node from openList
            Node n = openList.remove(0); //remove the first element
            System.out.println("\nRemoving node " + n.toString()+" from openList\n");
            //check if goal reached
            if(n.equals(goal)){
                System.out.println("Goal reached!. Path has been found");
                System.out.println("This is the path: ");
                continueSearching = false;
                while(!n.equals(start)){
                    System.out.print(n.getParent().toString());
                    n = n.getParent(); 
                    copy[n.getRow()][n.getCol()].setType(5);
                }
            }
            else {
                int newRow = n.getRow();
                int newCol = n.getCol();
                for(int i = newRow - 1; i <= newRow + 1; i++){
                    for(int j = newCol - 1; j <= newCol + 1; j++){
                        //look for neighbors
                        if((i >= 0 && i < row) && (j >= 0 && j < row) && (i != newRow || j != newCol) && (node[i][j].getType() != 1)){
                            Node newNode = new Node(i, j, 0); //Current node to get neighbors
                            System.out.println("Getting neighbors: " + newNode.toString());
                            newNode.setParent(n);
                            int newG = 10;
                            if(Math.abs(i - newRow) + Math.abs(j - newCol) == 2){
                                newG = 14;
                            }
                            newNode.setG(n.getG() + newG);
                            newNode.setH(node[start.getRow()][start.getCol()].getH());//assigns heuristic to node
                            newNode.setF();
                            if(Driver.checkinList(newNode, closedList) == null) { //Checks closed list
                                Node smallNode = Driver.checkinList(newNode, openList); //Checks the newNode against the openlist to see if it is present
                                if(smallNode == null){
                                    openList.add(newNode);
                                    System.out.println("\nAdding Node : " + newNode.toString()+" to List\n");
                                }
                                else {
                                    if(newNode.getG() < smallNode.getG()) {
                                        smallNode.setG(newNode.getG());
                                        smallNode.setParent(n);
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("No path could be found");
                        }
                    }
                }
                Driver.sort(openList); closedList.add(n);
            }
        }
        System.out.println("This is a grid layout where \"5\" represents the path\n");
        myDriver.displayNode(copy); //displays the grid with the path.
    }
}