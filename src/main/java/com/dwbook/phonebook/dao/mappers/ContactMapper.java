package com.dwbook.phonebook.dao.mappers;

import com.dwbook.phonebook.representations.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class ContactMapper implements ResultSetMapper<Contact>
{
    public Contact map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Contact(r.getInt("id"), r.getString("firstName"),
                r.getString("lastName"),r.getString("phone"));
    }
}
