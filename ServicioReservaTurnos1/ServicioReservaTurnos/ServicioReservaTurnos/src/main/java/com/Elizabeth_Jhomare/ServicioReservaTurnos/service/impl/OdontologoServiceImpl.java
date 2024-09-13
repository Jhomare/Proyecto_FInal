package com.Elizabeth_Jhomare.ServicioReservaTurnos.service.impl;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Odontologo;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.exception.ResourceNotFoundException;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.repository.IOdontologoRepository;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoServiceImpl implements IOdontologoService {

    @Autowired
    private IOdontologoRepository iOdontologoRepository;


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        if (odontologo == null) {
            throw new IllegalArgumentException("El odontólogo no puede ser null");
        }
        return iOdontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        //va a buscar al odontologo y lo va a guardar en odontologoBuscado
        //o va a guardar un null en el odontologoBuscado
        Optional<Odontologo> odontologoBuscado = iOdontologoRepository.findById(id);
        if (odontologoBuscado.isPresent()) {
            return odontologoBuscado.get();
        } else {

            throw new ResourceNotFoundException("No se encontró el odontólogo con id " + id);

        }
    }
    @Override
    public void eliminar(Long id) {
        if (!iOdontologoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró el odontólogo con id " + id);
        }
        iOdontologoRepository.deleteById(id);
    }
    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        if (odontologo == null || odontologo.getId() == null) {
            throw new IllegalArgumentException("El odontólogo no puede ser null");
        }
        if (!iOdontologoRepository.existsById(odontologo.getId())) {
            throw new ResourceNotFoundException("No se encontró el odontólogo con id " + odontologo.getId());
        }
        return iOdontologoRepository.save(odontologo); // Devuelve el odontólogo actualizado
    }
    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologos = iOdontologoRepository.findAll();
        if (odontologos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron odontólogos registrados");
        }
        return odontologos;
    }
    @Override
    public Odontologo buscarPorMatricula(String matricula) {
        Odontologo odontologo = iOdontologoRepository.findByMatricula(matricula);
        if (odontologo == null) {
            throw new ResourceNotFoundException("No se encontró el odontólogo con matrícula " + matricula);
        }
        return odontologo;
    }

}
