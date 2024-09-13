package com.Elizabeth_Jhomare.ServicioReservaTurnos.service.impl;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Paciente;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.exception.ResourceNotFoundException;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.repository.IPacienteRepository;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    private IPacienteRepository iPacienteRepository;



    @Override
    public Paciente guardar(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("Elpaciente no puede ser null");
        }
        return iPacienteRepository.save(paciente);
    }

    @Override
    public Paciente buscarPorId(Long id) {
        System.out.println("Buscando paciente con ID: " + id);
        return iPacienteRepository.findById(id)
                .map(paciente -> {
                    System.out.println("Paciente encontrado: " + paciente);
                    return paciente;
                })
                .orElseThrow(() -> {
                    System.out.println("Paciente no encontrado con ID: " + id);
                    return new ResourceNotFoundException("No se encontró el paciente con id " + id);
                });
    }
    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = iPacienteRepository.findAll();
        if (pacientes.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron paciente registrados");
        }
        return pacientes;
    }

    @Override
    public void actualizar(Paciente paciente) {
        if (paciente == null || paciente.getId() == null) {
            throw new IllegalArgumentException("El paciente no puede ser null");
        }
        if (!iPacienteRepository.existsById(paciente.getId())) {
            throw new ResourceNotFoundException("No se encontró el paciente con id " + paciente.getId());
        }
        iPacienteRepository.save(paciente);
    }

    @Override
    public void eliminar(Long id) {
        if (!iPacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el paciente con id " + id);
        }
        iPacienteRepository.deleteById(id);
    }
}