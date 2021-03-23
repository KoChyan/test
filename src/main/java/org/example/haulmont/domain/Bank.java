package org.example.haulmont.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


}
