package sopt.uber.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sopt.uber.api.dto.res.TaxiRes;
import sopt.uber.core.domain.Taxi;

import java.util.List;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {

    @Query("SELECT new sopt.uber.api.dto.res.TaxiRes(t.id, t.type, t.min, t.max, t.guests, t.comment) " +
            "FROM Taxi t WHERE t.id IN (1, 2)")
    List<TaxiRes> findAllTaxiListResponse();

    @Query("SELECT new sopt.uber.api.dto.res.TaxiRes(t.id, t.type, t.min, t.max, t.guests, t.comment) " +
            "FROM Taxi t WHERE t.id IN (2, 3)")
    List<TaxiRes> findAllCaseTaxiListResponse();
}