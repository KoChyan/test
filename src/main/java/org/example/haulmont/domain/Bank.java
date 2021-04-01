package org.example.haulmont.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "BANK")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<Credit> credits = new ArrayList<>();


    public Bank() {
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
            clients.add(client);
            client.setBank(this);
        }
    }

    public void removeClient(Client client){
        if(client != null){
            clients.remove(client);
            client.setBank(null);
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

    public void removeCredit(Credit credit){
        if(credit != null){
            credits.remove(credit);
            credit.setBank(null);
        }
    }

    @Override
    public String toString() {
        return "Bank{" +
                "clients=" + clients +
                ", credits=" + credits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id.equals(bank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
