package ch.bfh.loscompaneros.authorization.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    // There is no need to implement the controller in this example because we are using an in-memory user created by the authentication manager conf, but we do need to register the endpoint....

}
