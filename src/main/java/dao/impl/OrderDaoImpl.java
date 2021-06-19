package dao.impl;

import connection.FactoryConnection;
import dao.CrudRepository;
import dao.OrderDao;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements CrudRepository<Order>, OrderDao {

    private static OrderDaoImpl instance;
    private static final String GET_ALL_ORDERS = "SELECT * FROM orders;";
    private static final String CREATE_ORDER = "INSERT INTO orders (userId, ordersAptId, dateIn, dateOut) VALUES (?,?,?,?);";
    private static final String GET_ORDER = "SELECT * FROM orders WHERE id = ?;";
    private static final String UPDATE_ORDER = "UPDATE orders SET userId = ?, ordersAptId = ?, dateIn = ?, dateOut = ? WHERE id = ?;";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE id = ?;";
    private static final String GET_CHOSEN_ORDERS = "SELECT * FROM orders WHERE ordersAptId = ?;";

    private OrderDaoImpl() {

    }

    public static OrderDaoImpl getInstance() {

        if (instance == null) {

            instance = new OrderDaoImpl();
        }

        return instance;
    }

    @Override
    public Order create(Order entity) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getAptId());
            preparedStatement.setDate(3, java.sql.Date.valueOf(entity.getDateIn()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(entity.getDateOut()));

            int i = preparedStatement.executeUpdate();

            if (i > 0) {

                try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        entity.setId(keys.getInt(1));
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Order read(int id) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER)) {

            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int anId = rs.getInt(1);
                int userId = rs.getInt(2);
                int aptId = rs.getInt(3);
                Date dateIn = rs.getDate(4);
                Date dateOut = rs.getDate(5);

                Order order = Order.builder()
                        .id(anId)
                        .userId(userId)
                        .aptId(aptId)
                        .dateIn(dateIn.toLocalDate())
                        .dateOut(dateOut.toLocalDate())
                        .build();

                return order;

            }

            preparedStatement.executeUpdate();

            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Order update(Order entity) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {

            Order oldOrder = read(entity.getId());

            if (oldOrder != null) {

                int oldId = oldOrder.getId();
                int newUserId = oldOrder.getUserId();
                int newAptId = oldOrder.getAptId();

                preparedStatement.setInt(1, newUserId);
                preparedStatement.setInt(2, newAptId);
                preparedStatement.setDate(3, Date.valueOf(entity.getDateIn()));
                preparedStatement.setDate(4, Date.valueOf(entity.getDateOut()));
                preparedStatement.setInt(5, oldId);


                preparedStatement.executeUpdate();

                return oldOrder;
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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> list = new ArrayList<>();

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Order order = Order.builder()
                        .id(rs.getInt("id"))
                        .userId(rs.getInt("userId"))
                        .aptId(rs.getInt("ordersAptId"))
                        .dateIn(rs.getDate("dateIn").toLocalDate())
                        .dateOut(rs.getDate("dateOut").toLocalDate())
                        .build();

                list.add(order);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Order> getChosenOrders(int id) {

        List<Order> list = new ArrayList<>();

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CHOSEN_ORDERS)) {

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Order order = Order.builder()
                        .id(rs.getInt("id"))
                        .userId(rs.getInt("userId"))
                        .aptId(rs.getInt("ordersAptId"))
                        .dateIn(rs.getDate("dateIn").toLocalDate())
                        .dateOut(rs.getDate("dateOut").toLocalDate())
                        .build();

                list.add(order);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
