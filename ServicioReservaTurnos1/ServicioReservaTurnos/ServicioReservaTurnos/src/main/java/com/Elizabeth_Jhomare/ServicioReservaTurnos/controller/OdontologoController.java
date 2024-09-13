package com.Elizabeth_Jhomare.ServicioReservaTurnos.controller;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Odontologo;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private IOdontologoService odontologoService;
    //RequestParam url ? parametro = X & parametro = X
    //PathVariable url/pathVariable
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    //listar todos
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @GetMapping("matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizar(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        // Buscar el odontólogo existente
        Odontologo odontologoExistente = odontologoService.buscarPorId(id);
        if (odontologoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar el odontólogo con los nuevos datos
        odontologo.setId(id); // nos aseguramos de que el ID se mantenga
        Odontologo odontologoActualizado = odontologoService.guardar(odontologo);

        return ResponseEntity.ok(odontologoActualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        odontologoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
