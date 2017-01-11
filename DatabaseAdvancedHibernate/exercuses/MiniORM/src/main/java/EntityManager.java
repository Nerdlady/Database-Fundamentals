import interfaces.DbContext;
import persistence.Column;
import persistence.Entity;
import persistence.Id;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class EntityManager implements DbContext {
    private Connection connection;
    private List<Object> persistedEntities;

    public EntityManager(Connection connection) {
        this.connection = connection;
        this.persistedEntities = new ArrayList<>();
    }

    public <E> boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value = primary.get(entity);

        this.doCreate(entity, primary);

        if (value == null || (Integer) value <= 0) {
            return this.doInsert(entity, primary);
        }

        return this.doUpdate(entity, primary);
    }

    @SuppressWarnings("unchecked")
    public <E> Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Statement statement = this.connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table);
        ResultSet resultSet = statement.executeQuery(query);

        if (this.persistedEntities.size() > 0) {
            this.persistedEntities.clear();
        }

        while (resultSet.next()) {
            E entity = table.newInstance();
            entity = this.fillEntity(table, resultSet, entity);
            this.persistedEntities.add(entity);
        }
        return (Iterable<E>) Collections.unmodifiableList(this.persistedEntities);
    }

    @SuppressWarnings("unchecked")
    public <E> Iterable<E> find(Class<E> table, String... where) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Statement statement = this.connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table) + " WHERE 1 ";
        for (String str : where) {
            query += " AND " + str;
        }

        ResultSet resultSet = statement.executeQuery(query);

        if (this.persistedEntities.size() > 0) {
            this.persistedEntities.clear();
        }

        while (resultSet.next()) {
            E entity = table.newInstance();
            entity = this.fillEntity(table, resultSet, entity);
            this.persistedEntities.add(entity);
        }
        return (Iterable<E>) Collections.unmodifiableList(this.persistedEntities);
    }


    public <E> E findFirst(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Statement statement = this.connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table) + " LIMIT 1";
        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.newInstance();
        resultSet.next();
        entity = this.fillEntity(table, resultSet, entity);
        return entity;
    }

    public <E> E findFirst(Class<E> table, String... where) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Statement statement = this.connection.createStatement();
        String query = "SELECT * FROM " + this.getTableName(table) + " WHERE 1=1";
        for (String str : where) {
            query += " AND " + str;
        }
        query += " LIMIT 1";
        ResultSet resultSet = statement.executeQuery(query);
        E entity = table.newInstance();
        if (resultSet.next()) {
            entity = this.fillEntity(table, resultSet, entity);
        }
        return entity;
    }

    @Override
    public <E> boolean delete(Class<E> table, Integer id) throws IllegalAccessException, SQLException {
        String query = "DELETE FROM " + this.getTableName(table) +
                " WHERE " + this.getFieldName(this.getId(table)) + " = " + id;
        int affectedRows = this.connection.createStatement().executeUpdate(query);

        return affectedRows > 0;
    }

    @Override
    public <E> boolean delete(Class<E> table, String... where) throws SQLException {
        String query = "DELETE FROM " + this.getTableName(table) +
                " WHERE 1=1 ";

        for (String str : where) {
            query += " AND " + str;
        }

        int affectedRows = this.connection.createStatement().executeUpdate(query);
        return affectedRows > 0;
    }

    private <E> String getTableName(Class<E> entity) {
        if (entity.isAnnotationPresent(Entity.class)) {
            Entity entityAnnotation = entity.getAnnotation(Entity.class);

            if (!entityAnnotation.name().isEmpty()) {
                return entityAnnotation.name();
            }
        }
        return entity.getSimpleName();
    }

    private String getFieldName(Field field) {
        if (field.isAnnotationPresent(Column.class)) {
            Column fieldAnnotation = field.getAnnotation(Column.class);

            if (!fieldAnnotation.name().isEmpty()) {
                return fieldAnnotation.name();
            }
        }
        return field.getName();
    }

    private Field getId(Class c) throws IllegalAccessException {
        for (Field field : c.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        throw new IllegalAccessException("Id annotation is missing");

    }

    private <E> boolean doCreate(E entity, Field primary) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS " + this.getTableName(entity.getClass()) + "( ";

        List<String> parameters = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            String param = this.getFieldName(field) + " " + this.getDbType(field, primary);
            parameters.add(param);
        }

        query += String.join(", ", parameters);
        query += ")";

        return this.connection.createStatement().execute(query);
    }

    private String getDbType(Field field, Field primary) {
        field.setAccessible(true);

        if (field.getName().equals(primary.getName())) {
            return "BIGINT PRIMARY KEY AUTO_INCREMENT";
        }

        switch (field.getType().getSimpleName().toLowerCase()) {
            case "int":
                return "INT";
            case "string":
                return "VARCHAR(50)";
            case "boolean":
                return "BIT";
            case "date":
                return "DATE";
            case "long":
                return "LONG";
        }
        return null;
    }

    private <E> boolean doInsert(E entity, Field primary) throws SQLException, IllegalAccessException {
        String query = "INSERT INTO " + this.getTableName(entity.getClass()) + " (";

        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (!field.getName().equals(primary.getName())) {
                field.setAccessible(true);
                String column = "`" + this.getFieldName(field) + "`";
                String value = "";
                Object fieldValue = field.get(entity);
                value = setValueFormat(fieldValue);

                columns.add(column);
                values.add(value);
            }
        }

        query += String.join(", ", columns) + ") VALUES (" + String.join(", ", values) + " )";
        return connection.prepareStatement(query).execute();
    }


    private <E> boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String query = "UPDATE " + this.getTableName(entity.getClass()) + " SET ";
        String where = " WHERE " + primary.getName() + " = ?";

        List<String> updateRows = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (!field.getName().equals(primary.getName())) {
                field.setAccessible(true);

                Object fieldValue = field.get(entity);
                String value = "";
                value = setValueFormat(fieldValue);

                String updateRow = this.getFieldName(field) + " = " + value;
                updateRows.add(updateRow);
            }
        }

        query += String.join(", ", updateRows);
        PreparedStatement preparedStatement = this.connection.prepareStatement(query + where);
        preparedStatement.setInt(1, (Integer) primary.get(entity));
        return preparedStatement.execute();
    }

    private String setValueFormat(Object fieldValue) {
        String value;
        if (fieldValue instanceof Date) {
            value = "'" + new SimpleDateFormat("yyyy-MM-dd").format(fieldValue) + "'";
        } else if (fieldValue.getClass().equals(Boolean.TYPE) || fieldValue.getClass().equals(Boolean.class)) {
            value = String.valueOf(Boolean.parseBoolean(fieldValue.toString()) ? 1 : 0);
        } else {
            value = "'" + fieldValue + "'";
        }
        return value;
    }

    private <E> E fillEntity(Class<E> table, ResultSet resultSet, E entity) throws SQLException, NoSuchFieldException, IllegalAccessException {
        if (resultSet != null) {
            for (Field field : table.getDeclaredFields()) {
                field.setAccessible(true);
                String columnName = this.getFieldName(field);
                if (field.getType().equals(String.class)) {
                    field.set(entity, resultSet.getString(columnName));
                } else if (field.getType().equals(Integer.TYPE) || field.getType().equals(Integer.class)) {
                    field.set(entity, resultSet.getInt(columnName));
                } else if (field.getType().equals(Long.TYPE) || field.getType().equals(Long.class)) {
                    field.set(entity, resultSet.getLong(columnName));
                } else if (field.getType().equals(Boolean.TYPE) || field.getType().equals(Boolean.class)) {
                    field.set(entity, resultSet.getBoolean(columnName));
                } else if (field.getType().equals(java.util.Date.class)) {
                    field.set(entity, resultSet.getDate(columnName));
                }
            }
        }


        return entity;
    }

}
