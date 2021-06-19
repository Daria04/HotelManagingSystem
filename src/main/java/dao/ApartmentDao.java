package dao;

import model.Apartment;

import java.util.List;

public interface ApartmentDao extends CrudRepository<Apartment> {

    List<Apartment> getAllApartment();

    List<Apartment> getChosenApartments(Apartment apartment);


}
