package src.Users;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public Users() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        if (user != null) {
            users.add(user);
            System.out.println("User added to the list");
        }
    }

    public User findUser(String query) {
        for (User user : users) {
            if (user.getName().contains(query) || user.getEmail().contains(query)) {
                return user;
            }
        }
        return null;
    }

}
