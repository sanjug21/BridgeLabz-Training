import java.util.*;

class User {
    int userId;
    String name;
    int age;
    LinkedList<Integer> friendIds; // Nested list for friends

    User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new LinkedList<>();
    }
}



public class SocialMediaConnections {
    static Scanner sc = new Scanner(System.in);
    Node<User> head;

    public void addUser(User user) {
        Node<User> nn = new Node<User>(user);
        if (head == null) {
            head = nn;
        } else {
            Node<User> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = nn;
        }
        System.out.println("User " + user.name + " added successfully.");
    }

    public User findUserById(int id) {
        Node<User> temp = head;
        while (temp != null) {
            if (temp.data.userId == id) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public void addFriendConnection(int userId1, int userId2) {
        if (userId1 == userId2) {
            System.out.println("Cannot add self as friend.");
            return;
        }
        User u1 = findUserById(userId1);
        User u2 = findUserById(userId2);

        if (u1 == null || u2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (u1.friendIds.contains(userId2)) {
            System.out.println("They are already friends.");
            return;
        }

        u1.friendIds.add(userId2);
        u2.friendIds.add(userId1);
        System.out.println("Friend connection added between " + u1.name + " and " + u2.name);
    }

    public void removeFriendConnection(int userId1, int userId2) {
        User u1 = findUserById(userId1);
        User u2 = findUserById(userId2);

        if (u1 == null || u2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (u1.friendIds.contains(userId2)) {
            u1.friendIds.remove(Integer.valueOf(userId2)); // Remove object, not index
            u2.friendIds.remove(Integer.valueOf(userId1));
            System.out.println("Friend connection removed.");
        } else {
            System.out.println("They are not friends.");
        }
    }

    public void findMutualFriends(int userId1, int userId2) {
        User u1 = findUserById(userId1);
        User u2 = findUserById(userId2);

        if (u1 == null || u2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        System.out.println("Mutual friends between " + u1.name + " and " + u2.name + ":");
        boolean found = false;
        for (int friendId : u1.friendIds) {
            if (u2.friendIds.contains(friendId)) {
                User mutual = findUserById(friendId);
                if (mutual != null) {
                    System.out.println("- " + mutual.name + " (ID: " + mutual.userId + ")");
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No mutual friends found.");
    }

    public void displayFriends(int userId) {
        User u = findUserById(userId);
        if (u == null) {
            System.out.println("User not found.");
            return;
        }
        System.out.println("Friends of " + u.name + ":");
        if (u.friendIds.isEmpty()) {
            System.out.println("No friends added yet.");
            return;
        }
        for (int fid : u.friendIds) {
            User friend = findUserById(fid);
            if (friend != null) {
                System.out.println("- " + friend.name + " (ID: " + friend.userId + ")");
            }
        }
    }

    public void searchUser(String query) {
        Node<User> temp = head;
        boolean found = false;
        System.out.printf("%-10s %-20s %-5s %-10s%n", "ID", "Name", "Age", "Friends");
        System.out.println("------------------------------------------------");
        while (temp != null) {
            String uidStr = String.valueOf(temp.data.userId);
            if (uidStr.equals(query) || temp.data.name.equalsIgnoreCase(query)) {
                System.out.printf("%-10d %-20s %-5d %-10d%n",
                        temp.data.userId, temp.data.name, temp.data.age, temp.data.friendIds.size());
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No user found matching: " + query);
    }

    public void countFriends() {
        Node<User> temp = head;
        System.out.printf("%-20s %-10s%n", "User Name", "Friend Count");
        System.out.println("------------------------------");
        while (temp != null) {
            System.out.printf("%-20s %-10d%n", temp.data.name, temp.data.friendIds.size());
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        SocialMediaConnections smc = new SocialMediaConnections();
        System.out.println("==== Social Media Friend Connections ====");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add User");
            System.out.println("2. Add Friend Connection");
            System.out.println("3. Remove Friend Connection");
            System.out.println("4. Find Mutual Friends");
            System.out.println("5. Display Friends of User");
            System.out.println("6. Search User");
            System.out.println("7. Count Friends for All");
            System.out.println("8. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter User ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    smc.addUser(new User(id, name, age));
                    break;
                case 2:
                    System.out.println("Enter User ID 1: ");
                    int id1 = sc.nextInt();
                    System.out.println("Enter User ID 2: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    smc.addFriendConnection(id1, id2);
                    break;
                case 3:
                    System.out.println("Enter User ID 1: ");
                    int rid1 = sc.nextInt();
                    System.out.println("Enter User ID 2: ");
                    int rid2 = sc.nextInt();
                    sc.nextLine();
                    smc.removeFriendConnection(rid1, rid2);
                    break;
                case 4:
                    System.out.println("Enter User ID 1: ");
                    int mid1 = sc.nextInt();
                    System.out.println("Enter User ID 2: ");
                    int mid2 = sc.nextInt();
                    sc.nextLine();
                    smc.findMutualFriends(mid1, mid2);
                    break;
                case 5:
                    System.out.println("Enter User ID: ");
                    int fid = sc.nextInt();
                    sc.nextLine();
                    smc.displayFriends(fid);
                    break;
                case 6:
                    System.out.println("Enter Name or ID to search: ");
                    String q = sc.nextLine();
                    smc.searchUser(q);
                    break;
                case 7: smc.countFriends(); break;
                case 8: System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }
}