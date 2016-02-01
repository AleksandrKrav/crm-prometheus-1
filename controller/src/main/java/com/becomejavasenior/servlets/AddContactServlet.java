package com.becomejavasenior.servlets;

import com.becomejavasenior.*;
import com.becomejavasenior.config.ApplicationContextConfig;
import com.becomejavasenior.daoContact.ContactDao;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Shevchenko on 19.12.2015.
 */
@MultipartConfig
public class AddContactServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddContactServlet.class);

    private ContactDao contactDao;
    private UserService userService;

    private Contact contact;
    private Phone phone;
    private Position position;
    private Comment comment;
    private Tag tag;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        userService = context.getBean(UserService.class);
        contactDao = context.getBean(ContactDao.class);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            contact = new Contact();
            contact.setName(req.getParameter("nameContact"));

            tag = contactDao.readTagByName(req.getParameter("tagContact"));
            if (tag == null){
                tag = new Tag();
                tag.setName(req.getParameter("tagContact"));
                contactDao.insertTag(tag);
            }
            contact.setTags(new HashSet<Tag>(Arrays.asList(tag)));

            position = contactDao.readPositionByName(req.getParameter("selPositionContact"));
            if (position == null){
                position = new Position();
                position.setName(req.getParameter("selPositionContact"));
                contactDao.setPosition(position);
            }
            contact.setPosition(position);

            contact.setOwner(userService.getById(Integer.parseInt(req.getParameter("selResponsibleContact"))));

            phone = new Phone();
            phone.setPhoneType(contactDao.readPhoneTypeById(Integer.parseInt(req.getParameter("selPhoneType"))));
            phone.setValue(req.getParameter("phoneNum"));
            contact.setPhones(new HashSet<Phone>(Arrays.asList(contactDao.readPhoneById(contactDao.insertPhone(phone)))));

            contact.setEmail(req.getParameter("emailContact"));

            contact.setSkype(req.getParameter("skypeContact"));

            comment = new Comment();
            comment.setComment(req.getParameter("commentContact"));
            comment = contactDao.readCommentById(contactDao.insertComment(comment));
            contact.setComments(new HashSet<Comment>(Arrays.asList(comment)));

            com.becomejavasenior.File file;
            Set<com.becomejavasenior.File> files = new HashSet<>();
            for (Part part : req.getParts()) {
                String fileName = getFileName(part);
                if (fileName != null) {
                    file = new com.becomejavasenior.File();
                    file.setDate(new Date());
                    file.setName(fileName);
                    try (InputStream in = part.getInputStream();
                         ByteArrayOutputStream output = new ByteArrayOutputStream();) {
                        int read;
                        byte[] bufferData = new byte[(int) part.getSize()];
                        while ((read = in.read(bufferData)) != -1) {
                            output.write(bufferData, 0, read);
                        }
                        file.setData(output.toByteArray());
                    }
                    files.add(file);
                }
            }
            contact.setFiles(files);

            contactDao.createContact(contact);
        }  catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String getFileName(final Part part) {
//        final String partHeader = part.getHeader("content-disposition");
//        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
