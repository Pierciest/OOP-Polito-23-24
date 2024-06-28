package social;
import java.util.Collection;
import java.util.List;

public class main {
    public static void main(String[] args) throws NoSuchCodeException, PersonExistsException {
        Social f;
        f = new Social();

        f.addPerson("A", "Mario", "Verdi");


        System.out.println(f.persons.entrySet());

        
    }
}
