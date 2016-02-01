package com.becomejavasenior.impl;

import com.becomejavasenior.*;
import com.becomejavasenior.Currency;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DealDaoImpl extends AbstractJDBCDao<Deal> implements DealDao {
    private static final Logger log = Logger.getLogger(DealDaoImpl.class);

    private ExtendedTablesDao extendedTablesDao = new ExtendedTablesDao(connection);
//    private UserDaoImpl userDao;
//    private CompanyDaoImpl companyDao;
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private CompanyDao companyDao;


    public DealDaoImpl(DataSource dataSource) {
        super(dataSource);
//        userDao = new UserDaoImpl(getConnection());
//        companyDao = new CompanyDaoImpl(getConnection());
    }

    @Override
    public String getSelectQuery() {
        log.warn("For Deal");
        return "SELECT id, date_of_create, name, budget, deal_status_id, currency_id, owner_id, company_id FROM deal ";
    }

    @Override
    public String getCreateQuery() {
        log.warn("For Deal");
        return "INSERT INTO deal (date_of_create, name, budget, deal_status_id, currency_id, owner_id, company_id) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        log.warn("For Deal");
        return "UPDATE deal SET date_of_create = ?, name = ?, budget = ?, deal_status_id = ?, " +
                "currency_id = ?, owner_id = ?, company_id = ? WHERE id=?;";
    }

    @Override
    public String getDeleteQuery() {
        log.warn("For Deal");
        return "UPDATE deal SET is_deleted=true WHERE id=?;";
    }

    @Override
    protected List<Deal> parseResultSet(ResultSet rs) throws PersistException {
        log.info("Enter to parseResultSet()");
        LinkedList<Deal> result = new LinkedList<Deal>();
        try {
            log.warn("Input data from DB");
            while (rs.next()) {
                Deal deal = new Deal();
                deal.setId(rs.getInt("id"));
                deal.setDate(rs.getDate("date_of_create"));
                deal.setName(rs.getString("name"));
                deal.setBudget(rs.getBigDecimal("budget"));
                deal.setStatus(readDealStatus(rs.getInt("deal_status_id")));
                deal.setCurrency(readCurrency(rs.getInt("currency_id")));
                deal.setOwner(getUser(rs.getInt("owner_id")));
                deal.setCompany(readCompany(rs.getInt("company_id")));
                deal.setComments(extendedTablesDao.getComments(deal));
                deal.setFiles(extendedTablesDao.getFiles(deal));
                deal.setTags(extendedTablesDao.getTags(deal));
                deal.setContacts(readContacts(deal));
                result.add(deal);
            }
        } catch (Exception e) {
            log.error("Exception with read data from  DB", new PersistException(e));
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Deal object) throws PersistException {
        log.info("For Deal");
        log.warn("Input data to DB");
        try {
            statement.setDate(1, new Date(object.getDate().getTime()));
            statement.setString(2, object.getName());
            statement.setBigDecimal(3, object.getBudget());
            statement.setInt(4, object.getStatus().getId());
            if (object.getCurrency()!=null)
                statement.setInt(5, object.getCurrency().getId());
            else
                statement.setObject(5, null);
            statement.setInt(6, object.getOwner().getId());
            if (object.getCompany()!=null)
                statement.setInt(7, object.getCompany().getId());
            else
                statement.setObject(7, null);
        } catch (Exception e) {
            log.error("Exception with write data to  DB", new PersistException(e));
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Deal object) throws PersistException {
        log.info("For Deal");
        log.warn("Input data to DB");
        try {
            statement.setDate(1, (Date) object.getDate());
            statement.setString(2, object.getName());
            statement.setBigDecimal(3, object.getBudget());
            statement.setInt(4, object.getStatus().getId());
            if (object.getCurrency()!=null)
                statement.setInt(5, object.getCurrency().getId());
            else
                statement.setObject(5, null);
            statement.setInt(6, object.getOwner().getId());
            statement.setInt(7, object.getCompany().getId());
            statement.setInt(8, object.getId());
            extendedTablesDao.insertUpdateTableRecords(object, object.getComments(), false);
            extendedTablesDao.insertUpdateTableRecords(object, object.getFiles(), false);
            extendedTablesDao.insertUpdateTableRecords(object, object.getTags(), false);
        } catch (Exception e) {
            log.error("Exception with update data to DB", new PersistException(e));
        }
    }

    @Override
    protected void insertUpdateExtendedTableRecords(Deal object) throws PersistException {
        insertUpdateContactRecords(object, object.getContacts(), true);
        extendedTablesDao.insertUpdateTableRecords(object, object.getComments(), true);
        extendedTablesDao.insertUpdateTableRecords(object, object.getFiles(), true);
        extendedTablesDao.insertUpdateTableRecords(object, object.getTags(), true);
    }

    @Override
    protected void deleteExtendedTableRecords(Deal object) throws PersistException {
        deleteContactRecords(object, object.getContacts());
        extendedTablesDao.deleteTableRecords(object, object.getComments());
        extendedTablesDao.deleteTableRecords(object, object.getFiles());
        extendedTablesDao.deleteTableRecords(object, object.getTags());
    }

    @Override
    public Currency readCurrency(int key) throws PersistException {
        log.info("created sql request for readCurrency to DealDaoImpl record");
        String sql = "SELECT currency.id, currency.name, currency.code FROM currency WHERE id = ?;";
        Currency currency = null;
        log.warn("Give sql to prepared statement");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            currency = new Currency();
            currency.setId(rs.getInt("id"));
            currency.setCode(rs.getString("code"));
            currency.setName(rs.getString("name"));
        } catch (SQLException e) {
            log.error("Error with readCurrency in DealDaoImpl", new SQLException());
        }
        return currency;
    }

    @Override
    public List<DealStatus> getAllStatus() throws PersistException {
        String sql = "SELECT deal_status.id, deal_status.name FROM deal_status ;";
        List<DealStatus> statuses = new LinkedList<>();
        DealStatus dealStatus = null;
        log.warn("Give sql to prepared statement");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dealStatus = new DealStatus();
                dealStatus.setId(rs.getInt("id"));
                dealStatus.setName(rs.getString("name"));
                statuses.add(dealStatus);
            }
        } catch (SQLException e) {
            log.error("Error with readDealStatusl in DealDaoImpl", new SQLException());
        }
        return statuses;
    }

    @Override
    public DealStatus readDealStatus(int key) throws PersistException {
        log.info("created sql request for readDealStatus to DealDaoImpl record");
        String sql = "SELECT deal_status.id, deal_status.name FROM deal_status WHERE id = ?;";
        DealStatus dealStatus = null;
        log.warn("Give sql to prepared statement");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            dealStatus = new DealStatus();
            dealStatus.setId(rs.getInt("id"));
            dealStatus.setName(rs.getString("name"));
        } catch (SQLException e) {
            log.error("Error with readDealStatus in DealDaoImpl", new SQLException());
        }
        return dealStatus;
    }

    private void deleteContactRecords(Deal deal, List<Contact> contacts) {
        if (contacts != null) {
            for (Contact c : contacts) {
                String sql = "DELETE FROM deal_contact WHERE contact_id = '" + c.getId() + "' " +
                        "deal_id = '" + deal.getId() + "'";
                try (Statement stm = connection.createStatement()) {
                    stm.executeUpdate(sql);

                } catch (Exception e) {
                    log.error("Error with createStatement", new PersistException(e));
                }
            }
        }
    }

    private void insertUpdateContactRecords(Deal entity, List<Contact> contactSet, boolean isInsert) {
        if (contactSet != null) {
            for (Contact c : contactSet) {
                String sql = null;
                sql = getSqlInsertQuery(entity, c, isInsert);
                try (Statement statement = connection.createStatement()) {
                    log.warn("Use ResultSet");
                    ResultSet rs = statement.executeQuery(sql);
                    rs.next();
                } catch (Exception e) {
                    log.error("Error with createStatement", new PersistException(e));
                }
            }
        }
    }

    private String getSqlInsertQuery(Deal entity, Contact contact, boolean isInsert) {
        String query;
        if (isInsert) {
            query = "INSERT INTO deal_contact (deal_id, contact_id)" +
                    "VALUES (' " + entity.getId() + " ', ' " + contact.getId() + " ');";
        } else {
            query = "UPDATE deal_contact SET deal_id = '" + entity.getId() + " ', contact_id = ' "
                    + contact.getId() + " ' ";
        }
        return query;
    }

    @Override
    public List<Contact> readContacts(Deal deal) throws PersistException {
        String sql = "SELECT contact.id, contact.name, contact.positions_id, contact.company_id, contact.email, \n" +
                "contact.skype, contact.owner_id FROM deal, contact, deal_contact WHERE \n" +
                "deal.id = deal_contact.deal_id AND deal_contact.contact_id = contact.id AND deal.id = ?;";
        List<Contact> contacts = new ArrayList<>();
        Contact contact = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, deal.getId());
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    contact = new Contact();
                    contact.setId(rs.getInt("id"));
                    contact.setName(rs.getString("name"));
                    contact.setSkype(rs.getString("skype"));
                    contact.setEmail(rs.getString("email"));
                    contact.setOwner(getUser(rs.getInt("owner_id")));
                    contact.setPosition(readPosition(rs.getInt("positions_id")));
                    contact.setCompany(readCompany(rs.getInt("company_id")));
