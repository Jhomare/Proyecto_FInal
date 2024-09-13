package com.Elizabeth_Jhomare.ServicioReservaTurnos.controller;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Paciente;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    //public PacienteController(IPacienteService pacienteService) {
      //  this.pacienteService = pacienteService;
    //}

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> consultarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    //listar todos
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        // Buscar el paciente existente
        Paciente pacienteExistente = pacienteService.buscarPorId(id);
        if (pacienteExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar el paciente con los nuevos datos
        paciente.setId(id); // nos aseguramos de que el ID se mantenga
        Paciente pacienteActualizado = pacienteService.guardar(paciente);

        return ResponseEntity.ok(pacienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.ok().build();
    }

}

