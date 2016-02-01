package com.becomejavasenior.daoContact;

import com.becomejavasenior.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by vlad on 27.11.15.
 */
public class ContactDaoImpl extends AbstractJDBCDao<Contact> implements ContactDao  {
    private final  static String GET_TYPE="select name from  communication_form where id=?";
    private final static  String INSERT_INTO_CONTACT ="INSERT INTO contact(name,  positions_id, company_id, email, skype, owner_id)VALUES (?, ?, ?, ?, ?,?)";
    private final static  String INSERT_INTO_COMMENT ="INSERT INTO comment(date_of_create,  comment)VALUES (?, ?)";
    private final static  String INSERT_INTO_TAG="INSERT INTO tag (name) VALUES(?)";
    private  final static   String INSERT_INTO_PHONE="INSERT INTO phone (phone_value, phone_type_id)VALUES (?,?)";
    private final static  String INSERT_INTO_CONTACT_TAG="INSERT INTO contact_tag (contact_id, tag_id) VALUES (?,?)";
    private final static  String INSERT_INTO_POSITION="INSERT INTO positions (name) VALUES (?)";
    private final static String UPDATE="UPDATE contact  SET  name=?,owner_id=?, positions_id=?,email=?, skype=? WHERE id=?";
    private final static String SELECTALL="select c.name, t.name,  u.name, p.name, ph.phone_value, pht.name,c.email, c.skype, com.name, c.id,  comment.date_of_create, comment.comment, file.date_of_create, file.name, file.data, t.id, ph.id, pht.id,c.owner_id,c.positions_id  from contact c "+
    " LEFT join contact_tag ct on c.id=ct.contact_id"+
    " LEFT join  tag t on ct.tag_id=t.id"+
    " LEFT join users u on c.owner_id=u.id"+
    " LEFT join positions p on c.positions_id=p.id"+
    " LEFT join contact_phone  cf on c.id=cf.contact_id"+
    " LEFT join phone ph on ph.id=cf.phone_id"+
    " LEFT join phone_type pht on pht.id=ph.phone_type_id"+
            " LEFT JOIN  company com ON c.company_id=com.id"+
            " left join contact_comment cc on c.id=cc.contact_id"+
            " left join comment ON cc.comment_id=comment.id"+
            " left join contact_file cfile on c.id=cfile.contact_id"+
            " LEFT JOIN file on cfile.file_id=file.id"+
            " where c.is_deleted='f'"+
    " order BY c.id";
    private final static  String DELETE ="update contact SET is_deleted=TRUE WHERE id=?";
    private final  static String SELECTCONTACT="select c.name, t.name,  u.name, p.name, ph.phone_value, pht.name, c.email, c.skype, com.name, c.id,  comment.date_of_create, comment.comment, file.date_of_create, file.name, file.data, t.id, ph.id, pht.id, c.owner_id, c.positions_id from contact c"+
            " left JOIN contact_tag ct on c.id=ct.contact_id"+
            " left JOIN tag t on ct.tag_id=t.id"+
            " left JOIN  users u on c.owner_id=u.id"+
            " LEFT join positions p on c.positions_id=p.id"+
            " LEFT  JOIN  contact_phone cf ON c.id=cf.contact_id"+
            " LEFT  JOIN  phone ph on cf.phone_id=ph.id"+
            " left join phone_type pht on pht.id = ph.phone_type_id"+
            " LEFT JOIN  company com ON c.company_id=com.id"+
            " left join contact_comment cc on c.id=cc.contact_id"+
            " left join comment ON cc.comment_id=comment.id"+
            " left join contact_file cfile on c.id=cfile.contact_id"+
            " LEFT JOIN file on cfile.file_id=file.id"+
              " where c.id=? AND c.is_deleted='f'" ;
    /*Имя Фамилия
    Тег (тектовое поле, их может быть сколько угодно у обекта)
    Ответственный (выпадающий список с пользователями системы)
    Должность
    Номер телефона
    Email
            Skype*/
    private static final String INSERT_INTO_CONTACT_PHONE = "INSERT INTO contact_phone  (contact_id, phone_id)VALUES (?,?)";

