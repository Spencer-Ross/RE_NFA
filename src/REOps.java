

public class REOps {
    public NFA union(NFA a, NFA b, int states) {
        states = states - a.getStart() - b.getStart(); //not sure about this
        NFA c = new NFA(states, 'E');       //make new NFA for c
        c.edges.clear();                        //clear transitions
        c.edges.add(new Transition(states, a.getStart(), 'E')); //add e-trans from c to a
        c.edges.add(new Transition(states, b.getStart(), 'E')); //  e-trans from c to b
        states++;
        c.edges.add(new Transition(b.getFin(), states, 'E'));   //make c new F state
        c.edges.add(new Transition(a.getFin(), states, 'E'));
        c.edges.addAll(b.edges);            //copy all transitions
        c.edges.addAll(a.edges);
        return c;
    }

    public NFA concat(NFA a, NFA b, int states) {
        NFA c = new NFA(states, 'E');   //new NFA c
        c.edges.clear();
        c.edges.addAll(a.edges);
        c.edges.addAll(b.edges);
        c.edges.add(new Transition(a.getFin(), b.getStart(), 'E')); //concat end of a to begin of b
        return c;
    }

    public NFA kStar(NFA n, int states) {           //need to loop through n any number of times
        NFA m = new NFA(states, n.getSymbol());
        m.edges.clear();
        m.edges.addAll(n.edges);
        m.edges.add(new Transition(n.getStart() - 1, n.getStart(), 'E'));   //e-trans from state before n to nStart
        m.edges.add(new Transition(n.getFin(), n.getFin() + 1, 'E'));   //e-trans from nFinal to new state after Final
        m.edges.add(new Transition(n.getFin(), n.getStart(), 'E'));     //e-trans from old n final to old n start
        return m;
    }
}
