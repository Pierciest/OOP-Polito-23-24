class Customer implements Comparable<Customer> {
    private int id;
    private String name;
    private String surname;
    private String location;

    public Customer(int id, String name, String surname, String location) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.location = location;
        
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLocation() {
        return location;
    }
    

    @Override
    public int compareTo(Customer other){       
        return Integer.compare(this.id, other.id);
    }

   
}
