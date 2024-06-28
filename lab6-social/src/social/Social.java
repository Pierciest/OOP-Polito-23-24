package social;

import java.util.*;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;


public class Social {

	/**
	 * Creates a new account for a person
	 * 
	 * @param code	nickname of the account
	 * @param name	first name
	 * @param surname last name
	 * @throws PersonExistsException in case of duplicate code
	 */
	Map<String, Person> persons = new HashMap<>();
	Map<String,Group> groups = new HashMap<>();

	public void addPerson(String code, String name, String surname)
			throws PersonExistsException {
				Person curPerson = persons.get(code);
				if(curPerson != null){
					throw new PersonExistsException();
				}
				Person newPerson = new Person(code, name, surname);
				persons.put(code, newPerson);
	}

	/**
	 * Retrieves information about the person given their account code.
	 * The info consists in name and surname of the person, in order, separated by blanks.
	 * 
	 * @param code account code
	 * @return the information of the person
	 * @throws NoSuchCodeException
	 */
	public String getPerson(String code) throws NoSuchCodeException {
		Person curPerson = persons.get(code);
		if(curPerson == null){
			throw new NoSuchCodeException();
		}
		return curPerson.code + " " + curPerson.name + " " + curPerson.surname;
	}

	/**
	 * Define a friendship relationship between to persons given their codes.
	 * 
	 * Friendship is bidirectional: if person A is friend of a person B, that means that person B is a friend of a person A.
	 * 
	 * @param codePerson1	first person code
	 * @param codePerson2	second person code
	 * @throws NoSuchCodeException in case either code does not exist
	 */
	public void addFriendship(String codePerson1, String codePerson2)
			throws NoSuchCodeException {
				Person curPerson = persons.get(codePerson1);
				Person newFriend = persons.get(codePerson2);

				if(curPerson == null || newFriend == null){
					throw new NoSuchCodeException();
				}

				curPerson.addFriendship(newFriend);
				newFriend.addFriendship(curPerson);
	}

	/**
	 * Retrieve the collection of their friends given the code of a person.
	 * 
	 * 
	 * @param codePerson code of the person
	 * @return the list of person codes
	 * @throws NoSuchCodeException in case the code does not exist
	 */
	public Collection<String> listOfFriends(String codePerson) throws NoSuchCodeException {
		Person curPerson = persons.get(codePerson);
		if (curPerson == null) {
			throw new NoSuchCodeException();
		}

		Collection<String> friendsList = curPerson.friends.keySet();
		return friendsList;
	}

	/**
	 * Retrieves the collection of the code of the friends of the friends
	 * of the person whose code is given, i.e. friends of the second level.
	 * 
	 * 
	 * @param codePerson code of the person
	 * @return collections of codes of second level friends
	 * @throws NoSuchCodeException in case the code does not exist
	 */
	public Collection<String> friendsOfFriends(String codePerson) throws NoSuchCodeException {
		Person curPerson = persons.get(codePerson);
		if (curPerson == null){
			throw new NoSuchCodeException();
		}
		Collection<String> friendsOfFriendsList = curPerson.friends.values().stream()
				.flatMap(friend -> friend.friends.keySet().stream())
				.filter(code -> code != codePerson)
				.collect(Collectors.toList());
		return friendsOfFriendsList;
	}

	/**
	 * Retrieves the collection of the code of the friends of the friends
	 * of the person whose code is given, i.e. friends of the second level.
	 * The result has no duplicates.
	 * 
	 * 
	 * @param codePerson code of the person
	 * @return collections of codes of second level friends
	 * @throws NoSuchCodeException in case the code does not exist
	 */
	public Collection<String> friendsOfFriendsNoRepetition(String codePerson) throws NoSuchCodeException{
		Person curPerson = persons.get(codePerson);
		if (curPerson == null){
			throw new NoSuchCodeException();
		}
		Collection<String> friendsOfFriendsList = curPerson.friends.values().stream()
				.flatMap(friend -> friend.friends.keySet().stream())
				.distinct()
				.filter(code -> code != codePerson)
				.collect(Collectors.toList());
		return friendsOfFriendsList;
	}

	/**
	 * Creates a new group with the given name
	 * 
	 * @param groupName name of the group
	 */
	public void addGroup(String groupName) {
		if(groupName.split(" ").length > 1){
			System.out.println("The group name should be single word");
		}
		Group curGroup = groups.get(groupName);
		if(curGroup != null){
			System.out.println("Group name already in use");
		}
		Group newGroup = new Group(groupName);
		groups.put(groupName, newGroup);
	}

	/**
	 * Retrieves the list of groups.
	 * 
	 * @return the collection of group names
	 */
	public Collection<String> listOfGroups() {
		return groups.keySet();
	}

	/**
	 * Add a person to a group
	 * 
	 * @param codePerson person code
	 * @param groupName  name of the group
	 * @throws NoSuchCodeException in case the code or group name do not exist
	 */
	public void addPersonToGroup(String codePerson, String groupName) throws NoSuchCodeException {
		Person curPerson = persons.get(codePerson);
		if(curPerson == null){
			throw new NoSuchCodeException();
		}
		Group curGroup = groups.get(groupName);
		if(curGroup == null){
			System.out.println("Group does not exist");
		}
		curPerson.subscribeToGroup(groupName, curGroup);
		curGroup.addMember(codePerson, persons.get(codePerson));
	}

