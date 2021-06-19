package service.impl;

import dao.impl.ApartmentDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.UserDaoImpl;
import model.Apartment;
import model.Order;
import model.User;
import service.Service;

import java.util.List;

public class ServiceImpl implements Service {

    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    private ApartmentDaoImpl apartmentDao = ApartmentDaoImpl.getInstance();
    private OrderDaoImpl orderDao = OrderDaoImpl.getInstance();

    @Override
    public User createUser(User user){

        return userDao.create(user);
    }

    @Override
    public Order createOrder(Order order) {

        return orderDao.create(order);
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public void deleteOrder(int id) {

    }

    @Override
    public Order readOrder(int id) {

        return orderDao.read(id);
    }

    @Override
    public User readUser(int id) {

        return userDao.read(id);
    }

    @Override
    public Apartment readApart(int id) {

        return apartmentDao.read(id);
    }

    @Override
    public User updateUser(User user) {

        return userDao.update(user);
    }

    @Override
    public Order updateOrder(Order order) {

        return orderDao.update(order);
    }

    @Override
    public List<User> getAllUser() {

        return userDao.getAllUsers();
    }

    @Override
    public List<Apartment> getAllApartment() {

        return apartmentDao.getAllApartment();
    }

    @Override
    public List<Apartment> getChosenApartments(Apartment apartment) {

        return apartmentDao.getChosenApartments(apartment);
    }

    @Override
    public List<Order> getAllOrders() {

        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getChosenOrders(int id){

        return orderDao.getChosenOrders(id);
    }
}
