package dao;

public interface CrudRepository<T> {

    T create(T entity);

    T read(int id);

    T update(T entity);

    void delete(int id);
}
