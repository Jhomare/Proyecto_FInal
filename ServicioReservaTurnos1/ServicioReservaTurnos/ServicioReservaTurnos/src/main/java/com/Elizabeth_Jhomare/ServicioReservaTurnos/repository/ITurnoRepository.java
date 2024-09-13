package com.Elizabeth_Jhomare.ServicioReservaTurnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Turno;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
