package service;

import model.Apartment;
import model.Order;
import model.User;

import java.util.List;

public interface Service {

    User createUser(User user);

    Order createOrder(Order order);

    void deleteUser(int id);

    void deleteOrder(int id);

    Order readOrder(int id);

    User readUser(int id);

    Apartment readApart(int id);

    User updateUser(User user);

    Order updateOrder(Order order);

    List<User> getAllUser();

    List<Apartment> getAllApartment();

    List<Apartment> getChosenApartments(Apartment apartment);

    List<Order> getAllOrders();

    List<Order> getChosenOrders(int id);
}
