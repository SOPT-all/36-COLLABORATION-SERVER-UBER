package sopt.uber.core.domain;

import jakarta.persistence.*;

@Entity
public class Uber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String departures;

    @Column(nullable = false)
    private String destination;

    // 객체 생성을 제한적으로 허용하기 위한 접근 제어자 변경
    protected Uber() {

    }

    public Uber(String departures, String destination) {
        this.departures = departures;
        this.destination = destination;
    }
}
