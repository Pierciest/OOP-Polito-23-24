
import java.util.function.BiFunction;
public class Encoder implements BiFunction<String, Integer, String>{

        @Override
        public String apply(String s, Integer key) {
            StringBuffer sb=new StringBuffer(s);
			for(int i=0; i<sb.length(); i++) {
				if(i%2==0) {
					sb.setCharAt(i, (char)(sb.charAt(i)+key));
				}
				
			}
			return new String(sb);
                                
        }

}