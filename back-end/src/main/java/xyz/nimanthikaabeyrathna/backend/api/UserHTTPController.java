package xyz.nimanthikaabeyrathna.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.nimanthikaabeyrathna.backend.business.custom.UserBO;
import xyz.nimanthikaabeyrathna.backend.dto.UserDTO;
import xyz.nimanthikaabeyrathna.backend.dto.UserFinder;
import xyz.nimanthikaabeyrathna.backend.entity.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
public class UserHTTPController {

    private final UserBO userBO;

    public UserHTTPController(UserBO userBO) {
        this.userBO = userBO;
    }


    @GetMapping
    public List<UserDTO> getAllUsers() throws Exception {
        return userBO.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Long> saveUser(@RequestBody UserDTO userDTO) throws Exception {
       Long generatedId = userBO.saveUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(generatedId);
    }


    @PostMapping(value = "/userFinder", consumes = "application/json")
    public ResponseEntity<?> findUser(@RequestBody UserFinder userFinder) {
        User user = userBO.findUser(userFinder);

        if (user != null) {
            // User found, return the user information
            return ResponseEntity.ok(user);
        } else {
            // User not found, return a response with an appropriate status code
            return ResponseEntity.notFound().build(); // You can customize this response as needed
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) throws Exception {
        userBO.deleteUser(id);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id,
                                  @RequestBody @Valid UserDTO userDTO) throws Exception {
        userBO.updateUser(userDTO);
    }
}
