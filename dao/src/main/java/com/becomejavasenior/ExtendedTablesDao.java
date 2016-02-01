package com.becomejavasenior;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;


public class ExtendedTablesDao {

    private static final Logger log = Logger.getLogger(AbstractJDBCDao.class);
    private Map<String, String> entityMap = new HashMap<String, String>();
    private Connection connection;

    public ExtendedTablesDao(Connection connection) {
        this.connection = connection;

        entityMap.put("Company", "company");
        entityMap.put("Deal", "deal");
        entityMap.put("Task", "task");
        entityMap.put("Contact", "contact");
    }

    /**
     * Returns query for read tables Phone, File, Comment, Tag.
     */
    private String getSqlSelectQuery(String entityTableName, String extendedClassName) {
        log.info("Enter to getSqlSelectQuery()");

        String query = null;
        if (extendedClassName.contains("Phone")) {
            log.warn("Get Phone query");
            query = "SELECT phone.id as phone_id, phone.phone_value, phone_type.id as phone_type_id, " +
                    "phone_type.name as phone_type_name FROM phone, phone_type, " + entityTableName +
                    ", " + entityTableName + "_phone WHERE phone.phone_type_id = phone_type.id AND phone.id = " +
                    entityTableName + "_phone.phone_id AND " + entityTableName + "_phone." + entityTableName +
                    "_id = " + entityTableName + ".id AND " + entityTableName + ".id = ?;";
        }
        if (extendedClassName.contains("File")) {
            log.warn("Get File query");
            query = "SELECT file.id as file_id, file.date_of_create as file_date_of_create, file.name as file_name, " +
                    "file.data as file_data FROM " + entityTableName + ", file, " + entityTableName + "_file " +
                    "WHERE " + entityTableName + ".id = " + entityTableName + "_file." + entityTableName +
                    "_id AND " + entityTableName + "_file.file_id = file.id AND " + entityTableName + ".id = ?;";
        }
        if (extendedClassName.contains("Comment")) {
            log.warn("Get Comment query");
            query = "SELECT comment.id as comment_id, comment.date_of_create as comment_date_of_create, comment.comment" +
                    " as comment_comment FROM " + entityTableName + ", comment, " + entityTableName + "_comment " +
                    "WHERE " + entityTableName + ".id = " + entityTableName + "_comment." + entityTableName + "_id AND " +
                    entityTableName + "_comment.comment_id = comment.id  AND " + entityTableName + ".id = ?;";
        }
        if (extendedClassName.contains("Tag")) {
            log.warn("Get Tag query");
            query = "SELECT tag.id as tag_id, tag.name as tag_name FROM " + entityTableName + ", tag, " + entityTableName +
                    "_tag WHERE " + entityTableName + ".id = " + entityTableName + "_tag." + entityTableName +
                    "_id AND " + entityTableName + "_tag.tag_id = tag.id AND " + entityTableName + ".id = ?;";
        }

        return query;
    }

    /**
     * Returns query for insert records in tables Phone, File, Comment, Tag.
     */
    private <E> String getSqlInsertQuery(Identified entity, E collectionRecord) {
        log.info("Enter to getSqlInsertQuery()");
        String entityTableName = entityMap.get(entity.getClass().getSimpleName());
        String query = null;
        if (collectionRecord.getClass().getSimpleName().contains("Phone")) {
            log.warn("Get Phone query");
            Phone phone = (Phone)collectionRecord;
            query = "with first as (INSERT INTO phone(phone_value, phone_type_id) VALUES ('" + phone.getValue() +
                    "', '" + phone.getPhoneType().getId() +"') RETURNING id)" + "INSERT INTO " +
                    entityTableName + "_phone(" + entityTableName + "_id, phone_id) " +
                    "VALUES ('" + entity.getId() +"', (select id from first));";
        }
        if (collectionRecord.getClass().getSimpleName().contains("File")) {
            log.warn("Get File query");
            File file = (File)collectionRecord;
            query = "with first as (INSERT INTO file(date_of_create, name, data) VALUES ('" +
                    new java.sql.Date(file.getDate().getTime()) + "', '" + file.getName() +"', '" + file.getData() +
                    "') RETURNING id) INSERT INTO " + entityTableName + "_file(" + entityTableName +
                    "_id, file_id) VALUES ('" + entity.getId() + "', (select id from first));";
        }
        if (collectionRecord.getClass().getSimpleName().contains("Comment")) {
            log.warn("Get Comment query");
            Comment comment = (Comment)collectionRecord;
            query = "with first as (INSERT INTO comment(date_of_create, comment) VALUES ('" +
                    new java.sql.Date(comment.getDateOfCreate().getTime()) + "', '" + comment.getComment()
                    + "') RETURNING id) INSERT INTO " + entityTableName + "_comment(" + entityTableName +
                    "_id, comment_id) VALUES ('" + entity.getId() + "', (select id from first));";
        }
        if (collectionRecord.getClass().getSimpleName().contains("Tag")) {
            log.warn("Get Tag query");
            Tag tag = (Tag)collectionRecord;
            query = "with first as (INSERT INTO tag(name) VALUES ('" + tag.getName() + "') RETURNING id)" +
                    "INSERT INTO " + entityTableName + "_tag(" + entityTableName + "_id, tag_id) " +
                    "VALUES ('" + entity.getId() + "', (select id from first)) ;";
        }


        return query;
    }

