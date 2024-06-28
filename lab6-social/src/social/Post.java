package social;

import java.sql.Time;

public class Post {
    String pid;
    String content;
    String author;
    long timeStamp;

    public Post(String pid, String content){
        this.pid = pid;
        this.content = content;
        this.timeStamp = System.currentTimeMillis();
    }

    public String getContent(){
        return this.content;
    }

    public long getTimestamp(){
        return this.timeStamp;
    }

    
}
