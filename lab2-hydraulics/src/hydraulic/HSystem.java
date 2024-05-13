package hydraulic;

/**
 * Main class that acts as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	Element elements[] = new Element[100];
	int i = 0;
	String Layout[][] = new String[100][100];
	String layoutString = "";

// R1
	/**
	 * Adds a new element to the system
	 * 
	 * @param elem the new element to be added to the system
	 */
	public void addElement(Element elem){
		elements[i] = elem;
		i++;
	}
	
	/**
	 * returns the element added so far to the system
	 * 
	 * @return an array of elements whose length is equal to 
	 * 							the number of added elements
	 */
	public Element[] getElements(){
		int count = 0;
		int ind = 0;
		for(int k = 0; k<i; k++){
			if(elements[k] != null){
				count++;
			}
		}
		Element new_elements[] = new Element[count];
		for(int j = 0; j<i; j++){
			if(elements[j] != null){
				new_elements[ind++] = elements[j];
			}
		}
		return new_elements;
	}

// R4
	/**
	 * starts the simulation of the system
	 * 
	 * The notification about the simulations are sent
	 * to an observer object
	 * 
	 * Before starting simulation the parameters of the
	 * elements of the system must be defined
	 * 
	 * @param observer the observer receiving notifications
	 */
	public void simulate(SimulationObserver observer){
		Element.initializeFlow(elements);
		SimulationObserver printingObserver = observer;
		for(Element element: elements){
			if(element != null){
				printingObserver.notifyFlow(element.getClass().getSimpleName(), element.getName(), element.flow, element.outputFlow);
			}
			
		}
		
		

		
	}

// R6
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public void initializeLayout(){
		for(Element el: elements){
			if(el instanceof Source){
				updateLayout(el,0,0);
			}
		}
	}

	public void updateLayout(Element element, int row, int column){
		if (element == null) {
			return;
		}

		if (row >= Layout.length || column >= Layout[column].length) {
			return;
		}
	
		switch (element.getClass().getSimpleName()) {
			case "Source":	
				if(element.getOutput() == null){
					Layout[row][column] = "[" + element.name + "]" + "Source *";
				}
				else{
					Layout[row][column] = "[" + element.name + "]" + "Source -> ";
				}
				updateLayout(element.output[0], row, column + 1);
				break;
	
			case "Tap":
				if(element.getOutput() == null){
					Layout[row][column] = "[" + element.name + "]" + "Tap *";
				}
				else if (row > 0){
					Layout[row][column] = "[" + element.name + "]" + "Tap +-> ";
				}	
				else{
					Layout[row][column] = "[" + element.name + "]" + "Tap -> ";
				}				
				updateLayout(element.output[0], row, column + 1);
				break;
	
			case "Sink":
				if(row > 0){
					Layout[row][column] = "+-> " + "[" + element.name + "]" + "Sink";
				}
				else{
					Layout[row][column] = "[" + element.name + "]" + "Sink";

				}
				break;
	
			case "Split":
				Layout[row][column] = "[" + element.name + "]" + "Split +-> ";
				for (int i = 0; i < 2; i++) {
					if(element.output[i] == null){
						Layout[row+i][column + 1] = "+-> *";
					}
					updateLayout(element.output[i], row + i, column + 1);
				}
				break;
	
			case "Multisplit":
				Layout[row][column] = "[" + element.name + "]" + "Multisplit +-> ";
				for (int i = 0; i < element.output.length; i++) {
					if(element.output[i] == null){
						Layout[row+i][column + 1] = "+-> *";
					}
					updateLayout(element.output[i], row + i, column + 1);
				}
				break;
		}
	}
	



	public String layout(){
		
		initializeLayout();


		for(int i = 0; i< 100; i++){
			for(int j = 0; j<100; j++){
				int length = 0;
				if(Layout[i][j] != null){
					if(i == 0){
						layoutString += Layout[i][j];
					}
					else{
						for(int k = 0; k<j ; k++){
							length += Layout[0][k].length();
						}
						layoutString += " ".repeat(length-4) + Layout[i][j];
					}
					
				}
			}
			layoutString += "\n";
		}
		return layoutString;
	}

// R7
	/**
	 * Deletes a previously added element 
	 * with the given name from the system
	 */
	public boolean deleteElement(String name) {
        for (int ind = 0; ind < elements.length; ind++) {
            Element el = elements[ind];
            if (el == null) continue; // Skip null elements

            if (el.name.equals(name) && !processTarget(el)) {
                return false; // If the element is not processable, return false
            }

            // Check if the current element itself should be deleted
            if (el.name.equals(name) && processTarget(el)) {
                disconnectOutputs(el); // Handle outputs before deletion
                elements[ind] = null; // Remove the element from the array
                return true;
            }

            // Additionally check if any outputs need to be disconnected or redirected
            for (int i = 0; i < el.outputNumber; i++) {
                Element target = el.output[i];
                if (target != null && target.name.equals(name)) {
                    updateOutputConnection(el, i);
                }
            }
        }
        return false; // No element was found and deleted
    }

    private boolean processTarget(Element target) {
        // Modify condition based on actual needs, here simplifying to a direct check
        return target.usedOutputs < 2; // Allow deletion if there's one or zero used outputs
    }

    private void disconnectOutputs(Element el) {
        // Clear all outputs of the element being deleted
        for (int i = 0; i < el.outputNumber; i++) {
            if (el.output[i] != null) {
                el.output[i] = null;
                el.usedOutputs--; // Decrease the used output count as we are disconnecting each
            }
        }
    }

    private void updateOutputConnection(Element el, int outputIndex) {
        Element target = el.output[outputIndex];
        if (target == null) return;

        if (target.getClass().getSimpleName().equals("Sink")) {
            el.output[outputIndex] = null; // Disconnect if target is a 'Sink'
            el.usedOutputs--; // Decrease the used output count as we are disconnecting
        } else if (target.output != null && target.output.length > 0) {
            el.output[outputIndex] = target.output[0]; // Replace with the target's first output
        } else {
            el.output[outputIndex] = null; // Set to null if no outputs are available to replace
            el.usedOutputs--; // Decrease the used output count as we are disconnecting
        }
    }
	
	

// R8
	/**
	 * starts the simulation of the system; if {@code enableMaxFlowCheck} is {@code true},
	 * checks also the elements maximum flows against the input flow
	 * 
	 * If {@code enableMaxFlowCheck} is {@code false}  a normals simulation as
	 * the method {@link #simulate(SimulationObserver)} is performed
	 * 
	 * Before performing a checked simulation the max flows of the elements in thes
	 * system must be defined.
	 */
	public void simulate(SimulationObserver observer, boolean enableMaxFlowCheck) {
		Element.initializeFlow(elements);
		SimulationObserver printingObserver = observer;
		
		for(Element element: elements){
			if(element == null){
				return;
			}
			else if(element.flow <= element.MAXFlow || element.getClass().getSimpleName().equals("Source")){
				printingObserver.notifyFlow(element.getClass().getSimpleName(), element.getName(), element.flow, element.outputFlow);
			}
			else if(element.flow > element.MAXFlow ){
				printingObserver.notifyFlow(element.getClass().getSimpleName(), element.getName(), element.flow, element.outputFlow);
				printingObserver.notifyFlowError(element.getClass().getSimpleName(), element.getName(), element.flow, element.MAXFlow);;
			}
			
		}
	}

// R9
	/**
	 * creates a new builder that can be used to create a 
	 * hydraulic system through a fluent API 
	 * 
	 * @return the builder object
	 */
    public static HBuilder build() {
		
		return new HBuilder();
    }
}
