// Michael Pearce
// Assignment 1
import java.util.ArrayList;
import java.util.Collections;

public class Assignment1{
    
    public static int boardSize = 8;
    public static void printBoard(){};
    public static void checkState(){};
    public static void createH(){};
  
    public static void printBoard(ArrayList<ArrayList<Integer>> board){
        //Allows for easy print formatting of the boards
        for (int y = 0; y < boardSize; y++){
            for (int x = 0; x < boardSize; x++){
                System.out.print(board.get(y).get(x).toString());
                if (x != boardSize - 1){
                    System.out.print(" , ");
                }
                else {
                    System.out.println("");
                }
            }
        }
    }

    public static Boolean checkState(ArrayList<ArrayList<Integer>> board){
        for(int y = 0; y < boardSize; y++){
            for(int x = 0; x < boardSize; x++){
                if(board.get(y).get(x) == 1){
                    //loop through column
                    for(int i = 0;i < boardSize;i++){
                        if(x != i ){
                            if(board.get(y).get(i) == 1){
                                return false;
                            }
                        }
                    }
                    //loop through row of queen
                    for(int j = 0; j < boardSize; j++){
                        if(y != j && board.get(j).get(x) == 1){
                            return false;
                        }
                    }
                    int tempX = x;
                    int tempY = y;
                    // highest point of left diagonal
                    if (tempX > tempY){
                        tempX -= tempY;
                        tempY = 0;
                    }
                    else {
                        tempY -= tempX;
                        tempX = 0;
                    }
                    while(true){
                        if(board.get(tempY).get(tempX) == 1){
                            if(tempY != y && tempX != x){
                                return false;
                            }
                        }
                        tempX += 1;
                        tempY += 1;
                        if (tempX < 0 || tempX > boardSize - 1 || tempY < 0  || tempY > boardSize - 1){
                            break;
                        }
                    }
                    tempX = x;
                    tempY = y;
                    //highest point right diagonal
                    if (tempX + tempY <= boardSize){
                        tempX += tempY;
                        tempY = 0;
                    }
                    else {
                        tempY -= boardSize - 1 - tempX;
                        tempX = boardSize - 1;
                    }
                    while(true){ 
                        if(tempX < 0 || tempX > boardSize - 1 || tempY < 0  || tempY > boardSize - 1 ){
                            break;
                        }
                        if(board.get(tempY).get(tempX) == 1 && tempY != y && tempX != x){
                            return false;
                        }
                        tempX -= 1;
                        tempY += 1;
                    }
                }
            }
        }
        return true;
    }
    /**
    * Recieves a board of queens, queen in each row
    * Returns a heuristic that counts the number of collisions
    */
    public static int createH(ArrayList<ArrayList<Integer>> board){
        // loop through every space on board
        int numCollisions = 0;
        for(int x = 0; x < boardSize ; x++){
            for(int y = 0; y < boardSize; y++){
                if(board.get(y).get(x) == 1){
                    // loop through column
                    for(int i = x + 1; i < boardSize; i++){
                        if(board.get(y).get(i) == 1){
                            numCollisions += 1;
                        }
                    }
                    // loop through row
                    for(int i = y + 1; i < boardSize; i++){
                        if(board.get(i).get(x) == 1){
                            numCollisions += 1;
                        }
                    }
                    // left diagonal collision
                    int tempX = x;
                    int tempY = y;
                    while(true){
                        tempX += 1;
                        tempY += 1;
                            if(tempX > boardSize - 1 || tempY > boardSize - 1 ){
                                break;
                            }
                            if(board.get(tempY).get(tempX) == 1){
                                numCollisions += 1;
                            }
                    }
                    // right diagonal collision
                    tempX = x;
                    tempY = y;
                    while(true){
                        tempX += 1;
                        tempY -= 1;
                        if(tempY < 0 || tempX > boardSize - 1 ){
                            break;
                        }
                        if(board.get(tempY).get(tempX) == 1){
                            numCollisions += 1;
                        }
                    }
                }
            }
        }
    return numCollisions;
    }
    public static void main(String[] args){

        ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>(); // Declare array list for the board
        ArrayList<ArrayList<Integer>> valueBoard = new ArrayList<ArrayList<Integer>>(); // Declare array list for heuristic board
        ArrayList<ArrayList<Integer>> tempBoard = new ArrayList<ArrayList<Integer>>(); // Declare array list for a temporary board

        //Initializes 2d arraylists
        for (int a = 0; a < boardSize; a++){
            board.add(new ArrayList<Integer>());
            valueBoard.add(new ArrayList<Integer>());
            tempBoard.add(new ArrayList<Integer>());
            for (int b = 0;b < boardSize; b++){
                board.get(a).add(0);
                valueBoard.get(a).add(0);
                tempBoard.get(a).add(0);
            }
        }
        // randomly add queens to a column
        for (int a = 0; a < boardSize; a++){
            board.get((int)(Math.random() * boardSize)).add(a, 1);
        }
        boolean isRestart; // determines whether the board gets stuck and needs to restart
        int stateChange = 0; //counts number of state changes
        int restarts = 0; //counts # of restarts
        int numStates = 0; //counts number of lower neighbor states
        int heuristicValue = 0; //holds current board heuristic value
        int pastHValue; //holds lowest Heuristic value
        int lowPosition[] = new int[2]; //holds the x and y values of first position on the board with the pastHValue value  

        while (!checkState(board)) {
            if (numStates == 0){
                board = new ArrayList<ArrayList<Integer>>(); 
                valueBoard = new ArrayList<ArrayList<Integer>>();
                tempBoard = new ArrayList<ArrayList<Integer>>();
                for (int a = 0; a < boardSize; a++){
                    board.add(new ArrayList<Integer>());
                    valueBoard.add(new ArrayList<Integer>());
                    tempBoard.add(new ArrayList<Integer>());
                    for (int b = 0;b < boardSize; b++){
                        board.get(a).add(0);
                        valueBoard.get(a).add(0);
                        tempBoard.get(a).add(0);
                    }
                }
                for (int a = 0; a < boardSize; a++){
                    board.get((int)(Math.random() * boardSize)).add(a, 1);
                }
                heuristicValue = createH(board);
            }
            else {
                numStates = 0;
            }
            pastHValue = heuristicValue;

            System.out.println("Current h: " + heuristicValue);
            System.out.println("Current State");
            printBoard(board);

            for (int x = 0; x < boardSize; x++){
                int queenIndex = 0;
                for(int y = 0; y < boardSize; y++){
                    if(board.get(y).get(x) == 1){
                        queenIndex = y;
                    }
                }
                //create a copy of the board
                for(int b = 0 ; b < boardSize;b++){
                    for(int c = 0 ; c < boardSize;c++){
                        int e = board.get(b).get(c);
                        tempBoard.get(b).set(c,e);
                    }
                }
                //removes queen from previous position on copied board
                tempBoard.get(queenIndex).set(x, 0); 
                for(int y = 0; y < boardSize ;y++){
                    tempBoard.get(y).set(x, 1); 
                    int tempHeuristic = createH(tempBoard);
                    valueBoard.get(y).set(x, tempHeuristic);
                    tempBoard.get(y).set(x,0);
                }
            }
            isRestart = true;
            //check for lowest hueristic present on the board
            for(int x = 0; x < boardSize; x++){
                for(int y = 0; y < boardSize; y++){
                    if(valueBoard.get(y).get(x) < heuristicValue){
                    //count how many positions have a lower heuristic value
                    numStates += 1;
                    }
                    if(valueBoard.get(y).get(x) < pastHValue){
                        //if heuristic value in the current pos is lower than the lowest heuristic, change the lowest heuristic to the 
                        pastHValue = valueBoard.get(y).get(x);
                        lowPosition[0] = y;
                        lowPosition[1] = x;
                        heuristicValue = pastHValue;
                        isRestart = false; //#signals reset doesn't need to happen
                    }
                }
            }
            stateChange += 1;
            if (isRestart){
                //if no position has been moved to have a lower hueristic, reset board
                restarts +=1;
                System.out.println("RESTART");
            }
            else {
            //if a position has been found to have a lower hueristic, move queen
                System.out.println("Neighbors found with lower h:" + numStates);
                System.out.println("Setting new current state...\n");
                // move queen to row with lowest heuristic
                for(int y = 0; y < boardSize; y++){
                    board.get(y).set(lowPosition[1], 0);
                    if(lowPosition[0] == y ){
                        board.get(y).set(lowPosition[1], 1);
                    }
                }
            }
        }
        System.out.println("Current State");
        printBoard(board);
        System.out.println("The Solution was Found!");
        System.out.println("State Changes: "+ stateChange);
        System.out.println("Restarts: "+ restarts);
    }
}