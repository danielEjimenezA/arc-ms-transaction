package com.pichincha.springjparest.repository;

import com.pichincha.springjparest.domain.Transac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacRepository extends JpaRepository<Transac, Long>
{
    @Query("SELECT t FROM Transac t WHERE t.date = :date")
    List<Transac> findAllByDate(LocalDate date);
    
    @Query("SELECT t FROM Transac t WHERE t.account.client.person.id = :personId AND t.date >= :fechaInicio AND t.date <= :fechaFin")
    List<Transac> findByPersonIdAndInitialDateAndFinishDate(
            String personId,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );
}
