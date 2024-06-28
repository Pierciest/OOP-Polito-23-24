package example;


import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import social.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class TestExample {
	private static final String MARIO99 = "Mario99";
	private Social f;

	@Before
	public void setUp() throws PersonExistsException{
		f = new Social();

		f.addPerson(MARIO99, "Mario", "Rossi");
		f.addPerson("Mario88", "Mario", "Verdi");
		f.addPerson("Elena66", "Elena", "Aresti");
		f.addPerson("BigLupo", "Lupo", "Bianchi");
		f.addPerson("FFA", "Franca", "Rosetti");
		f.addPerson("Sally", "Sandra", "Sandroni");
	}

	@Test
	public void testR1() throws NoSuchCodeException {
		
		String s = f.getPerson(MARIO99); // "Mario99 Mario Rossi"

		assertNotNull("Cannot retrieve person", s);
		assertTrue(s.contains(MARIO99));
		assertTrue(s.contains("Rossi"));	
	}

	@Test
	public void testR2() throws NoSuchCodeException {
		f.addFriendship(MARIO99, "BigLupo");
		f.addFriendship(MARIO99, "Elena66");
		f.addFriendship("Elena66", "FFA");
		f.addFriendship("Elena66", "Sally");
		f.addFriendship("BigLupo", "FFA");
		
		Collection<String> friends = f.listOfFriends(MARIO99); // "Elena66" "BigLupo"    
		assertNotNull("Missing list of friends", friends);
		assertTrue(friends.contains("Elena66"));
		assertTrue(friends.contains("BigLupo"));

		friends = f.friendsOfFriends(MARIO99); // "FFA" "Sally"
		assertTrue(friends.contains("FFA"));
		assertTrue(friends.contains("Sally"));		
	}

	@Test
	public void testR3() throws NoSuchCodeException {
		f.addGroup("torino");
		f.addGroup("poli");

		f.addPersonToGroup(MARIO99, "torino");
		f.addPersonToGroup("Elena66", "torino");
		f.addPersonToGroup(MARIO99, "poli");
		f.addPersonToGroup("FFA", "poli");

		Collection<String> s = f.listOfPeopleInGroup("poli");

		assertNotNull("Missing list of people in group", s);
		assertTrue(s.contains(MARIO99));
		assertTrue(s.contains("FFA"));
	}

	@Test
	public void testR4() throws NoSuchCodeException {
		f.addFriendship(MARIO99, "BigLupo");
		f.addFriendship(MARIO99, "Elena66");
		f.addFriendship("Elena66", "FFA");
		f.addFriendship("Elena66", "Sally");
		f.addFriendship("BigLupo", "FFA");
		
		f.addGroup("torino");
		f.addGroup("poli");
		f.addGroup("acolyte");

		f.addPersonToGroup(MARIO99, "torino");
		f.addPersonToGroup("Elena66", "torino");
		f.addPersonToGroup(MARIO99, "poli");
		f.addPersonToGroup("FFA", "acolyte");

		String id = f.personWithMostFriendsOfFriends(); // "FFA"
		assertEquals("Wrong person with most friends", "FFA", id);

		id = f.personInLargestNumberOfGroups();
		assertEquals("Wrong person with most friends", MARIO99, id);
	}

	@Test
	public void testR5() throws NoSuchCodeException, InterruptedException {
		f.addFriendship(MARIO99, "BigLupo");
		f.addFriendship(MARIO99, "Elena66");
		f.addFriendship("Elena66", "FFA");
		f.addFriendship("Elena66", "Sally");
		f.addFriendship("BigLupo", "FFA");

		long t0 = System.currentTimeMillis();
		Thread.sleep(5);
		String text = "Hello world!";
		String pid = f.post(MARIO99, text);

        assertNotNull("Missing post id", pid);

        assertEquals(f.getPostContent(MARIO99, pid), text );
        long t = f.getTimestamp(MARIO99, pid);
		assertTrue(t > t0);

		f.post(MARIO99, 2+text);
        Thread.sleep(5);
        f.post(MARIO99, 3+text);
        Thread.sleep(5);
        f.post(MARIO99, 4+text);
        Thread.sleep(5);
        String pid1 = f.post(MARIO99, 5+text);


		List<String> posts = f.getPaginatedUserPosts(MARIO99, 1, 3);
		assertNotNull(posts);
		assertEquals(3, posts.size());
		assertEquals(pid1, posts.get(0));
	}

}