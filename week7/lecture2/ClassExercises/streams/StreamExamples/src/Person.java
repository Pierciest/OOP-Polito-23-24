import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {
    
	private String name;
	private int age;
    private List<String> hobbies;
     
     Person(String name, int age, List<String> hobbies){
    	 this.name=name;
    	 this.hobbies=hobbies;
    	 this.age=age;
     }
     
    public List<String> getHobbies(){
    	return hobbies;
    }
    
    public String getName(){
    	return name;
    }
    
    public int getAge(){
    	return age;
    }
    
}
