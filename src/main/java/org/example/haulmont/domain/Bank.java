package org.example.haulmont.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private static String name = "Money house";

    //список клиентов банка
    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<Client> clients = new ArrayList<>();

    //список кредитов банка
    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<Credit> credits = new ArrayList<>();


    public Bank() {
    }

    public Bank(List<Client> clients, List<Credit> credits) {
        this.clients = clients;
        this.credits = credits;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        if (clients != null)
            this.clients = clients;
    }

    public void addClient(Client client) {
        if (client != null) {
            this.clients.add(client);
            client.setBank(this);
        }
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        if (credits != null)
            this.credits = credits;
    }

    public void addCredit(Credit credit) {
        if (credit != null) {
            this.credits.add(credit);
            credit.setBank(this);
        }
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Bank.name = name;
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
