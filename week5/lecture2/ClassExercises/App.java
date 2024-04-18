public class App {
    public static void main(String[] args) throws Exception{
        Pair<String> sp = new Pair<>("first","second");
        Pair<Double> fp = new Pair<>(1.23,3.01);
    
        String s1 = sp.getFirst();
        String s2 = sp.getSecond();
    
        Double f1 = fp.getFirst();
        Double f2 = fp.getSecond();
    
        MixedPair<String, Double> sdp = new MixedPair("hey",1.66);
        String sdp1 = sdp.getFirst();
        Double sdp2 = sdp.getSecond();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(sdp1);
        System.out.println(sdp2);
    }


}
