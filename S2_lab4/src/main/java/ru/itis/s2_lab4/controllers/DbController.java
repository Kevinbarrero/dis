package ru.itis.s2_lab4.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.s2_lab4.orm.EntityManagerFactory;

import java.io.IOException;

@Controller
public class DbController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @RequestMapping("/")
    public void writer(java.io.Writer writer) throws IOException {
        writer.write(entityManagerFactory.testDbWork());
    }


    @RequestMapping("/add")
    public void add(java.io.Writer writer) throws IOException {
        /*
        User user = new User();
        user.seId(random);
        user.setName(...);
        EntityManager em = entityManagerFactory.getEntityManager();
        em.save(user);
        entityManagerFactory.closeEntityManager();
        */
        writer.write(entityManagerFactory.testDbWork());
    }

    @RequestMapping("/edit")
    public void edit(@RequestParam("id") Long id) throws IOException {
        /*
        EntityManager em = entityManagerFactory.getEntityManager();
        User user = em.find(User.class, id);
        user.setName(.....);
        em.merge(user);
        entityManagerFactory.closeEntityManager();
        */
    }

}