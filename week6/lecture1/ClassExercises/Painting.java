package ClassExercises;

public class Painting implements Comparable<Painting> {

    private String title;

    public Painting(String title){
        this.title=title;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public int compareTo(Painting other) {
        
        return this.title.compareTo(other.getTitle());
    }

    

}

