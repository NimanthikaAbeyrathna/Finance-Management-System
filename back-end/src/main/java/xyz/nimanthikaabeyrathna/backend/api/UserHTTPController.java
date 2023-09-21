package xyz.nimanthikaabeyrathna.backend.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.nimanthikaabeyrathna.backend.business.custom.UserBO;
import xyz.nimanthikaabeyrathna.backend.dto.UserDTO;

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
    public void saveUser(@RequestBody UserDTO userDTO) throws Exception {
        userBO.saveUser(userDTO);
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
