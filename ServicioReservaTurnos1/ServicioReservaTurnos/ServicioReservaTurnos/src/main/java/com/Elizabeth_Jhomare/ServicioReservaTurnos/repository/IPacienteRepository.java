package com.Elizabeth_Jhomare.ServicioReservaTurnos.repository;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
}

