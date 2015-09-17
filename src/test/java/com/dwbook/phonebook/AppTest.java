package com.dwbook.phonebook;


import com.dwbook.phonebook.representations.Contact;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {


    @ClassRule
    public static final DropwizardAppRule<PhonebookConfiguration> RULE =
            new DropwizardAppRule<PhonebookConfiguration>(App.class, "config.yaml");

    private Client client;

    @BeforeClass
    public static void migrateDb() throws Exception {
//        RULE.getApplication().run("db", "migrate", CONFIG_PATH);
    }

    @Before
    public void setUp() throws Exception {
        client = new JerseyClientBuilder().build();
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }

    @Test
    public void testPostContact() {
        Contact expectedContact = new Contact(1,"Lucas", "Avila", "91949561");

        Response response = client.target("http://localhost:" + RULE.getLocalPort() + "/contact")
                .request()
                .post(Entity.entity(expectedContact, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatus()).isEqualTo(201);
    }

    @Test
    public void testGetContact() {
        Contact result = client.target(
                String.format("http://localhost:%d/contact/1", RULE.getLocalPort())
        ).request().get(Contact.class);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getFirstName()).isEqualTo("John");
    }

    @Test
    public void testPutContact() {
        Contact contact = new Contact(2,"Maria", "Avila", "91949561");
        Contact changedContact = client.target(
                String.format("http://localhost:%d/contact/2", RULE.getLocalPort()))
                .request()
                .put(Entity.entity(contact, MediaType.APPLICATION_JSON_TYPE))
                .readEntity(Contact.class);

        assertThat(changedContact.getId()).isEqualTo(2);
        assertThat(changedContact.getFirstName()).isEqualTo(contact.getFirstName());
        assertThat(changedContact.getLastName()).isEqualTo(contact.getLastName());
        assertThat(changedContact.getPhone()).isEqualTo(contact.getPhone());
    }

}
