package com.example.mavennew.Controller;


import bean.User;
import database.Userdata;
import database.Userdataimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class UserControl {

    Userdata userdata;

    public UserControl() {
        userdata = new Userdataimpl();
    }


    //getall
    @RequestMapping(value = "/getall")
    public String getall() throws SQLException {
        return userdata.getAll().toString();
    }

    //getone
    @GetMapping(value = "/getone/{id}")
    public String getone(@PathVariable int id) throws SQLException {
        return userdata.getone(id).toString();
    }

    //Create new or POST
    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public void create(@RequestBody User user) throws SQLException {
        userdata.create(user);
    }

    //update
    @RequestMapping(method = RequestMethod.PUT, value = "/put/{id}")
    public void update(@RequestBody User user, @PathVariable String id) throws SQLException {
        userdata.update(user, id);
    }


    //deleteone
    @GetMapping(value="/delete/{id}")
    public int ser(@PathVariable int id) throws SQLException {
        return userdata.delete(id);
    }

}
