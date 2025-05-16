package sopt.uber.core.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "address", nullable = false)
    private String address;

    @CreatedDate
    @Column(name = "date" , updatable = false)
    private LocalDateTime date;

//    private Uber uber;  관계 매핑 필요

    protected Search() {}

    public Search(String keyword, String address, LocalDateTime date) {
        this.keyword = keyword;
        this.address = address;
        this.date = date;
    }
}
