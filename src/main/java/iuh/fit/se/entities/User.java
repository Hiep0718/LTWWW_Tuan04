package iuh.fit.se.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.lang.model.element.Name;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    @NotNull(message = "First name must be not null")
    @NotEmpty(message = "First name must be not empty")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;
    @Column(name = "last_name")
    @NotNull(message = "Last name must be not null")
    @NotEmpty(message = "Last name must be not empty")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName;
    @Column(name = "email", unique = true)
    @NotEmpty(message = "Email must be not empty")
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "Gender")
    private String Gender;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, String birthDate, String Gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.Gender = Gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }
    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +" email= " + email + ", password=" + password + ", birthDate=" + birthDate + ", Gender=" + Gender + "]";
    }
}
