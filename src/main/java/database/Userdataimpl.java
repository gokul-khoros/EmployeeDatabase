package database;

import bean.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Userdataimpl implements Userdata{


    List<User> users = new ArrayList<User>();
    Connection conc;
    {
        try {
            conc = DriverManager.getConnection("jdbc:mysql://localhost:3306/heros", "root", "Goksravi99.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //GETALL
    public List<User> getAll() throws SQLException {
            Statement stat= conc.createStatement();
            ResultSet res=stat.executeQuery(" select *  from employee;");
            while(res.next()) {
                User user = new User();
                user.setId(res.getInt("id"));
                user.setAddress(res.getString("address"));
                user.setName(res.getString("firstname"));
                user.setSalary(res.getInt("salary"));
                users.add(user);
            }
        return users;
    }

    //GETONE WITH ID
    @Override
    public List<User> getone(int id) throws SQLException {
        /*Userdata udi= new Userdataimpl();
        udi.getAll();
        users.stream() ;*/

        PreparedStatement stat = conc.prepareStatement("select * from employee where id= ?");
        stat.setInt(1, id);
        ResultSet res = stat.executeQuery();
        while (res.next()) {
            User user = new User();
            user.setId(res.getInt("id"));
            user.setAddress(res.getString("address"));
            user.setName(res.getString("firstname"));
            user.setSalary(res.getInt("salary"));
            users.add(user);
        }
        return users;
    }


    //DELETE WITH ID
    @Override
    public int delete(int id) throws SQLException {
        PreparedStatement stat = conc.prepareStatement("delete from employee where id= ?;");

            stat.setInt(1, id);
            int res=stat.executeUpdate();

        return 0;
    }

}