package com.Elizabeth_Jhomare.ServicioReservaTurnos.controller;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Turno;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private ITurnoService turnoService;

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.guardar(turno));
    }


    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        List<Turno> turnos = turnoService.listarTodos();
        System.out.println("Turnos enviados: " + turnos.size());
        return ResponseEntity.ok(turnos);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizar(@PathVariable Long id, @RequestBody Turno turno) {
        Turno turnoActualizado = turnoService.actualizar(turno);
        return ResponseEntity.ok(turnoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

