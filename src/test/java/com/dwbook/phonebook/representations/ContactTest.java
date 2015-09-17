package com.dwbook.phonebook.representations;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class ContactTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Contact contact = new Contact(1, "Lucas", "Avila", "91949561");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/contact.json"), Contact.class));

        assertThat(MAPPER.writeValueAsString(contact)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Contact expectedContact = new Contact(1, "Lucas", "Avila", "91949561");
        Contact contact = MAPPER.readValue(fixture("fixtures/contact.json"), Contact.class);

        assertThat(expectedContact)
                .isEqualTo(contact);
    }

}
