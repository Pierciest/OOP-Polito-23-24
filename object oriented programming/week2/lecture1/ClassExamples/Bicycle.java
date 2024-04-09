package week2.lecture1.ClassExamples;

public class Bicycle {
    //Attributes
    private String color;
    private String brand;
    private boolean isElectrical;
    private int size;
    private float priceRange;

    //Constructor
    public Bicycle(String color, String brand, boolean isElectrical, int size, float priceRange) {
        this.color = color;
        this.brand = brand;
        this.isElectrical = isElectrical;
        this.size = size;
        this.priceRange = priceRange;
    }

    

public String getColor() {
    return color;
};

public String getBrand() {
    return brand;
}
public boolean getisElectrical() {
    return isElectrical;
}
public int getSize() {
    return size;
}
public float getPriceRange() {
    return priceRange;
}

}


