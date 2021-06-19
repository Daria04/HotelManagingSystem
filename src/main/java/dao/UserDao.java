package dao;

import model.User;

import java.util.List;

public interface UserDao extends CrudRepository<User> {

    List<User> getAllUsers();

}
