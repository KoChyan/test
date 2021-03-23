package org.example.haulmont.domain;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Long limit;

    private Double interestRate;
}