    /**
     * Returns query for update records in tables Phone, File, Comment, Tag.
     */
    private <E> String getSqlUpdateQuery(E collectionRecord) {
        log.info("Enter to getSqlUpdateQuery()");
        String query = null;
        if (collectionRecord.getClass().getSimpleName().contains("Phone")) {
            log.warn("Get Phone query");
            Phone phone = (Phone)collectionRecord;
            query = "UPDATE phone SET phone_value ='" + phone.getValue() + "', phone_type_id ='" +
                    phone.getPhoneType().getId() + "' WHERE id = '" + phone.getId() +"';";
        }
        if (collectionRecord.getClass().getSimpleName().contains("File")) {
            log.warn("Get File query");
            File file = (File)collectionRecord;
            query = "UPDATE file SET date_of_create ='" + new java.sql.Date(file.getDate().getTime()) + "', name = '" +
                    file.getName() + "', data = '" + file.getData() + "' WHERE id = '" + file.getId() +"';";
        }
        if (collectionRecord.getClass().getSimpleName().contains("Comment")) {
            log.warn("Get Comment query");
            Comment comment = (Comment)collectionRecord;
            query = "UPDATE comment SET date_of_create ='" + new java.sql.Date(comment.getDateOfCreate().getTime()) +
                    "', comment = '" + comment.getComment() + "' WHERE id = '" + comment.getId() +"';";
        }
        if (collectionRecord.getClass().getSimpleName().contains("Tag")) {
            log.warn("Get Tag query");
            Tag tag = (Tag)collectionRecord;
            query = "UPDATE tag SET name ='" + tag.getName() + "' WHERE id = '" + tag.getId() +"';";
        }

        return query;
    }



    /**
     * Returns records of table Phone for object 'entity'.
     */
    public Set<Phone> getPhones(Identified entity) throws PersistException {
        log.info("Enter to getPhones()");
        Set<Phone> result = new LinkedHashSet<Phone>();
        log.warn("getSqlSelectQuery()");
        String sql = getSqlSelectQuery(entityMap.get(entity.getClass().getSimpleName()), Phone.class.getSimpleName());
        log.warn("Use prepared statement");
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, entity.getId());

