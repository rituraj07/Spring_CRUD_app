package com.WebApp3.WebApp3.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepo service;
    @Autowired
    private PostRepo postService;

    private static final Logger log =
            LoggerFactory.getLogger(userDaoServiceCmdRunner.class);

    @GetMapping(path = "/jpa/users")
    public List<User> getAll()
    {
        //log.info("/////////////////////////////////////////");
        //User user=new User("aman", new Date());
        //long insert =service.insert(user);
        //log.info("new user :"+ user);
        return service.findAll();
    }
    @GetMapping(path = "/jpa/users/{id}")
    public User getUser(@PathVariable int id)
    {
        Optional<User> foundUser = service.findById(id);
        /*SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept("name");
        FilterProvider filters= new SimpleFilterProvider().addFilter("myFilter",filter);
        MappingJacksonValue mapping =new MappingJacksonValue(user);
        mapping.setFilters(filters);*/
        if(!foundUser.isPresent())
        {
            throw new UserNotFoundException("id-"+id);
        }
       // else return foundUser;
        return foundUser.get();
    }
    @PostMapping("/jpa/user")
    public ResponseEntity<Object>  createUser(@Valid @RequestBody User user)
    {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand (savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/user/{id}")
    public void deleteUser(@PathVariable int id)
    {
       service.deleteById(id);


    }

    @GetMapping(path = "/jpa/user/{id}/posts")
    public List<Post> getAllPostbyUser(@PathVariable int id)
    {
        //log.info("/////////////////////////////////////////");
        //User user=new User("aman", new Date());
        //long insert =service.insert(user);
        //log.info("new user :"+ user);
        Optional<User> user=  service.findById(id);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("id- "+id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/user/{id}/posts")
    public ResponseEntity<Object>  createPost(@PathVariable int id,@RequestBody Post post)
    {
        Optional<User> user=  service.findById(id);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("id- "+id);
        }
        User foundUser = user.get();
        post.setUser(foundUser);
        postService.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


}
