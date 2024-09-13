package com.Elizabeth_Jhomare.ServicioReservaTurnos.service;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);
    Odontologo buscarPorId(Long id);
    void eliminar(Long id);

    Odontologo actualizar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorMatricula(String matricula);
}
