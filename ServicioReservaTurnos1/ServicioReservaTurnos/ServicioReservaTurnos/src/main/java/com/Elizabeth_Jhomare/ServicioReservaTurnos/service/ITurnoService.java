package com.Elizabeth_Jhomare.ServicioReservaTurnos.service;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Turno;

import java.util.List;

public interface ITurnoService {
    //CRUD
    Turno guardar(Turno turno);
    Turno buscarPorId(Long id);
    List<Turno> listarTodos();

    Turno actualizar(Turno turno);

    void eliminar(Long id);

    Turno buscarPorId(Turno turno);

    Turno buscarPorId();
}

