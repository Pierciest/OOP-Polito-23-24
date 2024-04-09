package Lecture1;
public class First {
    // Atributes
    int ID;
    String name;
    String description;

    // Constructor
    public First(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.description = "";
    }

    // getters
    public String getInformation() {

        String information = "ID: " + this.ID + " Name: " + this.name + " Description: " + this.description;

        return information;
    }

    // setters
    public void setDescription(String descr) {
        this.description = descr;
    }
}