//                    contact.setDeals(); ???
                    contact.setPhones(extendedTablesDao.getPhones(contact));
                    contact.setComments(extendedTablesDao.getComments(contact));
                    contact.setTags(extendedTablesDao.getTags(contact));
                    contact.setFiles(extendedTablesDao.getFiles(contact));
                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public void addContact(Deal deal, Contact contact) throws PersistException{
        String query = "INSERT INTO deal_contact (deal_id, contact_id)" +
                "VALUES (' " + deal.getId() + " ', ' " + contact.getId() + " ');";
        try (Statement statement = connection.createStatement()) {
            log.warn("Use ResultSet");
            ResultSet rs = statement.executeQuery(query);
            rs.next();
        } catch (Exception e) {
            log.error("Error with createStatement", new PersistException(e));
        }
    }

    @Override
    public Position readPosition(int key) throws PersistException {
        String sql = "SELECT * FROM positions WHERE id = ?";
        Position pos = new Position();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, key);
            try (ResultSet rs = stm.executeQuery()) {
                rs.next();
                pos.setId(rs.getInt("id"));
                pos.setName(rs.getString("name"));
            } catch (SQLException e) {

            }
        } catch (SQLException e) {

        }
        return pos;
    }

    @Override
    public List<Currency> getAllCurrencies() throws PersistException {
        log.info("Enter to readCurrencies()");
        String sql = "SELECT * FROM currency;";
        List<Currency> currencyList = new ArrayList<Currency>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            log.warn("Executing statement query");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Currency currency = new Currency();
                currency.setId(rs.getInt("id"));
                currency.setName(rs.getString("name"));
                currency.setCode(rs.getString("code"));
                currencyList.add(currency);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return currencyList;
    }


    @Override
    public List<Deal> getDealsByCompanyId(int key) throws PersistException {
        String sql = "SELECT * FROM deal WHERE is_deleted = false AND company_id = ?";
        log.info("Enter to getDealsByCompanyId()");
        List<Deal> result = new LinkedList<Deal>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, key);
            try (ResultSet rs = stm.executeQuery()) {
                log.warn("Input data from DB");
                while (rs.next()) {
                    Deal deal = new Deal();
                    deal.setId(rs.getInt("id"));
                    deal.setDate(rs.getDate("date_of_create"));
                    deal.setName(rs.getString("name"));
                    deal.setBudget(rs.getBigDecimal("budget"));
                    deal.setStatus(readDealStatus(rs.getInt("deal_status_id")));
                    deal.setCurrency(readCurrency(rs.getInt("currency_id")));
                    deal.setOwner(getUser(rs.getInt("owner_id")));
                    deal.setComments(extendedTablesDao.getComments(deal));
                    deal.setFiles(extendedTablesDao.getFiles(deal));
                    deal.setTags(extendedTablesDao.getTags(deal));
                    deal.setContacts(readContacts(deal));
                    result.add(deal);
                }
            } catch (Exception e) {
                log.error("Exception with read data from  DB", new PersistException(e));
            }
        } catch (Exception e) {
            log.error("Exception with read data from  DB", new PersistException(e));
        }
        return result;
    }

    @Override
    public User getUser(int key) throws PersistException {
        String sql = "SELECT users.id as users_id , users.name as users_name, users.login as users_login, " +
                "users.password as users_password, users.email as users_email, users.roles_id as users_roles_id, " +
                "roles.name as roles_name, permition.can_read, permition.can_write, permition.can_delete" +
                " FROM users, roles, permition WHERE users.roles_id = roles.id AND is_deleted = false" +
                " AND roles.id = permition.roles_id AND users.id = ?;";
        User user = new User();
        try (PreparedStatement stm = connection.prepareStatement(sql)){
            stm.setInt(1, key);
            ResultSet rs = stm.executeQuery();
            rs.next();
            user.setId(rs.getInt("users_id"));
            user.setName(rs.getString("users_name"));
            user.setLogin(rs.getString("users_login"));
            user.setPassword(rs.getString("users_password"));
            user.setEmail(rs.getString("users_email"));
            Role role = new Role();
            role.setId(rs.getInt("users_roles_id"));
            role.setName(rs.getString("roles_name"));
            Permition permition = new Permition();
            permition.setCanRead(rs.getBoolean("can_read"));
            permition.setCanWrite(rs.getBoolean("can_write"));
            permition.setCanDelete(rs.getBoolean("can_delete"));
            role.setPermition(permition);
            user.setRole(role);
        }catch (Exception e){
            throw new PersistException(e);
        }
        return user;
    }

    @Override
    public Company readCompany(int key) throws PersistException {
        String sql = "SELECT * FROM company WHERE  is_deleted = false AND id=?;";
        Company company = new Company();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            rs.next();
            company.setId(rs.getInt("id"));
            company.setName(rs.getString("name"));
            company.setAddress(rs.getString("address"));
            company.setWeb(rs.getString("web"));
            company.setEmail(rs.getString("email"));
            company.setOwner(getUser(rs.getInt("owner_id")));
            company.setPhones(extendedTablesDao.getPhones(company));
            company.setComments(extendedTablesDao.getComments(company));
            company.setFiles(extendedTablesDao.getFiles(company));
            company.setTags(extendedTablesDao.getTags(company));
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return company;
    }

}
