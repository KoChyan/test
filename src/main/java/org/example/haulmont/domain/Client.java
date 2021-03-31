package org.example.haulmont.domain;



import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "Пожалуйста, введите имя")
    @Length(min = 2, max = 16, message = "Имя должно быть от 2 до 16 символов длиной")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Пожалуйста, введите фамилию")
    @Length(min = 2, max = 16, message = "Фамилия должна быть от 2 до 16 символов длиной")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Пожалуйста, введите Отчество")
    @Length(min = 2, max = 16, message = "Отчество должно быть от 2 до 16 символов длиной")
    @Column(name = "patronymic")
    private String patronymic;

    @Email(message = "Email некорректен")
    @NotBlank(message = "Пожалуйста, введите Email")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Пожалуйста, введите номер телефона")
    @Length(min = 10, max = 10, message = "Введите 10 символов")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Пожалуйста, введите номер паспорта")
    @Length(min = 6, max = 6, message = "Введите 6 символов")
    @Column(name = "passport_number")
    private String passportNumber;


    public Client() {
    }

    public Client(String name, String surname, String patronymic, String email, String phone, String passportNumber) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.passportNumber = passportNumber;
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
