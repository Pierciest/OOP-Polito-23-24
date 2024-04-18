# OOP Lab 2 - Hydraulic System Simulation

(the Italian version is available in file [README_it.md](README_it.md)).


Design and implement a program to manage the simulation of a hydraulic system.

All the classes must belong to the package `hydraulic`.

## R1. Elements and pipes

A hydraulic system is composed of elements (of different types) connected to each other (through pipes that are not explicitly modeled in the system).

A system is represented by an object of class `HSystem` that allows adding new elements through the method `addElement()` that accepts as argument an object of class `Element` and adds it to the system components.

By means of method `getElements()` it is possible to get an array containing all and only the elements that compose the system.

Hints:

- 👉 You can safely assume that the maximum number of elements in a system is `100`.


## R2. Simple elements

The simple elements are represented by the classes `Source`, `Tap`, and `Sink`.

All elements have a name that can be read through the getter method `getName()`. 

It is possible to connect the output of an element to the input of another element by means of the method `connect()` that accepts as argument the element whose input should be connected to the output of the subject: `a.connect(b);` connects the output of element `a` to the input of element `b`.  
The invocation of method `connect()` on a `Sink` object has no effect.

Given an element, it is possible to know to which other output element it is connected by means of the method `getOutput()` that returns an `Element` object.


## R3. Complex elements

In addition to the simple elements described above, there are some complex elements. The *"T"* element, represented by class `Split`, allows splitting the input flow into two equal output flows. For this class, the `connect()` method accepts an additional argument specifying which output is being connected, such parameter may assume the values `0` for the first output and `1` for the second output. 

For this class, it is possible to know which elements are connected to the outputs, by means of the method `getOutputs()` that returns an array with the two connected elements.


## R4. Simulation
Given a valid system, i.e. a tree with a source as the root and where each path ends with a sink, it is possible to compute flow and how it is split among the distinct paths.

The computation takes place into two phases: in the first phase the parameters of the elements belonging to the system are defined, in the second phase the actual simulation starts.

During the first setup phase it is possible to:

- define the flow of a `Source` through the method `setFlow()` that accepts a number representing the cubic meters per hour.
- set the opening of `Taps` using the method `setOpen()` that accepts a boolean argument. When a tap is open the output flow is equal to the input flow, otherwise the output is zero.

For the T split the input flow is divided into two equal output flows (each a half of the input flow).

The `simulate()` method of class `HSystem`, performs the flow computations for each element in the system starting from each source (for the source, only the output flow, and for the sink just the input flow). This method requires as argument an object implementing the `SimulationObserver` interface.

When, during simulation, the input and output flows are known for a given element then method `notifyFlow()` of the observer object must be called passing the element type (the name of the class, e.g. `"Source"`), the name, the input flow, and the output flows; if any of the flows is not defined (e.g. for the input for `Source` and output for `Sink`), the constant `NO_FLOW` defined in the interface can be used.

Hints:

- 👉 given an object, to find out if it is an instance of a specific class the operator `instanceof` can be used.
E.g. `if( element instanceof Source )` checks if reference `element` points to an object of class `Source`.
- **Warning**: you are **not** required to implement the interface `SimulationObserver`, you only need to use it in the simulation code; a sample implementation that simply prints to the console the notifications, is given in class `PrintingObserver`.


---

### Additional requirements

---

## R5. Multi-split

Class `Multisplit` represents an extension of class `Split` that allows multiple outputs. The constructor accepts, in addition to the name, the number of outputs.

The method `connect()` accepts two arguments: the element to be connected, and the output number to connect it to. Outputs are numbered starting from 0.

To find out which elements are connected to the outputs of a multi-split, method `getOutputs()` can be used; it returns an array of the connected elements. If no elements is connected to an output, the corresponding item in the array is set to `null`.
In preparation for the simulation method `setProportions()` can be used; it accepts a series of `double` values that define the proportion according to which the input flow is divided among the outputs.

Hints:

- 👉 Assume that the number of proportions passed to the method is equal to the number of outputs and that their sum is `1.0`.


## R6. Visualization

Method `layout()` in class `HSystem` returns a string containing the layout of the system using ASCII characters and spacing.

