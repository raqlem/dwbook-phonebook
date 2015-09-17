package com.dwbook.phonebook.representations;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Objects;

public class Contact {
    private int id;

    @NotBlank
    @Length(min=2, max=255)
    private String firstName;

    @NotBlank
    @Length(min=2, max=255)
    private String lastName;

    @NotBlank
    @Length(min=2, max=30)
    private String phone;

    public Contact() {
    }

    public Contact(int id, String firstName, String lastName,
                   String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }

        final Contact that = (Contact) o;

        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getFirstName(), that.getFirstName()) &&
                Objects.equals(this.getLastName(), that.getLastName()) &&
                Objects.equals(this.getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPhone());
    }

}