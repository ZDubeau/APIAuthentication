package api;

import business.UserService;
import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUser() {
        return userService.getUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int userId) {
        if()
        try {
            User user = userService.getUserById(userId);
            return new ResponseEntity<User> (user, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
//    with @RequestBody we convert http contain to json
    @PostMapping("/user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }
}
