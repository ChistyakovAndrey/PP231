package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    public User getUserById(int id);

    void deleteUserById(Integer id);
}
