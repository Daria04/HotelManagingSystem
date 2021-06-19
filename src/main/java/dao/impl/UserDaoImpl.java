package dao.impl;

import connection.FactoryConnection;
import dao.CrudRepository;
import dao.UserDao;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements CrudRepository<User>, UserDao {

    private static UserDaoImpl instance;
    private static final String CREATE_USER = "INSERT INTO users (name, lastName, passportData, email, phoneNumber) VALUES (?,?,?,?,?);";
    private static final String GET_USER = "SELECT * FROM users WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE users SET name = ?, lastName = ?, passportData = ?, email = ?, phoneNumber = ? WHERE id = ?;";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?;";
    private static final String GET_ALL_USERS = "SELECT * FROM users;";

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {

        if (instance == null) {
                    instance = new UserDaoImpl();
                }

        return instance;
    }

    @Override
    public User create(User entity) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setLong(3, entity.getPassportData());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setLong(5, entity.getPhoneNumber());

            int i = preparedStatement.executeUpdate();

            if (i > 0) {

                try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        entity.setUserId(keys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public User read(int id) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                long passportData = resultSet.getLong(4);
                String email = resultSet.getString(5);
                long phoneNumber = resultSet.getLong(6);

                User user = User.builder()
                        .userId(userId)
                        .phoneNumber(phoneNumber)
                        .passportData(passportData)
                        .email(email)
                        .lastName(lastName)
                        .name(name)
                        .build();

                return user;
            }
            preparedStatement.executeUpdate();

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User update(User entity) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {

            User oldUser = read(entity.getUserId());

            if (oldUser != null) {

                int oldUserId = oldUser.getUserId();
                String newName = entity.getName();
                String newLastName = entity.getLastName();
                long newPassportData = entity.getPassportData();
                long newPhoneNumber = entity.getPhoneNumber();
                String newEmail = entity.getEmail();

                preparedStatement.setString(1, newName);
                preparedStatement.setString(2, newLastName);
                preparedStatement.setLong(3, newPassportData);
                preparedStatement.setString(4, newEmail);
                preparedStatement.setLong(5, newPhoneNumber);
                preparedStatement.setInt(6, oldUserId);

                preparedStatement.executeUpdate();

                return oldUser;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(int id) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                User user = User.builder()
                        .userId(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .lastName(rs.getString("lastName"))
                        .phoneNumber(rs.getLong("phoneNumber"))
                        .passportData(rs.getLong("passportData"))
                        .email(rs.getString("email"))
                        .build();

                list.add(user);
            }
        }
        catch (SQLException e) {
        e.printStackTrace();
    }
        return list;
    }

}

