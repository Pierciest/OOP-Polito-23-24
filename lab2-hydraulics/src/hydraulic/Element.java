package hydraulic;


/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 * 
 * The class is abstract since it is not intended to be instantiated,
 * though all methods are defined to make subclass implementation easier.
 */
public abstract class Element {
	int outputNumber = 1;
	String name;
	Element output[];
	double flow;
	double outputFlow[];
	double prop[];
	double MAXFlow;
	int usedOutputs = 0;
	/**
	 * getter method for the name of the element
	 * 
	 * @return the name of the element
	 */
	public String getName() {
		return name;
	}

	public Element(){
		outputFlow = new double[outputNumber];
		output = new Element[outputNumber];
		
	}

	public Element(int outputNumber){
		this.outputNumber = outputNumber;
		outputFlow = new double[outputNumber];
		output = new Element[outputNumber];
		prop = new double[outputNumber];

	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * 
	 * In case of element with multiple outputs this method operates on the first one,
	 * it is equivalent to calling {@code connect(elem,0)}. 
	 * 
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem) {
		output[0] = elem;
	}
	
	/**
	 * Connects a specific output of this element to a given element.
	 * The given element will be connected downstream of this element
	 * 
	 * @param elem the element that will be placed downstream
	 * @param index the output index that will be used for the connection
	 */
	public void connect(Element elem, int index){
		output[index] = elem;
	}
	
	/**
	 * Retrieves the single element connected downstream of this element
	 * 
	 * @return downstream element
	 */
	public Element getOutput(){
		return output[0];
	}

	/**
	 * Retrieves the elements connected downstream of this element
	 * 
	 * @return downstream element
	 */
	public Element[] getOutputs(){
		return output;
	}
	
	/**
	 * Defines the maximum input flow acceptable for this element
	 * 
	 * @param maxFlow maximum allowed input flow
	 */
	public void setMaxFlow(double maxFlow) {
		MAXFlow = maxFlow;
	}
	
	public static void initializeFlow(Element[] elements){
		for(Element el: elements){
			if(el instanceof Source){
				updateFlow(el,el.flow);
			}
		}
	}

	public static void updateFlow(Element element, double inFlow) {
		element.flow = inFlow;
		switch (element.getClass().getSimpleName()) {
			case "Source":
				element.outputFlow[0] = inFlow;
				updateFlow(element.output[0], inFlow);
				break;


			case "Tap":
				element.outputFlow[0] = inFlow;
				updateFlow(element.output[0], inFlow);
				break;


			case "Sink":
				element.outputFlow[0] = inFlow;
				if (element.output.length > 0 && element.output[0] != null) {
					updateFlow(element.output[0], inFlow);
				}
				break;
			case "Split":
				double splitFlow = inFlow / 2; 
				for (int i = 0; i < 2; i++) {
					if (element.output[i] != null) {
						element.outputFlow[i] = splitFlow;
						updateFlow(element.output[i], splitFlow);
					}
				}
				break;
			case "Multisplit":
				for (int i = 0; i < element.output.length; i++) {
					if (element.output[i] != null) {
						element.outputFlow[i] = element.prop[i] * inFlow;
						updateFlow(element.output[i], element.outputFlow[i]);
					}
				}
				break;
		}
	}
	
}
