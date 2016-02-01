package com.becomejavasenior.daoContact;

import com.becomejavasenior.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by vlad on 27.11.15.
 */
public interface ContactDao {


    public Contact readById(int id);
    public int createContact(Contact contact);
    public void deleteContactById(int id);

    public Contact findContact(Contact contact);
    public void updateContact(Contact contact);

    public List<Contact> getAllAboveContact();
//    public   Connection getConnectoin() throws SQLException;

    public Position getPosition(int key);
    public int setPosition(Position position);
    public Set<Position> getAllPosition();
    public Set<PhoneType> getAllPhoneType();
    public PhoneType readPhoneTypeById(int id);
    public int insertPhone(Phone phone);
    public Phone readPhoneById(int id);
    public Position readPositionByName(String name);
    public int insertComment(Comment comment);
    public Comment readCommentById(int id);
    public int insertTag(Tag tag);
    public Tag readTagByName(String name);
}