    public ContactDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getSelectQuery() {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    protected List<Contact> parseResultSet(ResultSet rs) {

        List<Contact> setContact = new LinkedList<>();
        Contact contact = new Contact();
        Set<Tag> TagSet = new HashSet<>();
        Set<Phone> PhoneSet = new HashSet<>();
        Set<Comment> CommentSet = new HashSet<>();
        Set<com.becomejavasenior.File> fileSet = new HashSet<>();
        Phone phone;
        Tag tag;
        PhoneType phoneType;
        try {
            while (rs.next()) {
                if (contact.getId() == (rs.getInt(10))) {
                    tag = new Tag();
                    tag.setName(rs.getString(2));
                    tag.setId(rs.getInt(16));
                    TagSet.add(tag);
                    phone = new Phone();
                    phone.setValue(rs.getString(5));
                    phone.setId(rs.getInt(17));
                    phoneType = new PhoneType();
                    phoneType.setName(rs.getString(6));
                    phoneType.setId(rs.getInt(18));
                    phone.setPhoneType(phoneType);
                    PhoneSet.add(phone);
                    Comment comment = new Comment();
                    Date myDate = rs.getDate(11);
                    if (myDate != null) {
                        comment.setDateOfCreate(myDate);
                    }
                    comment.setComment(rs.getString(12));
                    CommentSet.add(comment);
                    com.becomejavasenior.File file = new com.becomejavasenior.File();
                    Date fDate = (rs.getDate(13));
                    if (fDate != null) {
                        file.setDate(fDate);
                    }
                    file.setName(rs.getString(14));
                    byte[] fbyte = rs.getBytes(15);
                    file.setData(fbyte);
                    fileSet.add(file);
                } else {
                    contact = new Contact();
                    contact.setId(rs.getInt(10));
                    TagSet = new HashSet<>();
                    PhoneSet = new HashSet<>();
                    CommentSet = new HashSet<>();
                    fileSet = new HashSet<>();
                    tag = new Tag();
                    tag.setName(rs.getString(2));
                    tag.setId(rs.getInt(16));
                    TagSet.add(tag);
                    phone = new Phone();
                    phone.setValue(rs.getString(5));
                    phone.setId(rs.getInt(17));
                    phoneType = new PhoneType();
                    phoneType.setName(rs.getString(6));
                    phoneType.setId(rs.getInt(18));
                    PhoneSet.add(phone);
                    Comment comment = new Comment();
                    Date myDate = rs.getDate(11);
                    if (myDate != null) {
                        comment.setDateOfCreate(myDate);
                    }
                    comment.setComment(rs.getString(12));
                    CommentSet.add(comment);
                    com.becomejavasenior.File file = new com.becomejavasenior.File();
                    java.sql.Date fDate = rs.getDate(13);
                    if (fDate != null) {
                        file.setDate(fDate);
                    }
                    file.setName(rs.getString(14));
                    byte[] fbyte = rs.getBytes(15);
                    file.setData(fbyte);
                    fileSet.add(file);
                    contact.setName(rs.getString(1));
                    contact.setPhones(PhoneSet);
                    contact.setTags(TagSet);
                    contact.setFiles(fileSet);
                    contact.setComments(CommentSet);
                    contact.setEmail(rs.getString(7));
                    contact.setSkype(rs.getString(8));
                    Company company = new Company();
                    company.setName(rs.getString(9));
                    contact.setCompany(company);
                    User user = new User();
                    user.setName(rs.getString(3));
                    user.setId(rs.getInt(19));
                    contact.setOwner(user);

                    Position position = new Position();
                    position.setName(rs.getString(4));
                    position.setId(rs.getInt(20));
                    contact.setPosition(position);
                    setContact.add(contact);
                }
            }
        } catch (SQLException e) {

        }
        return setContact;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Contact object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Contact object) throws PersistException {

    }

    @Override
    public Contact readById(int id) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Contact contact = new Contact();
        try {
            connection = this.connection;
            preparedStatement = connection.prepareStatement(SELECTCONTACT);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            for (Contact rescontact : parseResultSet(res)) {
                contact = rescontact;
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("нет такого контакта с таким " + id);
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public Position getPosition(int key){
        Position position = new Position();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.connection;
            preparedStatement = connection.prepareStatement("SELECT id, name FROM positions WHERE id=?");
            preparedStatement.setInt(1, key);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                position.setName(rs.getString("name"));
                position.setId(key);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return position;
    }

    @Override
    public Set<Position> getAllPosition(){
        Set<Position>positionSet = new HashSet<>();
        Connection connection =null;
        PreparedStatement preparedStatement=null;
        try{
            connection=this.connection;
            preparedStatement=connection.prepareStatement("SELECT * from positions");
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()){
                Position position = new Position();
                position.setId(resultSet.getInt(1));
                position.setName(resultSet.getString(2));
                positionSet.add(position);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionSet;
    }

    @Override
    public int setPosition(Position position) {
        PreparedStatement prst = null;
        Connection connection = null;
        int id = 0;
        try {
            connection =this.connection;
            prst = connection.prepareStatement(INSERT_INTO_POSITION, Statement.RETURN_GENERATED_KEYS);
            prst.setString(1, position.getName());
            prst.execute();
            ResultSet rs = prst.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            prst.close();
            connection.close();

 //           getConnectoin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Set<User> getAllOwners() {
        Connection connection = null;
        PreparedStatement prst = null;
        Set<User> userSet = new HashSet<>();
        try {
            connection = this.connection;
            prst = connection.prepareStatement("SELECT u.id, u.name from users u WHERE is_deleted='f'");
            ResultSet rs = prst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                userSet.add(user);
            }
            prst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userSet;
    }

    @Override
    public int createContact(Contact contact) {
        PreparedStatement prst = null;
        Connection connection = null;
        int id_contact = 0;
        int id_tag = 0;
        int id_phone = 0;
        int id_comment = 0;
        int id_file = 0;
        try {
            connection = this.connection;
            connection.setAutoCommit(false);

            prst = connection.prepareStatement(INSERT_INTO_CONTACT, Statement.RETURN_GENERATED_KEYS);
            prst.setString(1, contact.getName());
            prst.setInt(2, contact.getPosition().getId());
            if (contact.getCompany() != null)
                prst.setInt(3, contact.getCompany().getId());
            else
                prst.setInt(3, 1);
            prst.setString(4, contact.getEmail());
            prst.setString(5, contact.getSkype());
            prst.setInt(6, contact.getOwner().getId());
            prst.execute();
            ResultSet rs = prst.getGeneratedKeys();
            while (rs.next()) {
                id_contact = rs.getInt(1);
            }
            if (contact.getTags() != null) {
                prst = connection.prepareStatement(INSERT_INTO_TAG, Statement.RETURN_GENERATED_KEYS);
                Set<Tag> contactTagSet = contact.getTags();
                PreparedStatement preparedStatementTMP = prst;
                for (Tag tag : contactTagSet) {
                    preparedStatementTMP.setString(1, " #" + tag.getName());
                    preparedStatementTMP.execute();
                    ResultSet rs1 = preparedStatementTMP.getGeneratedKeys();
                    while (rs1.next()) {
                        id_tag = rs1.getInt(1);
                    }
                    prst = connection.prepareStatement(INSERT_INTO_CONTACT_TAG);
                    prst.setInt(1, id_contact);
                    prst.setInt(2, id_tag);
                    prst.execute();
                }
                /*prst=connection.prepareStatement(INSERT_INTO_CONTACT_TAG);
                prst.setInt(1,id_contact);
                prst.setInt(2, id_tag);
                prst.execute();*/
            }

            if (contact.getPhones() != null) {
                prst = connection.prepareStatement(INSERT_INTO_PHONE, Statement.RETURN_GENERATED_KEYS);

                Set<Phone> contactPhoneSet = contact.getPhones();
                PreparedStatement preparedStatementTMP1 = prst;
                for (Phone phone : contactPhoneSet) {
                    preparedStatementTMP1.setString(1, phone.getValue());
                    preparedStatementTMP1.setInt(2, phone.getPhoneType().getId());
                    preparedStatementTMP1.execute();
                    ResultSet rs3 = preparedStatementTMP1.getGeneratedKeys();
                    while (rs3.next()) {
                        id_phone = rs3.getInt(1);
                    }
                    prst = connection.prepareStatement(INSERT_INTO_CONTACT_PHONE);
                    prst.setInt(1, id_contact);
                    prst.setInt(2, id_phone);
                    prst.execute();
                }
                /*prst=connection.prepareStatement(INSERT_INTO_CONTACT_PHONE);
                prst.setInt(1,id_contact);
                prst.setInt(2, id_phone);
                prst.execute();*/
            }

            if (contact.getComments() != null) {
                Set<Comment> commentSet = contact.getComments();
                for (Comment comment : commentSet) {
                    prst = connection.prepareStatement("INSERT  INTO comment(date_of_create, comment)VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prst.setDate(1, sqlDate);
                    prst.setString(2, comment.getComment());
                    prst.execute();
                    ResultSet rs4 = prst.getGeneratedKeys();
                    while (rs4.next()) {
                        id_comment = rs4.getInt(1);
                    }
                    prst = connection.prepareStatement("INSERT INTO contact_comment (contact_id, comment_id)VALUES (?,?)");
                    prst.setInt(1, id_contact);
                    prst.setInt(2, id_comment);
                    prst.execute();
                }
            }
            if (contact.getFiles() != null) {
                Set<com.becomejavasenior.File> fileSet = contact.getFiles();
                for (com.becomejavasenior.File file : fileSet) {
                    prst = connection.prepareStatement("INSERT INTO file (date_of_create, name, data)VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prst.setDate(1, sqlDate);
                    prst.setString(2, file.getName());
                    prst.setBytes(3, file.getData());
                    prst.execute();
                    ResultSet rs5 = prst.getGeneratedKeys();
                    while (rs5.next()) {
                        id_file = rs5.getInt(1);
                    }
                    prst = connection.prepareStatement("INSERT INTO contact_file (contact_id, file_id)VALUES (?,?)");
                    prst.setInt(1, id_contact);
                    prst.setInt(2, id_file);
                    prst.execute();
                }
            }

            connection.commit();
            prst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id_contact;
    }



    @Override
    public void updateContact(Contact contact) {
        PreparedStatement prst = null;
        Connection connection = null;
        try {
            //SET  name=?,owner_id=?, positions_id=?,email=?, skype=? WHERE id=?;"
            connection = this.connection;
            connection.setAutoCommit(false);
            prst = connection.prepareStatement(UPDATE);
            prst.setInt(6, contact.getId());
            prst.setString(1, contact.getName());
            prst.setInt(2, contact.getOwner().getId());
            prst.setInt(3, contact.getPosition().getId());
            prst.setString(4, contact.getEmail());
            prst.setString(5, contact.getSkype());
            prst.executeUpdate();

            Set<Tag> contactTagSet = contact.getTags();
            for (Tag tag : contactTagSet) {
                prst = connection.prepareStatement("UPDATE tag SET name=? WHERE id=?");
                prst.setInt(2, tag.getId());
                prst.setString(1, tag.getName());
                prst.executeUpdate();
            }
            Set<Phone> phoneSet = contact.getPhones();
            for (Phone phone : phoneSet) {
                prst = connection.prepareStatement("UPDATE phone SET phone_value=?,phone_type_id=? WHERE id=?");
                prst.setInt(3, phone.getId());
                prst.setString(1, phone.getValue());
                prst.setInt(2, phone.getPhoneType().getId());
                prst.executeUpdate();
            }
            connection.commit();
            prst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Contact> getAllAboveContact() {
        List<Contact> contactSet = new ArrayList<>();
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = this.connection;
            st = connection.prepareStatement(SELECTALL);
            ResultSet resultSet = st.executeQuery();
            contactSet = parseResultSet(resultSet);
            st.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactSet;
    }

//    @Override
//    public Connection getConnectoin() throws SQLException {
//        return new DaoFactoryImpl().getConnection();
//
//    }

    @Override
    public void deleteContactById(int id) {
        Connection connection = null;
        PreparedStatement prst = null;
        boolean res = false;
        try {
            connection = this.connection;
            prst = connection.prepareStatement(DELETE);
            prst.setInt(1, id);
            prst.executeUpdate();
            prst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact findContact(Contact contact) {
        return null;
    }

    @Override
    public Set<PhoneType> getAllPhoneType(){
        Set<PhoneType>phoneTypeSet = new HashSet<>();
        Connection connection =null;
        PreparedStatement preparedStatement=null;
        try{
            connection= this.connection;
            preparedStatement=connection.prepareStatement("SELECT * from phone_type");
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()){
                PhoneType phoneType = new PhoneType();
                phoneType.setId(resultSet.getInt(1));
                phoneType.setName(resultSet.getString(2));
                phoneTypeSet.add(phoneType);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){e.printStackTrace();}
        return phoneTypeSet;
    }

    @Override
    public PhoneType readPhoneTypeById(int id) {
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        PhoneType phoneType = new PhoneType();
        try{
            connection = this.connection;
            preparedStatement= connection.prepareStatement("SELECT * FROM phone_type WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                phoneType.setId(Integer.parseInt(rs.getString("id")));
                phoneType.setName(rs.getString("name"));
            }
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println("нет такого PhoneType с таким "+id);
            e.printStackTrace();
        }
        return phoneType;
    }

    @Override
    public int insertPhone(Phone phone) {
        PreparedStatement prst = null;
        Connection connection = null;
        int id=0;
        try {
            connection= this.connection;
            prst =connection.prepareStatement(INSERT_INTO_PHONE, Statement.RETURN_GENERATED_KEYS);
            prst.setString(1, phone.getValue());
            prst.setInt(2, phone.getPhoneType().getId());
            prst.execute();
            ResultSet rs =prst.getGeneratedKeys();
            while(rs.next()){
                id=rs.getInt(1);
            }
            prst.close();
            connection.close();

//            getConnectoin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Phone readPhoneById(int id) {
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        Phone phone = new Phone();
        try{
            connection = this.connection;
            preparedStatement= connection.prepareStatement("SELECT * FROM phone WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                phone.setId(Integer.parseInt(rs.getString("id")));
                phone.setValue(rs.getString("phone_value"));
                phone.setPhoneType(readPhoneTypeById(Integer.parseInt(rs.getString("phone_type_id"))));
            }
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println("нет такого Phone с таким "+id);
            e.printStackTrace();
        }
        return phone;
    }

    @Override
    public Position readPositionByName(String name) {
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        Position position = null;
        try{
            connection = this.connection;
            preparedStatement= connection.prepareStatement("SELECT * FROM positions WHERE name=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                position = new Position();
                position.setId(Integer.parseInt(rs.getString("id")));
                position.setName(rs.getString("name"));
            }
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return position;
    }

    @Override
    public int insertComment(Comment comment) {
        PreparedStatement prst = null;
        Connection connection = null;
        int id=0;
        try {
            connection= this.connection;
            prst =connection.prepareStatement(INSERT_INTO_COMMENT, Statement.RETURN_GENERATED_KEYS);
            prst.setTimestamp(1, new Timestamp(new GregorianCalendar().getTimeInMillis()));
            prst.setString(2, comment.getComment());
            prst.execute();
            ResultSet rs =prst.getGeneratedKeys();
            while(rs.next()){
                id=rs.getInt(1);
            }
            prst.close();
            connection.close();

//            getConnectoin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Comment readCommentById(int id) {
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        Comment comment = new Comment();
        try{
            connection = this.connection;
            preparedStatement= connection.prepareStatement("SELECT * FROM comment WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                comment.setId(Integer.parseInt(rs.getString("id")));
                comment.setComment(rs.getString("comment"));
                comment.setDateOfCreate(rs.getTimestamp("date_of_create"));
            }
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println("нет такого Comment с таким "+id);
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public int insertTag(Tag tag) {
        PreparedStatement prst = null;
        Connection connection = null;
        int id=0;
        try {
            connection= this.connection;
            prst =connection.prepareStatement(INSERT_INTO_TAG, Statement.RETURN_GENERATED_KEYS);
            prst.setString(1, tag.getName());
            prst.execute();
            ResultSet rs =prst.getGeneratedKeys();
            while(rs.next()){
                id=rs.getInt(1);
            }
            prst.close();
            connection.close();

//            getConnectoin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Tag readTagByName(String name) {
        PreparedStatement preparedStatement=null;
        Connection connection=null;
        Tag tag = null;
        try{
            connection = this.connection;
            preparedStatement= connection.prepareStatement("SELECT * FROM tag WHERE name=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                tag = new Tag();
                tag.setId(Integer.parseInt(rs.getString("id")));
                tag.setName(rs.getString("name"));
            }
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tag;
    }

}