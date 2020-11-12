package com.mt.springmongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ComponentScan(basePackages = "com.mt.*")
public class UserController {
    private final UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }
    @RequestMapping("/save")
    public String save(@RequestParam("firstName") String firstName,
                       @RequestParam("lastName") String lastName,
                       @RequestParam("email") String email) {

    	logger.info("Creating user name: "+firstName);
        User user = new User(firstName, lastName, email);
        userRepository.save(user);

        return "index.html";
    }
}
