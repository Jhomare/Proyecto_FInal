package com.Elizabeth_Jhomare.ServicioReservaTurnos.repository;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    //    @Query("SELECT o FROM Odontologo o WHERE o.matricula = ?1")
    Odontologo findByMatricula (String matricula);

}
