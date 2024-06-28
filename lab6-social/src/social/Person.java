package social;

import java.util.*;

public class Person {
    String code;
    String name;
    String surname;
    int pidNumber = 0;

    Map<String,Person> friends = new HashMap<>();
    Map<String,Group> subscribedGroups = new HashMap<>();
    Map<String,Post> posts = new HashMap<>();

    public Person(String code, String name, String surname){
        this.code = code;
        this.name = name;
        this.surname = surname;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void addFriendship(Person friend){
        this.friends.put(friend.code, friend);
    }

    public Person getFriend(String code){
        return this.friends.get(code);

    }
    
    public int getNumberOfFriends(){
        return this.friends.size();
    }
    public void subscribeToGroup(String groupName, Group group){
        this.subscribedGroups.put(groupName, group);
    }
    public int getGroupCount(){
        return this.subscribedGroups.size();
    }
    public void addPost(String pid, Post post){
        this.posts.put(pid, post);
        this.pidNumber++;
    }
}
