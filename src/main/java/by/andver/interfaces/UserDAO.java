package by.andver.interfaces;

import by.andver.objects.User;

public interface UserDAO {
    void saveUser (User user);
    User findById(Integer id);

}