Each element is printed in the form `"[name]Type"`, where *name* is the name of the element and *Type* is the class of the element. The connection between the output of one element and the input of another is represented by the symbol `"->"`, while the absence of a connected element at the output is represented by `"*"`. In the case of multiple elements connected downstream of an element, the character `"+"` is used, and the first connection is reported on the same line. In subsequent lines, `"+"` is repeated (vertically aligned with the first) and then the other connections are reported. The rows that separate the multiple connections (at least one, but there could be more if there are other elements downstream with multiple outputs) are marked with the character `"|"` vertically aligned with `"+"`.

For instance, a system composed of a `Source`, connected to a `Tap`, that is connected to a `Split` whose outputs are connected to `Sink`s, would return a layout like the following one:

```
[Src]Source -> [R]Tap -> [T]Split +-> [sink A]Sink
                                  | 
                                  +-> [sink B]Sink
```

Hints:

- 👉 Remember that the system might be incomplete, i.e., some elements' outputs might not be connected to any element.


## R7. Element removal

Method `deleteElement()` of class `HSystem` deletes from the system a previously added element; the method takes as input parameter the name of element to be deleted.

If such an element is `Split` or a `Multisplit` having more than one output connected to other elements, no operation is performed, and the method returns `false`.

Otherwise, (i.e., `Split` or `Multisplit` with at most one output connected or any other element type), the element is removed from the system, and if the element to be removed is connected to other elements in input or output, connections must be modified appropriately so that the upstream element is connected to the downstream one. In this case the method returns `true`.

Taking the example from [R6](#r6-visualization), the layout after deleting the `Tap` "R" should be like the following one:

```
[Src]Source -> [T]Split +-> [sink A]Sink
                        |
                        +-> [sink B]Sink
```

## R8. Maximum flow rate and related alarms

The class `Element` has a method `setMaxFlow()`, which takes as input parameter a real number, representing the maximum flow rate of an element. If an element takes in input a flow greater than its max flow rate, the element is in danger of breaking down. For `Source` objects, since they do not have any input, calls to the `setMaxFlow()` method should not have any effect.

The `HSystem` class contains an overload of method `simulate()`, that accepts an additional boolean argument `enableMaxFlowCheck`: if this latter method is `true` maximum flow checks should be performed.
The method accepts as first argument and object implementing the `SimulationObserver` interface. 
Whenever an element's input flow is greater than its maximum allowed flow, the simulation calls the method `notifyFlowError()` of the observer to notify an error. The notification method accepts as arguments the element type (class name), the name, the input flow, and the maximum flow rate.

## R9. Fluent builder

The class `HBuilder` implements a builder that using method chaining provides a [Fluent API](https://www.martinfowler.com/bliki/FluentInterface.html).

The builder can be created using the method `build()` in class `HSystem`.

As far as the system layout is concerned the following methods can be used:

- a new source can be added with method `addSource()` accepting the name of the source.

- a new tap can be linked to the previous element with `linkToTap()` that accepts the name of the element.

- a new sink can be linked to the previous element with `linkToSink()` that accepts the name of the element.

- a new split can be linked to the previous element with `linkToSplit()` that accepts the name of the element.

- a new multisplit can be linked to the previous element with `linkToMultisplit()` that accepts the name of the element and the number of outputs.

- when a split or multisplit is linked, the different outputs can be specified calling the method `withOutputs()` that introduces all the outputs:
    - the outputs can be defined using the `linkTo..` methods

- to specify the next output to a (multi)split the method `then()` must be used

- to indicate that all outputs for a (multi)split have been defined the method `done()` can be used

- eventually method `complete()` can be used to get the system object.

### Example

The following code:

```
HSystem s = HSystem.build().
        addSource("Src")
        linkToMultisplit("MS",3).withOutputs().
            linkToSplit("T").withOutputs().
                linkToSink("S1").
                then().linkToSink("S2").
                done().
            then().linkToSink("S3").
            then().linkToSink("S4").
        complete();
```

produces the following system:

```
[Src]Source -> [MS]Split +-> [T]Split +-> [S1]Sink
                         |            |
                         |            +-> [S2]Sink
                         |
                         +-> [S3]Sink
                         |
                         +-> [S4]Sink
```

As far as the simulation parameters, the following method can be used right after the corresponding element has been defined:

- `open()` and `closed()` to define the status of a tap,
- `withFlow()` to define the flow of a source,
- `withProportions()` to define the proportions of a multisplit.

Concerning the maximum flow, the method `maxFlow()` can be used after an element to define the maximum allowed flow.

---

Version 1.1.1 - 2024-04-06