	/**
	 * Retrieves the list of people on a group
	 * 
	 * @param groupName name of the group
	 * @return collection of person codes
	 */
	public Collection<String> listOfPeopleInGroup(String groupName) {
		Group curGroup = groups.get(groupName);
		return curGroup.memberList();
	}

	/**
	 * Retrieves the code of the person having the largest
	 * group of friends
	 * 
	 * @return the code of the person
	 */
	public String personWithLargestNumberOfFriends() {
		Optional<Person> personWithMostFriends = persons.values().stream().max(Comparator.comparingInt(Person::getNumberOfFriends));
		return personWithMostFriends.map(Person::getCode).orElse(null);
	}

	/**
	 * Find the code of the person with largest number
	 * of second level friends
	 * 
	 * @return the code of the person
	 */

	public String personWithMostFriendsOfFriends() {
		int max = 0;
		Person popPerson = new Person(null, null, null);
		for (Person p : persons.values()) {
			try {
				if(max < friendsOfFriendsNoRepetition(p.code).size()){
					max = friendsOfFriendsNoRepetition(p.code).size();
					popPerson = p;
				}
			} catch (NoSuchCodeException e) {
				// Handle the exception here
			}
		}
		return popPerson.code;
	}

	/**
	 * Find the name of group with the largest number of members
	 * 
	 * @return the name of the group
	 */
	public String largestGroup() {
		Optional<Group> largestGroup = groups.values().stream().max(Comparator.comparingInt(Group :: GetGroupSize));
		return largestGroup.map(Group :: getGroupName).orElse(null);
	}

	/**
	 * Find the code of the person that is member of
	 * the largest number of groups
	 * 
	 * @return the code of the person
	 */
	public String personInLargestNumberOfGroups() {
		Optional <Person> largestNumberOfGroups = persons.values().stream().max(Comparator.comparing(Person :: getGroupCount));
		return largestNumberOfGroups.map(Person :: getCode).orElse(null);
	}

	/**
	 * add a new post by a given account
	 * @param author the id of the post author
	 * @param text the content of the post
	 * @return a unique id of the post
	 */
    public String post(String author, String text) {
		Person curPerson = persons.get(author);
		String pid = author + curPerson.pidNumber;
		Post newPost = new Post(pid,text);
		curPerson.addPost(pid, newPost);
		return pid;
    }

	/**
	 * retrieves the content of the given post
	 * @param author	the author of the post
	 * @param pid		the id of the post
	 * @return the content of the post
	 */
    public String getPostContent(String author, String pid) {
		Person curPerson = persons.get(author);
		Post curPost = curPerson.posts.get(pid);
		return curPost.content;
    }

	/**
	 * retrieves the timestamp of the given post
	 * @param author	the author of the post
	 * @param pid		the id of the post
	 * @return the timestamp of the post
	 */
    public long getTimestamp(String author, String pid) {
		Person curPerson = persons.get(author);
		Post curPost = curPerson.posts.get(pid);
		return curPost.getTimestamp();
    }

	/**
	 * returns the list of post of a given author paginated 
	 * 
	 * @param author	author of the post
	 * @param pageNo	page number (starting at 1)
	 * @param pageLength page length
	 * @return the list of posts id
	 */
    public List<String> getPaginatedUserPosts(String author, int pageNo, int pageLength) {
		Person curPerson = persons.get(author);
		List<Post> result = curPerson.posts.values().stream().sorted(Comparator.comparing(Post :: getTimestamp).reversed()).toList();
		ArrayList<String> desiredPages = new ArrayList<String>();
		for(int i = pageLength*(pageNo-1); i < pageLength*pageNo && i < result.size(); i++){
			desiredPages.add(result.get(i).pid);
		}
		return desiredPages;
    }

	/**
	 * returns the paginated list of post of friends 
	 * 
	 * the returned list contains the author and the id of a post separated by ":"
	 * 
	 * @param author	author of the post
	 * @param pageNo	page number (starting at 1)
	 * @param pageLength page length
	 * @return the list of posts key elements
	 */
	public List<String> getPaginatedFriendPosts(String author, int pageNo, int pageLength) {
		Person curPerson = persons.get(author);
		Collection<Person> friendsOfFriendsList = curPerson.friends.values().stream()
				.flatMap(friend -> friend.friends.values().stream())
				.filter(person -> person.code != author)
				.collect(Collectors.toList());
		List<String> feed = friendsOfFriendsList.stream().flatMap(friend -> friend.posts.values().stream().sorted(Comparator.comparing(Post :: getTimestamp).reversed())).map(post -> post.author + ":" + post.pid).collect(Collectors.toList());
		ArrayList<String> desiredPages = new ArrayList<>();
		for(int i = pageLength*(pageNo-1); i < pageLength*pageNo && i < feed.size(); i++){
			desiredPages.add(feed.get(i));
		}
		return desiredPages;
	}
}