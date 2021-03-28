package org.example.haulmont.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    //список клиентов банка
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Client> clients = new HashSet<>();

    //список кредитов банка
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Credit> credits = new HashSet<>();


    public Bank() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        if (clients != null)
            this.clients = clients;
    }

    public void addClient(Client client) {
        if (client != null) {
            this.clients.add(client);
            client.setBank(this);
        }
    }

    public Set<Credit> getCredits() {
        return credits;
    }

    public void setCredits(Set<Credit> credits) {
        if (credits != null)
            this.credits = credits;
    }

    public void addCredit(Credit credit) {
        if (credit != null) {
            this.credits.add(credit);
            credit.setBank(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", clients=" + clients +
                ", credits=" + credits +
                '}';
    }
}
