package database;

import bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


public interface Userdata{
    //get
    List<User> getAll() throws SQLException;

    //getone
    List<User> getone(int id) throws SQLException;

    //DELETE
    public int delete(int id) throws SQLException;

}