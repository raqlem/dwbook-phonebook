package com.dwbook.phonebook.dao;


import com.dwbook.phonebook.representations.Contact;
import com.dwbook.phonebook.dao.mappers.ContactMapper;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


public interface ContactDAO {

    @Mapper(ContactMapper.class)
    @SqlQuery("select * from contact where id = :id")
    Contact getContactById(@Bind("id") int id);

}
