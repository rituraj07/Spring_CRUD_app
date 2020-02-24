package com.WebApp3.WebApp3.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Component
@Repository
@Transactional
public class UserDaoService {
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger log =
            LoggerFactory.getLogger(userDaoServiceCmdRunner.class);

    public long insert(User user)
    {
        log.info("herehereherehereherehereherehereherehere");
        entityManager.persist(user);
        return user.getId();
    }
    private static List<User> users = new ArrayList<>();
    private static int usersCount=4;
  /*  static{
        users.add(new User(1,"aman", new Date()));
        users.add(new User(2,"vik", new Date()));
        users.add(new User(3,"adi", new Date()));
    }*/
    public List<User> findAll()
    {
        return users;
    }
    public User save(User user)
    {
        //Integer usedId=user.getId();
        //if(user.getId()==""||user.getId() == null)
        //{
        //    user.setId(++usersCount);
        //}
        users.add(user);
        return user;
    }
    public User findOne(int id)
    {
        for(User user:users)
        {
            if(user.getId()==id)
                return user;
        }
        return null;
    }
    public User deleteById(int id)
    {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext())
        {
            User user= iterator.next();
            if(user.getId()==id)
            {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
