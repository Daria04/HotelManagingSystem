package dao.impl;

import connection.FactoryConnection;
import dao.ApartmentDao;
import dao.CrudRepository;
import model.Accomodation;
import model.Apartment;
import model.Comfort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDaoImpl implements CrudRepository<Apartment>, ApartmentDao {

    private static ApartmentDaoImpl instance;
    private static final String GET_ALL_APARTMENTS = "SELECT * FROM apartment;";
    private static final String GET_CHOSEN_APARTMENTS = "SELECT * FROM apartment WHERE cost = ? AND comfort = ? AND accomodation = ?;";
    private static final String CREATE_APARTMENT = "INSERT INTO apartment (cost, comfort, accomodation, roomNumber) VALUES (?,?,?,?);";
    private static final String GET_APARTMENT = "SELECT * FROM apartment WHERE id = ?;";
    private static final String UPDATE_APARTMENT = "UPDATE apartment SET cost = ?, comfort = ?, accomodation = ?, roomNumber = ? WHERE id = ?;";
    private static final String DELETE_APARTMENT = "DELETE FROM apartment WHERE id = ?;";


    private ApartmentDaoImpl() {

    }

    public static ApartmentDaoImpl getInstance() {

        if (instance == null) {

            instance = new ApartmentDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Apartment> getAllApartment() {

        List<Apartment> list = new ArrayList<>();

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_APARTMENTS)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Apartment apartment = Apartment.builder()
                        .aptId(rs.getInt("id"))
                        .cost(rs.getInt("cost"))
                        .comfort(Comfort.valueOf(rs.getString("comfort")))
                        .accomodation(Accomodation.valueOf(rs.getString("accomodation")))
                        .roomNumber(rs.getInt("roomNumber"))
                        .build();

                list.add(apartment);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
            return list;
        }

    @Override
    public List<Apartment> getChosenApartments(Apartment apartment) {

        List<Apartment> list = new ArrayList<>();

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CHOSEN_APARTMENTS)) {

            preparedStatement.setInt(1, apartment.getCost());
            preparedStatement.setString(2, apartment.getComfort().toString());
            preparedStatement.setString(3, apartment.getAccomodation().toString());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                Apartment apartment1 = Apartment.builder()
                        .cost(rs.getInt("cost"))
                        .comfort(Comfort.valueOf(rs.getString("comfort")))
                        .accomodation(Accomodation.valueOf(rs.getString("accomodation")))
                        .build();

                list.add(apartment1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Apartment create(Apartment entity) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_APARTMENT, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, entity.getCost());
            preparedStatement.setString(2, entity.getComfort().toString());
            preparedStatement.setString(3, entity.getAccomodation().toString());
            preparedStatement.setInt(4, entity.getRoomNumber());

            int i = preparedStatement.executeUpdate();

            if (i > 0) {

                try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        entity.setAptId(keys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Apartment read(int id) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_APARTMENT)) {

            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aptId = rs.getInt(1);
                int cost = rs.getInt(2);
                String comfort = rs.getString(3);
                String accomodation = rs.getString(4);
                int roomNumber = rs.getInt(5);

                Apartment apartment = Apartment.builder()
                        .aptId(aptId)
                        .cost(cost)
                        .comfort(Comfort.valueOf(comfort))
                        .accomodation(Accomodation.valueOf(accomodation))
                        .roomNumber(roomNumber)
                        .build();

                return apartment;

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
    public Apartment update(Apartment entity) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_APARTMENT)) {

            Apartment oldApartment = read(entity.getAptId());

            if (oldApartment != null) {

                int oldAptId = oldApartment.getAptId();
                int newCost = entity.getCost();
                Comfort newComfort = entity.getComfort();
                Accomodation newAccomodation = entity.getAccomodation();
                int newRoomNumber = entity.getRoomNumber();

                preparedStatement.setInt(1, newCost);
                preparedStatement.setString(2, entity.getComfort().toString());
                preparedStatement.setString(3, entity.getAccomodation().toString());
                preparedStatement.setInt(4, newRoomNumber);
                preparedStatement.setInt(5, oldAptId);

                preparedStatement.executeUpdate();

                return oldApartment;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {

        try (Connection connection = FactoryConnection.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_APARTMENT)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
