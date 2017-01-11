package interfaces;

import java.sql.SQLException;

public interface DbContext {
    <E> boolean persist (E entity) throws IllegalAccessException, SQLException;
    <E> Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;
    <E> Iterable<E> find(Class<E> table, String... where) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;
    <E> E findFirst(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;
    <E> E findFirst(Class<E> table, String... where) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;
    <E> boolean delete(Class<E> table, Integer id) throws IllegalAccessException, SQLException;
    <E> boolean delete(Class<E> table,String... where) throws SQLException;
}
