package sopt.uber.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Uber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departures;
    private String destination;

    public Uber() {

    }

    public Uber(String departures, String destination) {
        this.departures = departures;
        this.destination = destination;
    }
}
