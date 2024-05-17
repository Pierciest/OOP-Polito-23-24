package ClassExercises;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class App {
    public static void main(String[] args) throws Exception {
        
        Collection<Painting> hungPaintings = new LinkedList<Painting>();
        hungPaintings.add(new Painting("Gioconda"));
        hungPaintings.add(new Painting("The Scream"));
        hungPaintings.add(new Painting("Guernica"));
        System.out.println(hungPaintings.size());

        System.out.println();

        // addAll elements to another collection
        Collection<Painting> pArrayList = new ArrayList<Painting>();
        pArrayList.addAll(hungPaintings);

        // to array
        Painting[] paintingArray = pArrayList.toArray(new Painting[0]);
        System.out.println(paintingArray[0].getTitle());

        System.out.println();

        // Array - cannot set value in position out of bound
        // substitution

        // addition
        
        for (Painting p : paintingArray){
            System.out.println(p.getTitle());
        }

        System.out.println();

        // add, set get 
        ArrayList<Painting> morePaintings = new ArrayList<Painting>();

        // adding new elements
        morePaintings.add(new Painting("Gioconda"));
        morePaintings.add(new Painting("The Scream"));
        morePaintings.add(new Painting("Guernica"));


        for (Painting p : morePaintings){
            System.out.println(p.getTitle());
        }

        // adding elements in existing position

        morePaintings.add(1,new Painting("The last supper"));
        morePaintings.add(2,new Painting("The persistence of memory"));
        
        for (Painting p : morePaintings){
            System.out.println(p.getTitle());
        }
        // adding in new positions - ok
        morePaintings.add(new Painting("4 seasons"));


        System.out.println("Now size is " + morePaintings.size());
        

        // setting in new positions - out of bound exception
        

        // QUEUES

        // FIFO
        Queue<Integer> visitorsQueue = new LinkedList<Integer>();

        // Priority
        Queue<Integer> VIPQueue = new PriorityQueue<Integer>();

        // adding ticket numbers in order, FIFO vs priority
        
        // peeking into the two queues
        

        // SETS
        Set<Integer> artHistoryYears = new TreeSet<Integer>();

        // adding new years covered in art history by the gallery
        artHistoryYears.add(1990);
        artHistoryYears.add(2001);
        artHistoryYears.add(1867);
        artHistoryYears.add(1948);
        System.out.println("Now size is " + artHistoryYears.size());
        artHistoryYears.add(1941);
        System.out.println("Now size is " + artHistoryYears.size());
        artHistoryYears.add(2001);
        System.out.println("Now size is " + artHistoryYears.size());

        // natural order
        System.out.println("Natural order:");
        for (Integer i : artHistoryYears){
            System.out.println(i);
        }
        System.out.println();

        // with comparator - different constructor
        Set<Painting> uniquePieces = new TreeSet<Painting>(Painting::compareTo);

        System.out.println("Order set by compareTo implementation in Painting:");
        uniquePieces.add(new Painting("Double tree in a room"));
        uniquePieces.add(new Painting("A tree in outer space"));
        uniquePieces.add(new Painting("Ultimate galactic mission"));
        uniquePieces.add(new Painting("Random signs in red"));

        for (Painting p : uniquePieces){
            System.out.println(p.getTitle());
        }
        System.out.println();

        // ITERATOR
        List<Painting> paintingStorage = new LinkedList<Painting>();

        paintingStorage.add(new Painting("Double tree in a room"));
        paintingStorage.add(new Painting("Double tree in a room"));
        paintingStorage.add(new Painting("Untitled III"));
        paintingStorage.add(new Painting("The tree and the cat"));
        paintingStorage.add(new Painting("New York at sunset"));
        paintingStorage.add(new Painting("A young man playing lute"));
        paintingStorage.add(new Painting("New York at sunset"));

        
        // DIFFERENT CONSTRUCTS
        // direct usage: iterator is used until there is a next value
        
        // for-each syntax
        
        // iterable forEach() + lambda function or method reference
        

        // remove while iterating
        Integer count = 0;
        for (Iterator<Painting> in = paintingStorage.iterator(); in.hasNext();){
            in.next();
            if (count == 1){
                //numbers.remove(count); //ConcurrentModificationException
                
            }
            
            count++;
        }
        System.out.println();
        
        // add while iterating
        Integer count2 = 0;
        for (ListIterator<Painting> in = paintingStorage.listIterator(); in.hasNext();){
            in.next();
            if (count2 == 1){
                //numbers.add(count); //ConcurrentModificationException
                
            }
            count2++;
        }
    }
}