            log.warn("Executing statement query");
            try (ResultSet rs = stm.executeQuery()){
                while (rs.next()) {
                    Phone phone = new Phone();
                    phone.setId(rs.getInt("phone_id"));
                    phone.setValue(rs.getString("phone_value"));

                    PhoneType phoneType = new PhoneType();
                    phoneType.setId(rs.getInt("phone_type_id"));
                    phoneType.setName(rs.getString("phone_type_name"));
                    phone.setPhoneType(phoneType);
                    result.add(phone);
                }
            } catch (Exception e) {
                log.error("Error with ResultSet", new PersistException(e));
            }
        } catch (Exception e) {
            log.error("Error with PreparedStatement", new PersistException(e));
        }
        return result;
    }

    /**
     * Returns records of table File for object 'entity'.
     */
    public Set<File> getFiles(Identified entity) throws PersistException {
        log.info("Enter to getFiles()");
        Set<File> result = new LinkedHashSet<File>();
        log.warn("getSqlSelectQuery()");
        String sql = getSqlSelectQuery(entityMap.get(entity.getClass().getSimpleName()), File.class.getSimpleName());
        log.warn("Use prepared statement");
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, entity.getId());

            log.warn("Executing statement query");
            try (ResultSet rs = stm.executeQuery()){
                while (rs.next()) {
                    File file = new File();
                    file.setId(rs.getInt("file_id"));
                    file.setDate(rs.getDate("file_date_of_create"));
                    file.setName(rs.getString("file_name"));
                    file.setData(rs.getBytes("file_data"));
                    result.add(file);
                }
            } catch (Exception e) {
                log.error("Error with ResultSet", new PersistException(e));
            }
        } catch (Exception e) {
            log.error("Error with PreparedStatement", new PersistException(e));
        }
        return result;
    }

    /**
     * Returns records of table Comment for object 'entity'.
     */
    public Set<Comment> getComments(Identified entity) throws PersistException {
        log.info("Enter to getComments()");
        Set<Comment> result = new LinkedHashSet<Comment>();
        log.warn("getSqlSelectQuery()");
        String sql = getSqlSelectQuery(entityMap.get(entity.getClass().getSimpleName()), Comment.class.getSimpleName());
        log.warn("Use prepared statement");
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, entity.getId());

            log.warn("Executing statement query");
            try (ResultSet rs = stm.executeQuery()){
                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setId(rs.getInt("comment_id"));
                    comment.setDateOfCreate(rs.getDate("comment_date_of_create"));
                    comment.setComment(rs.getString("comment_comment"));
                    result.add(comment);
                }
            } catch (Exception e) {
                log.error("Error with ResultSet", new PersistException(e));
            }
        } catch (Exception e) {
            log.error("Error with PreparedStatement", new PersistException(e));
        }
        return result;
    }

    /**
     * Returns records of table Tag for object 'entity'.
     */
    public Set<Tag> getTags(Identified entity) throws PersistException {
        log.info("Enter to getTags()");
        Set<Tag> result = new LinkedHashSet<Tag>();
        log.warn("getSqlSelectQuery()");
        String sql = getSqlSelectQuery(entityMap.get(entity.getClass().getSimpleName()), Tag.class.getSimpleName());
        log.warn("Use prepared statement");
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, entity.getId());

            log.warn("Executing statement query");
            try (ResultSet rs = stm.executeQuery()){
                while (rs.next()) {
                    Tag tag = new Tag();
                    tag.setId(rs.getInt("tag_id"));
                    tag.setName(rs.getString("tag_name"));
                    result.add(tag);
                }
            } catch (Exception e) {
                log.error("Error with ResultSet", new PersistException(e));
            }
        } catch (Exception e) {
            log.error("Error with PreparedStatement", new PersistException(e));
        }
        return result;
    }

    /**
     * Inserts or updates records of extended tables for object 'entity'.
     */
    public <E extends Identified> void insertUpdateTableRecords(Identified entity, Set<E> extendedClassSet, boolean isInsert) throws PersistException {
        log.info("Enter to insertUpdateTableRecords()");
        if(!(extendedClassSet == null)){
            for (E collectionRecord : extendedClassSet) {
                String sql;
                if (isInsert == true){
                    log.warn("getSqlInsertQuery()");
                    sql = getSqlInsertQuery(entity, collectionRecord);
                }else{
                    log.warn("getSqlUpdateQuery()");
                    sql = getSqlUpdateQuery(collectionRecord);
                }
                log.warn("Use create statement");
                try (Statement statement = connection.createStatement()) {
                    log.warn("Executing statement query");
                    statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                    if (isInsert == true){
                        log.warn("Use ResultSet");
                        ResultSet rs = statement.getGeneratedKeys();
                        rs.next();
                        collectionRecord.setId(rs.getInt(collectionRecord.getClass().getSimpleName().toLowerCase() + "_id"));
                    }
                } catch (Exception e) {
                    log.error("Error with createStatement", new PersistException(e));
                }
            }
        }
    }

    /**
     * Deletes records of extended tables for object 'entity'.
     */
    public <E extends Identified> void deleteTableRecords(Identified entity, Set<E> extendedClassSet) throws PersistException {
        log.info("Enter to deleteTableRecords()");
        String entityTableName = entityMap.get(entity.getClass().getSimpleName());
        if(!(extendedClassSet == null)){

            for (Identified collectionRecord : extendedClassSet) {
                String extendedTableName = collectionRecord.getClass().getSimpleName().toLowerCase();
                log.warn("Get sql query");
                String sql = "DELETE FROM " + entityTableName + "_" + extendedTableName + " WHERE " +
                        extendedTableName + "_id = '" + collectionRecord.getId() + "'; DELETE FROM " +
                        extendedTableName + " WHERE id = '" + collectionRecord.getId() + "';";

                log.warn("Use create statement");
                try (Statement stm = connection.createStatement()) {
                        log.warn("Executing statement query");
                        stm.executeUpdate(sql);

                } catch (Exception e) {
                    log.error("Error with createStatement", new PersistException(e));
                }
            }
        }
    }
}


