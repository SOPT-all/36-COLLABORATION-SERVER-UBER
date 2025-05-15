package sopt.uber.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyword;
    private String address;
    private LocalDateTime date;

//    private Uber uber;  관계 매핑 필요

    public Search() {}

    public Search(String keyword, String address, LocalDateTime date) {
        this.keyword = keyword;
        this.address = address;
        this.date = date;
    }
}
