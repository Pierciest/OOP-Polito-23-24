import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.Comparator;



class CustomerDB implements Iterable<Customer>{ //explain the diamond later
    private Customer[] customers;
    private int size;

    private class CustomerIterator implements Iterator<Customer>{
        private int currIndex=0;

        @Override
        public boolean hasNext() {
            return currIndex <size;    
        }

        @Override
        public Customer next() {
           return customers[currIndex++];
        }
        
    }

    public CustomerDB(int capacity) {
        customers = new Customer[capacity];
        size = 0;
    }
    
    public void printCustomers() {
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(customers[i].getName()+" ");
        }
        System.out.println('\n');
    }

    public void printCustomers(Consumer printer){
        for (int i = 0; i < size; i++) {
            printer.accept(customers[i]);
        }
    }

    public void printCustomers(BiFunction<String, Integer, String> encoder, int key)
    {
        for (int i = 0; i < size; i++) {
            String encryptedName=encoder.apply(customers[i].getName(), key);
            String encryptedSurname=encoder.apply(customers[i].getSurname(), key);
            System.out.println(encryptedName+" "+encryptedSurname);
        }
    }

    public void printCustomers(Predicate<Customer> pred){
        for (int i = 0; i < size; i++) {
            if(pred.test(customers[i])){
                System.out.println(customers[i].getName());
            }
        }
    }

    public void addCustomer(Customer customer) {
        if (size < customers.length) {
            customers[size++] = customer;
        } else {
            System.out.println("Customer database is full. Cannot add more customers.");
        }
    }

    public void sortCustomers() {
        Arrays.sort(customers,0, size);
    }

    public void sortCustomers(Comparator<Customer> c) {
        Arrays.sort(customers,0, size, c);
    }

   

    @Override
    public Iterator<Customer> iterator() {
      return new CustomerIterator();
    }

    

}
