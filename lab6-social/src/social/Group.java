package social;

import java.util.*;

public class Group {
    String groupName;
    Map<String,Person> members = new HashMap<>();

    public Group(String groupName){
        groupName = this.groupName;
    }
    public String getGroupName(){
        return this.groupName;
    }
    public void addMember(String personCode, Person person){
        this.members.put(personCode, person);
    }
    public Collection<String> memberList(){
        return this.members.keySet();
    }
    public int GetGroupSize(){
        return this.members.size();
    }
}
