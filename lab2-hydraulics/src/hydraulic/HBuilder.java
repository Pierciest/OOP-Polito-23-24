package hydraulic;

/**
 * Hydraulics system builder providing a fluent API
 */



public class HBuilder {
    
    private Element curElement;
    private HSystem system;
    private Stack stack[] = new Stack[100];
    private int s = -1;

    HBuilder() {
        curElement = null;
        system = new HSystem();
    }

    private class Stack {
        Element stackElement;
        int usedOutputs = 0;
    }

    private void ensureStackInitialized() {
        if (stack[s] == null) {
            stack[s] = new Stack();
            stack[s].stackElement = curElement;
        }
    }

    




    /**
     * Add a source element with the given name
     * 
     * @param name name of the source element to be added
     * @return the builder itself for chaining 
     */

     
     public HBuilder addSource(String name) {
        Source src = new Source(name);
        curElement = src;
        system.addElement(src);
        return this;
    }


    /**
     * returns the hydraulic system built with the previous operations
     * 
     * @return the hydraulic system
     */
    public HSystem complete() {
        return system;
    }

    /**
     * creates a new tap and links it to the previous element
     * 
     * @param name name of the tap
     * @return the builder itself for chaining 
     */
    public HBuilder linkToTap(String name) {
        Tap tap = new Tap(name);
        if (s >= 0) {
            ensureStackInitialized();
            curElement.connect(tap, stack[s].usedOutputs);
            stack[s].usedOutputs++;
        } else {
            curElement.connect(tap);
        }
        curElement = tap;
        system.addElement(tap);
        return this;
    }

    /**
     * creates a sink and link it to the previous element
     * @param name name of the sink
     * @return the builder itself for chaining 
     */
    public HBuilder linkToSink(String name) {
        Sink sink = new Sink(name);
        if (s >= 0) {
            ensureStackInitialized();
            curElement.connect(sink, stack[s].usedOutputs);
            stack[s].usedOutputs++;
        } else {
            curElement.connect(sink);
        }
        curElement = sink;
        system.addElement(sink);
        return this;
    }

    /**
     * creates a split and links it to the previous element
     * @param name of the split
     * @return the builder itself for chaining 
     */
    public HBuilder linkToSplit(String name) {
        Split split = new Split(name);
        if (s >= 0) {
            ensureStackInitialized();
            curElement.connect(split, stack[s].usedOutputs);
            stack[s].usedOutputs++;
        } else {
            curElement.connect(split);
        }
        curElement = split;
        system.addElement(split);
        s++;
        ensureStackInitialized();
        return this;
    }
    /**
     * creates a multisplit and links it to the previous element
     * @param name name of the multisplit
     * @param numOutput the number of output of the multisplit
     * @return the builder itself for chaining 
     */
    public HBuilder linkToMultisplit(String name, int numOutput) {
        Multisplit ms = new Multisplit(name, numOutput);
        if (s >= 0) {
            ensureStackInitialized();
            curElement.connect(ms, stack[s].usedOutputs);
            stack[s].usedOutputs++;
        } else {
            curElement.connect(ms);
        }
        curElement = ms;
        system.addElement(ms);
        s++;
        ensureStackInitialized();
        return this;
    }

    /**
     * introduces the elements connected to the first output 
     * of the latest split/multisplit.
     * The element connected to the following outputs are 
     * introduced by {@link #then()}.
     * 
     * @return the builder itself for chaining 
     */


    public HBuilder withOutputs() {
        ensureStackInitialized();
        stack[s].stackElement = curElement;
        return this;     
    }

    /**
     * inform the builder that the next element will be
     * linked to the successive output of the previous split or multisplit.
     * The element connected to the first output is
     * introduced by {@link #withOutputs()}.
     * 
     * @return the builder itself for chaining 
     */
    public HBuilder then() {
        if (s >= 0) {
            ensureStackInitialized();
            curElement = stack[s].stackElement;
        }
        return this;
    }

    /**
     * completes the definition of elements connected
     * to outputs of a split/multisplit. 
     * 
     * @return the builder itself for chaining 
     */
    public HBuilder done() {
        if (s >= 0) {
            s--;
            curElement = stack[s].stackElement;
        }
        return this;
    }

    /**
     * define the flow of the previous source
     * 
     * @param flow flow used in the simulation
     * @return the builder itself for chaining 
     */
    public HBuilder withFlow(double flow) {
        if (curElement instanceof Source) {
            ((Source) curElement).setFlow(flow);
        }
        return this;
    }

    /**
     * define the status of a tap as open,
     * it will be used in the simulation
     * 
     * @return the builder itself for chaining 
     */
    public HBuilder open() {
        if (curElement instanceof Tap) {
            ((Tap) curElement).setOpen(true);
        }
        return this;
    }

    /**
     * define the status of a tap as closed,
     * it will be used in the simulation
     * 
     * @return the builder itself for chaining 
     */
    public HBuilder closed() {
        if (curElement instanceof Tap) {
            ((Tap) curElement).setOpen(false);
        }
        return this;
    }

    /**
     * define the proportions of input flow distributed
     * to each output of the preceding a multisplit
     * 
     * @param props the proportions
     * @return the builder itself for chaining 
     */
    public HBuilder withPropotions(double[] props) {
        if (curElement instanceof Multisplit) {
            ((Multisplit) curElement).setProportions(props);
        }
        return this;
    }

    /**
     * define the maximum flow theshold for the previous element
     * 
     * @param max flow threshold
     * @return the builder itself for chaining 
     */
    public HBuilder maxFlow(double max) {
        curElement.setMaxFlow(max);
        return this;
    }
}
