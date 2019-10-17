import java.util.*;

public class NFA {
    Vector<Transition> edges = new Vector<Transition>();    //each NFA will contain a Vector array of transitions
    private int start;                  //# of state at the NFA's beginning
    private int fin;                    //# of NFA's accept state
    private char symbol;                //language the NFA recognizes

    //constructors//////////////////////
    public NFA(int q, char c) {
        this.start = q + 1;
        this.fin = q + 2;
        this.symbol  = c;
        this.edges.add(new Transition(start, fin, symbol)); //a new NFA has one transition.
    }
    //SETTERS & GETTERS/////////////////
    public int getStart() {
        return this.start;
    }

    public void setStart(int n) {
        this.start = n;
    }

    public int getFin() {
        return this.fin;
    }

    public void setFin(int n) {
        this.fin = n;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setSymbol(char c) {
        this.symbol = c;
    }
    ///////////////////////////////////
}
