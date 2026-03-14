package com.bwardweb.kafka_messenger.controller;

import com.bwardweb.kafka_messenger.model.UserDTO;
import com.bwardweb.kafka_messenger.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable(required = true) String name) {
        UserDTO userDTO = userService.getUserDTOByName(name);

        if(userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.notFound().build();
    }
}
