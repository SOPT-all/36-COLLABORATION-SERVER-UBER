package sopt.uber.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Taxi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int min;
    private int max;
    private int guests;
    private String comment;
    private String image;

    public Taxi(String type, int min, int max, int guests, String comment, String image) {
        this.type = type;
        this.min = min;
        this.max = max;
        this.guests = guests;
        this.comment = comment;
        this.image = image;
    }

    protected Taxi() {

    }
}
