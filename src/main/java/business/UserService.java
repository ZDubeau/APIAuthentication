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
        List<User> userList = new ArrayList<>();
        //userRepository.findAll().forEach(userList::add);
        userRepository.findAll();
        userList.forEach(e->userList.add(e));

        return userList;

    }


//    public List<User> getUser(Integer id) {
//        List<User> userList = new ArrayList<>();
//        userList.add(new User(1, "Zahra", "db@db.com", "123456789"));
//        userList.add(new User(2, "Axel", "Sl@sl.com", "987654321"));
//
//        return userList;
//    }

    public User getUserById(int id) {
        Predicate<User> userById = p -> Objects.equals(p.getId(), id);

        return filterUser(userById);
    }

    private User filterUser(Predicate<User> userId) {

        return getUser().stream().filter(userId).findFirst().orElse(null);
    }

    public User addUser(User newUser) {
        return userRepository.save(newUser);
    }
}
