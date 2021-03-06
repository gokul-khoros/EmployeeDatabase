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

    @Override
    public void create(User user) throws SQLException {
        PreparedStatement stat = conc.prepareStatement("insert into employee (id,firstname,address,salary) values(?,?,?,?)");
        stat.setInt(1, user.getId());
        stat.setString(2,user.getName());
        stat.setString(3,user.getAddress());
        stat.setInt(4,user.getSalary());
        stat.executeUpdate();

    }

    @Override
    public void update(User user, String id) throws SQLException {
        PreparedStatement stat = conc.prepareStatement(" UPDATE employee SET firstname = ?, address=?, salary=? WHERE id = ?;");
        stat.setString(1,user.getName());
        stat.setString(2,user.getAddress());
        stat.setInt(3,user.getSalary());
        stat.setInt(4, Integer.parseInt(id));
        stat.executeUpdate();
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