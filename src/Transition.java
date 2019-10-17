public class Transition {
    private int curr;    //amount of states trans goes to
    private int next;       //amount of states trans left
    private char edge;      //trans symbol

    public Transition(int q0, int f, char symbol) {
        this.next = f;
        this.curr = q0;
        this.edge = symbol;
    }
    //SETTERS & GETTERS/////////////////
    public int getCurr() {
        return curr;
    }

    public int getNext() {
        return next;
    }

    public char getEdge() {
        return edge;
    }

    public void setCurr(int curr) {
        this.curr = curr;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public void setEdge(char edge) {
        this.edge = edge;
    }
}
