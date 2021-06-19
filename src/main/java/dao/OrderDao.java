package dao;

import model.Order;

import java.util.List;

public interface OrderDao extends CrudRepository<Order> {

    List<Order> getAllOrders();

    List<Order> getChosenOrders(int id);
}
