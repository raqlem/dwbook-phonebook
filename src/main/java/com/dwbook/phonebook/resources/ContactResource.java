package com.dwbook.phonebook.resources;

import com.dwbook.phonebook.dao.ContactDAO;
import com.dwbook.phonebook.representations.Contact;
import org.skife.jdbi.v2.DBI;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

    private final ContactDAO contactDao;
    private final Validator validator;

    public ContactResource(DBI jdbi, Validator validator) {
        contactDao = jdbi.onDemand(ContactDAO.class);
        this.validator = validator;
    }

    @GET
    @Path("/{id}")
    public Response getContact(@PathParam("id") int id) {
        Contact contact = contactDao.getContactById(id);
        return Response
                .ok(contact)
        .build();
    }

    @POST
    public Response createContact(Contact contact)throws URISyntaxException {
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Contact> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() +": " + violation.getMessage());
            }
            return Response
                    .status(Status.BAD_REQUEST)
                    .entity(validationMessages)
                    .build();
        }
        else {
            int newContactId = contactDao.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone());
            return Response.created(new URI(String.valueOf(newContactId))).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int id) {
        contactDao.deleteContact(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") int id, Contact contact) {
        contactDao.updateContact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.ok(new Contact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone())).build();
    }
}
