package org.example.haulmont.domain;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Bank bank;

    @NotBlank(message = "Обязательное поле")
    @Length(min = 2, max = 16, message = "Введите от 2 до 16 символов")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Обязательное поле")
    @Length(min = 2, max = 16, message = "Введите от 2 до 16 символов ")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Обязательное поле")
    @Length(min = 2, max = 16, message = "Введите от 2 до 16 символов")
    @Column(name = "patronymic")
    private String patronymic;

    @Email(message = "Email некорректен")
    @NotBlank(message = "Обязательное поле")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Номер телефона должен содержать 10 цифр")
    @NotBlank(message = "Обязательное поле")
    @Column(name = "phone")
    private String phone;

    @Pattern(regexp = "\\d{6}", message = "Номер паспорта должен содержать 6 цифр")
    @NotBlank(message = "Обязательное поле")
    @Column(name = "passport_number")
    private String passportNumber;


    public Client() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passportId='" + passportNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
