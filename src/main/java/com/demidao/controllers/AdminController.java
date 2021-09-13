package com.demidao.controllers;

import com.demidao.models.User;
import com.demidao.services.RoleService;
import com.demidao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
    }

    @GetMapping("/show-users")
    public ResponseEntity<List<User>> index() {
        final List<User> users = userService.index();
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        User signedUser = userService.findByEmail(userService.loadUserByUsername(principal.getName()).getUsername());
        return signedUser != null
                ? new ResponseEntity<>(signedUser, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-user")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody String user) {
        userService.saveJSON(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update-user")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody String user) {
        final boolean updated = userService.updateJSON(user);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
