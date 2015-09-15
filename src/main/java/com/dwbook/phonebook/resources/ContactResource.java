package com.dwbook.phonebook.resources;

import com.dwbook.phonebook.dao.ContactDAO;
import com.dwbook.phonebook.representations.Contact;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/contact")
@Produces(MediaType.APPLICATION_JSON)
public class ContactResource {

    private final ContactDAO contactDao;

    public ContactResource(DBI jdbi) {
        contactDao = jdbi.onDemand(ContactDAO.class);
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
        int newContactId = contactDao.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone());
        return Response.created(new URI(String.valueOf(newContactId))).build();
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
