/**
 * Michael Pearce
 */

import java.util.*;
import java.lang.*;

public class Driver {

    //displays node grid
    public void displayNode(Node [][]d) {
        System.out.println("     0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 ");
        System.out.print("    ____________________________________________");
        for(int i = 0; i < d.length; i++) {
            System.out.print("\n");
            if (i < 10) {
                System.out.print(i + "  | ");
            }
            else {
                System.out.print(i+" | ");
            }
            for(int j = 0; j < d[0].length; j++) {
                System.out.print(d[i][j].getType()+ " ");
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    //makes 10% of each node unpathable
    public void set10percent(int [][]b) {
        Random rand = new Random();
        for(int i=0; i < b.length; i++){
            for(int j = 0; j < b[0].length; j++){
                float chance = rand.nextFloat();
                if (chance <= 0.10f){
                    b[i][j]=1;
                }
            }
        }   
    }

    //transfers array into nodes
    public void writeToNode(int [][] a, Node [][]b){
        for (int i=0; i< a.length;i++) {
            for (int j=0; j<a[0].length;j++) {
                b[i][j] = new Node(i, j, a[i][j]);
            }
        }
    }

    public Node getStart(Node[][]b){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nUsing the row and column numbers,\n" +
                "Please enter the Starting node\n" +
                "separated by a space.\n" +
                "________________________________________________________\n");
        int startingRow = scanner.nextInt();
        int startingCol = scanner.nextInt();
        b[startingRow][startingCol].setType(2);

        System.out.println("Your starting Node is: ("+startingRow+", "+startingCol+")\n");

        Node s = new Node(startingRow, startingCol, 2);
        s.setG(0);
        s.setF();
        return s;
    }

    public Node getGoal(Node [][]b){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nUsing the row and column numbers,\n" +
                "Please enter the Goal node\n" +
                "separated by space.\n" +
                "________________________________________________________\n");

        int row = scanner.nextInt();
        int col = scanner.nextInt();
        b[row][col].setType(9);
        System.out.println("\nUSING \"2\" AS OUR START NODE AND \"9\" AS OUR GOAL NODE\n" +
                "\"0\" represents the walkable areas and \"1\' represents the blocks");
        System.out.println("Your Goal Node is: ("+row+", "+col+")\n");

        Node g = new Node(row, col, 3);//out the end node in to goal
        g.setF();
        return g;

    }

    public static void getHeuristic(Node [][]b, Node g){
        //get heuristic
        for (int i = 0; i < b.length; i++){
            for(int j = 0; j < b[0].length; j++){
                b[i][j].setH((10 * (Math.abs(i - g.getRow())))+(10 * (Math.abs(j - g.getCol()))));
            }
        }
    }

    public void displayHeuristic(Node [][]d) {
        System.out.println("\nThis is a grid of the heuristics");
        for(int i=0; i < d.length; i++) {
            System.out.print("\n");
            if (i < 10) {
                System.out.print(i + "  | ");
            }
            else {
                System.out.print(i + " | ");
            }
            //gets value for the node
            for(int j = 0; j < d[0].length; j++) {
                if ((d[i][j].getH() >= 10) && (d[i][j].getH() <= 90)){
                System.out.print(d[i][j].getH()+ "  ");
            }
                else if (d[i][j].getH() < 10){
                    System.out.print(d[i][j].getH()+ "   ");
                }
                else {
                    System.out.print(d[i][j].getH()+ " ");
                }
                System.out.print(" ");
            }
        }
        System.out.println("\n");
    }

    public static Node checkinList(Node node, ArrayList<Node> list){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(node)){
                return list.get(i);
            }
        }
        return null;
    }

    public static void sort(ArrayList<Node> slist) {
        int lowestF; 
        Node tempNode;
        for(int i = 0; i < slist.size(); i++) {
            lowestF = i;
            int b = slist.size() - 1;
            for(int id = i; id < b; id++) {
                if (slist.get(id + 1).getF() < slist.get(lowestF).getF()) {
                    lowestF = id + 1;
                }
            }
            tempNode = slist.get(i);
            slist.set(i, slist.get(lowestF));
            slist.set(lowestF, tempNode);}
    }
}