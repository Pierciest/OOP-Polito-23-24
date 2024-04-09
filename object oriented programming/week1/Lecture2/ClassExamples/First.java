package Lecture2.ClassExamples;

public class First {
    // Attributes
    int ID;
    String name; //Can be set to Static String name...
    String description;

    // Constructor
    public First(int _ID, String _name) {
        this.ID = _ID;  // Use First.ID if the attribute is static
        this.name = _name; // Can be set to private but private.name
        this.description = "";
    }

    // Getters
    public String getInformation() {
        String information = "ID: " + this.ID + "\nName: " + this.name + "\nDescription: " + this.description;
        return information;
    }

    /*
    public String getName(){
        return this.name;
    }
    */ //Used if the attributes are set private.

    // Setters
    public void setDescription(String descr) {
        this.description = descr;
    }

    public static void main(String[] args) {
        //System.out.println("Hello World!");

        First student1 = new First(293302, "Mert");
        student1.setDescription("This a me, merdo");
        String info1 = student1.getInformation();

        System.out.println(info1);
    }
}