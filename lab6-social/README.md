OOP Lab 6 - Social Network
==============

(the Italian version is available in file [README_it.md](README_it.md)).


Develop an application to support a social network. All classes must be
in the package `social`.

------------------------------------------------------------------------

R1 - Subscription
-----------------

The interaction with the system is made using class `Social`.

You can register new account using method `addPerson()` which receives
as parameters a unique code, name and surname.

The method throws the exception `PersonExistsException` if the code
passed is already associated to a subscription.

The method `getPerson()` returns a string containing code, name and
surname of the person, in order, separated by blanks. If the code,
passed as a parameter, does not match any person, the method throws the
exception `NoSuchCodeException`.


R2 - Friends
------------

Any person, registered in the social, should have possibility to add
friends. Friendship is bidirectional: if person A is friend of a person
B, that means that person B is a friend of a person A. 

Friendship is created using method `addFriendship()` that receives as arguments the
codes of both persons. The method throws the exception `NoSuchCodeException`
if one or both codes do not exist.

Method `listOfFriends()` receives as argument the code of a person and
returns the collection of his/her friends. The exception
`NoSuchCodeException` is thrown if the code does not exist. If a
person has no friends, an empty collection is returned.

The method `friendsOfFriends` receives as a parameter the code of a
person and returns the collection of the code of the friends of his/her friends,
i.e. friends of the second level. The exception `NoSuchCodeException`
is thrown if the code does not exist. If the list is empty, the method
returns an empty collection. The list should not contain the person whose
code was passed as parameter. (i.e. *"remove yourself from the
list"*)

The method `friendsOfFriendsNoRepitition()` returns the list of codes
of friends of the second level (friends of the friends), like the
previous method, with the difference that it should delete duplicated persons.
If the list is empty, the method should return an empty collection. 
The exception `NoSuchCodeException` is thrown if the
code does not exist.


R3 - Groups
-----------

It is possible to register a new group using method `addGroup()`. Name
of the group should be a single word.

The method `listOfGroups()` returns the list of names of all
registered groups. If there are no groups in the list, the method should
return an empty collection.

A person can subscribe to a group through the method `addPersonToGroup()`
that receives as arguments the code of the person and the name of the
group. In case the code of the person, or the name of the group are
not found a `NoSuchCodeException` is thrown.

Method `listOfPeopleInGroup()` returns collection of codes of people
subscribed to a given group. Returns null if the group does not exist.


R4 - Statistics
---------------

Method `personWithLargestNumberOfFriends()` returns code of a person
that has the largest amount of friends (first level). Do not consider the
case of ties.

Method `personWithMostFriendsOfFriends()` returns code of a person
that has the largest amount of the friends of friends (second level). Do not
consider the case of ties.

Method `largestGroup()` returns name of the group with the largest number
of members. Do not consider the case of ties.

Method `personInLargestNumberOfGroups()` returns code of a person that
is subscribed to the largest number of groups. Do not consider the case of
ties.


R5 - Posts
----------

It is possible to add a new post by a given account using the method `post()` 
that accepts as arguments the unique code of the person posting, and the text content.
The method returns a unique id for the post containing digits and letters only.

Given an author and a post id it is possible to get:

- the timestamp with `getTimestamp()`,
- the text content with `getPostContent()`.

The timestamp of the post is the current system time at the momento fo the post
creation (retrieved through `System.currentTimeMillis()`).

The paginated list of all posts of *a given user* can be retrieved using the method
`getPaginatedUserPosts()` that accepts the user id, the page number (1 is the first)
and the page length. The method returns the ids of the posts sorted by 
descending timestamp. The list is split into pages, each containing a 
number of posts specified by the page-length.
E.g. if page length is 5 and page is 2, then posts with position 6 to 10 are returned.
Post are sorted by descenging timestamp, i.e., most recent first.

The paginated list of all posts of *the friends of a given user* be retrieved using the method
`getPaginatedFriendPosts()` that accepts the user id, the page number (1 is the first)
and the page length. The method works like the previous one but it returns in the list both
the author name and the post id, separated by `":"`, e.g. `"elon:123wtf"`.
