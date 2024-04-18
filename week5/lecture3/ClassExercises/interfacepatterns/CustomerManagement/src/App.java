import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.print.attribute.IntegerSyntax;
import javax.swing.Box;

class Printer implements Consumer{

    @Override
    public void accept(Object t) {
        Customer customer=(Customer) t;
        System.out.println(customer.getName()+" "+customer.getSurname());
    }

}

public class App {
    public static void main(String[] args) {

        
        CustomerDB database = new CustomerDB(5);
        database.addCustomer(new Customer(19, "John", "Doe", "New York"));
        database.addCustomer(new Customer(2, "Alice", "Smith", "London"));
        database.addCustomer(new Customer(30, "Bob", "Johnson", "Paris"));

        // Sorting the database EXAMPLE of COMMON BEHAVIOUR (Comparable)
        database.printCustomers();
        //database.sortCustomers();
        database.printCustomers();
        
        //Iterating: EXAMPLE of COMMON BEHAVIOUR (Iterable and iterator)
        for(Customer c: database){
            System.out.println(c.getLocation());
        }
     
        //Passing different printing methods: EXAMPLE of BEHAVIOURAL PARAMETRIZATION
        // Printing customers' names and surnames
        System.out.println("Printing customers by name and surname");
        Printer p=new Printer();
        database.printCustomers(p);

        // Printing customers' ids and locations
        database.printCustomers(new Consumer() {
            public void accept(Object obj){
                Customer customer=(Customer) obj;
                System.out.println(customer.getId()+" "+customer.getLocation());
            }
        });
       

        //Comparator: EXAMPLE of STRATEGY PATTERN
        /*database.sortCustomers(new Comparator<Customer>() {

            @Override
            public int compare(Customer c1, Customer c2) {
                return c1.getName().compareTo(c2.getName());
            }
            
        });
        database.printCustomers();*/

       
        //Lambda functions: sort but by passing a lambda comparator
        database.sortCustomers((c1, c2)-> c1.getName().compareTo(c2.getName()));
        database.printCustomers();

        //Example anonimize by encryption the name of the customers --> replace in the name letters in even position by adding to their ASCII value the KEY
       //with anonymous class
       database.printCustomers(new BiFunction<String,Integer,String>() {
        public String apply(String s, Integer key){
            StringBuffer sb=new StringBuffer(s);
            for(int i=0; i<sb.length();i=i+2){
                sb.setCharAt(i, (char)(sb.charAt(i)+key));
            }
            return new String(sb);
        }
       }, 7);
        System.out.println();
       //with lambda
        database.printCustomers((s,key)->{
            StringBuffer sb=new StringBuffer(s);
            for(int i=0; i<sb.length();i=i+2){
                sb.setCharAt(i, (char)(sb.charAt(i)+key));
            }
            return new String(sb);
        }, 7);
        
       
        System.out.println();
        //TRY IT OUT: print after filtering using the Predicate interface 
        database.printCustomers(c->c.getLocation()=="New York");
      
        //with method reference
       Encoder anonymizer= new Encoder();
       database.printCustomers(anonymizer::apply, 7);
       


       //Generics //Example PAIR single type

   Pair<String> sp = new Pair<String>("first", "second");
   Pair<Integer> ip = new Pair<Integer>(1,2);
   
   String a = sp.getFirst();
   int b = ip.getSecond();

   //String bs = ip.getFirst(); //COMPILE-TIME ERROR. TYPE MISMATCH
	
	//Example mixed PAIR 
	//Pair<String> mp = new Pair<String>(1, "second"); //COMPILE-TIME ERROR. EXPECTING STRING

    MixedPair<String, Integer> mp = new MixedPair<String,Integer>("First", 2);
    String s = mp.getFirst();
    int i = mp.getSecond();
	
    
    //Generic Box Example

    String sc = "content";
    Integer ic = 130;
    
    GenericBox<String> box = new GenericBox<String>(sc);
    System.out.println(box.get());
    box.put("new content");
    System.out.println(box.get());
    //box.put(12); // COMPILE-TIME ERROR - NOT APPLICABLE FOR THE TYPE

    GenericBox<Integer> intBox = new GenericBox<Integer>(ic);
    System.out.println(intBox.get());
    intBox.put(13);
    System.out.println(intBox.get());

    GenericBox<String> anotherBox = new GenericBox<String>("hi");

    // generic methods - remove
    String removed = anotherBox.remove("hi");
    System.out.println(removed);

    // invariance limitations attempt at universality
    //Pair.printPair(sp);// errorrr - failed
    //Pair<Object> op = new Pair<Object>(1, "second");
    //Pair.printPair(op);

    //sorting pairs - Customer must implement Comparable<Customer> because Pair<T extends Comparable<T>>

    Customer c1 = new Customer(i, a, s, a);
    Pair<Customer> intPair = new Pair<Customer>(c1, c2);
    }

    static Comparator<Customer> reverse(Comparator <Customer> cmp){
        return (a, b)-> cmp.compare(b,a); 
    }

    


   
}