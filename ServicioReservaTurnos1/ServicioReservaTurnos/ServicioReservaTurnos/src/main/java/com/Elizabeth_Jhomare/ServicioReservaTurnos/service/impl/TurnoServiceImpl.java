package com.Elizabeth_Jhomare.ServicioReservaTurnos.service.impl;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Turno;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.exception.ResourceNotFoundException;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.repository.IOdontologoRepository;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.repository.IPacienteRepository;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.repository.ITurnoRepository;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements ITurnoService {

    @Autowired
    private ITurnoRepository iTurnoRepository;

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Override
    public Turno guardar(Turno turno) {
        if (turno == null) {
            throw new IllegalArgumentException("El turno no puede ser null");
        }

        // Verificación de paciente
        if (turno.getPaciente() == null) {
            throw new IllegalArgumentException("El paciente no puede ser null");
        }
        if (!pacienteRepository.existsById(turno.getPaciente().getId())) {
            throw new ResourceNotFoundException("Paciente no encontrado con id: " + turno.getPaciente().getId());
        }

        // Verificación de odontólogo
        if (turno.getOdontologo() == null) {
            throw new IllegalArgumentException("El odontólogo no puede ser null");
        }
        if (!odontologoRepository.existsById(turno.getOdontologo().getId())) {
            throw new ResourceNotFoundException("Odontólogo no encontrado con id: " + turno.getOdontologo().getId());
        }

        return iTurnoRepository.save(turno);
    }

    @Override
    public Turno buscarPorId(Long id) {
        Optional<Turno> turnoBuscado = iTurnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            return turnoBuscado.get();
        } else {
            throw new ResourceNotFoundException("No se encontró el turno con id " + id);
        }
    }

    @Override
    public List<Turno> listarTodos() {
        List<Turno> turnos = iTurnoRepository.findAll();
        System.out.println("Turnos encontrados: " + turnos.size());
        if (turnos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron turnos registrados");
        }
        return turnos;
    }



    @Override
    public Turno actualizar(Turno turno) {
        if (turno == null || turno.getId() == null) {
            throw new IllegalArgumentException("El turno no puede ser null");
        }
        if (!iTurnoRepository.existsById(turno.getId())) {
            throw new ResourceNotFoundException("No se encontró el turno con id " + turno.getId());
        }

        // Validamos que el odontólogo y paciente existan
        if (turno.getPaciente() == null || turno.getOdontologo() == null) {
            throw new IllegalArgumentException("El paciente o el odontólogo no pueden ser null");
        }
        if (!pacienteRepository.existsById(turno.getPaciente().getId())) {
            throw new ResourceNotFoundException("Paciente no encontrado con id: " + turno.getPaciente().getId());
        }
        if (!odontologoRepository.existsById(turno.getOdontologo().getId())) {
            throw new ResourceNotFoundException("Odontólogo no encontrado con id: " + turno.getOdontologo().getId());
        }

        return iTurnoRepository.save(turno);
    }

    @Override
    public void eliminar(Long id) {
        if (!iTurnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el turno con id " + id);
        }
        iTurnoRepository.deleteById(id);
    }

    @Override
    public Turno buscarPorId(Turno turno) {
        throw new UnsupportedOperationException("Método no implementado");
    }

    @Override
    public Turno buscarPorId() {
        throw new UnsupportedOperationException("Método no implementado");
    }
}
