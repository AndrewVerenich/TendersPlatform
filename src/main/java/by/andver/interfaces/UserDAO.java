package by.andver.interfaces;

import by.andver.objects.User;

public interface UserDAO {
    void saveUser (User user);
    User findUserById(Integer id);
    void deleteUser (User user);
    void updateUser (User user);
}
