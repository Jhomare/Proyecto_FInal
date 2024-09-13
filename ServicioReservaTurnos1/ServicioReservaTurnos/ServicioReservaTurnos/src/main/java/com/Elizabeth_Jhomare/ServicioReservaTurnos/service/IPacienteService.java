package com.Elizabeth_Jhomare.ServicioReservaTurnos.service;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    //CRUD - ABM
    Paciente guardar (Paciente paciente);
    Paciente buscarPorId(Long id);
    List<Paciente> listarTodos();
    void actualizar(Paciente paciente);
    void eliminar(Long id);
}
