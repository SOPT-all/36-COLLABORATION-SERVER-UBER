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

    // 객체 생성을 제한적으로 허용하기 위한 접근 제어자 변경
    protected Uber() {

    }

    public Uber(String departures, String destination) {
        this.departures = departures;
        this.destination = destination;
    }
}
