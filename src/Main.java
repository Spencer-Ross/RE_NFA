/***************
 * RE to NFA
 *
 * SPENCER ROSS
 * Class: Automata & Formal Languages
 * Prof: Farhana Kabir
 * 10/13/2019
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String line;
        char symbol;
        int state, x = 1;

        Stack<NFA> stack = new Stack<NFA>();    //stack of NFAs
        NFA nFA1;
        NFA nFA2;

        REOps op = new REOps();
        String database;

        try {
            database = args[1];     //check if user gave input on command line
        } catch (Exception e) {
            database = "/Users/Spencer/Downloads/Sample Testfile.txt";  //change this to whatever database file
        }

        File data = new File(database);     //turn filepath string into File
        Scanner sc;

        try {                               //try reading from file
            sc = new Scanner(data);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            return;
        }

        while(sc.hasNextLine()) {                   //loop until EOF
            System.out.println(("NFA " + x++ + ":"));
            stack.clear();          //clear the stack for use with a fresh NFA
            state = 0;              //new NFA has no states yet
            line = sc.nextLine();

            for(int i=0; i < line.length(); i++) {
                symbol = line.charAt(i);
                if(symbol == '&' || symbol == '|') {
                    if(stack.size() <= 1) {
                        System.out.println("ERROR: invalid regex");
                        return;
                    }
                    nFA2 = stack.pop();
                    nFA1 = stack.pop();
                    if(symbol == '&') stack.push(op.concat(nFA1, nFA2, state));     //concat
                    else if(symbol == '|') {                                    //union
                        stack.push(op.union(nFA1, nFA2, state));
                        state += 2;
                    }
                } else if(symbol == '*') {                  //kleene star
                    nFA1 = stack.pop();
                    stack.push(op.kStar(nFA1, state));
                    state += 2;
                } else {                                //normal NFA with a start and final state and a transition
                    stack.push(new NFA(state, symbol));
                    state += 2;
                }
            }

            for(int i=0; i <= stack.size()-1; i++) {
                for(int j=0; j <= stack.get(i).edges.size()-1; j++) {
                    System.out.print("(q" + stack.get(i).edges.get(j).getCurr()+ ", ");
                    System.out.print(stack.get(i).edges.get(j).getEdge());
                    System.out.println(") -> q" + stack.get(i).edges.get(j).getNext());
                }
            }
        }

        sc.close();
    }
}

