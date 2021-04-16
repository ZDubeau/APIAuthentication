package business;

import model.User;
import repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUser() {
        List<User> userList = (List<User>) userRepository.findAll();
        return userList;
    }

    public User getUserById(int id) {
        Predicate<User> userById = p -> Objects.equals(p.getId(), id);

        return filterUser(userById);
    }

    private User filterUser(Predicate<User> userId) {

        return getUser().stream().filter(userId).findFirst().orElse(null);
    }

    public boolean addUser(User newUser) {
        try {
            userRepository.save(newUser);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
