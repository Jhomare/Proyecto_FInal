package com.Elizabeth_Jhomare.ServicioReservaTurnos.service;

import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Turno;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Paciente;
import com.Elizabeth_Jhomare.ServicioReservaTurnos.entity.Odontologo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TurnoServiceIntegrationTest {

    @Autowired
    private ITurnoService turnoService;

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IOdontologoService odontologoService;

    private Paciente paciente;
    private Odontologo odontologo;

    @Test
    void testGuardarYBuscarTurno() {
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFechaHora(LocalDateTime.now().plusDays(1));

        Turno turnoGuardado = turnoService.guardar(turno);
        assertNotNull(turnoGuardado.getId());

        Turno turnoEncontrado = turnoService.buscarPorId(turnoGuardado.getId());
        assertNotNull(turnoEncontrado);
        assertEquals(paciente.getId(), turnoEncontrado.getPaciente().getId());
        assertEquals(odontologo.getId(), turnoEncontrado.getOdontologo().getId());
    }

    @BeforeEach
    void setUp() {
        paciente = new Paciente();
        paciente.setNombre("Juan");
        paciente.setApellido("Pérez");
        paciente.setDni("12345678");
        paciente = pacienteService.guardar(paciente);

        odontologo = new Odontologo();
        odontologo.setNombre("María");
        odontologo.setApellido("García");
        odontologo.setMatricula("ODO001");
        odontologo = odontologoService.guardar(odontologo);
    }

    @Test
    void testActualizarTurno() {
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFechaHora(LocalDateTime.now().plusDays(4));
        Turno turnoGuardado = turnoService.guardar(turno);

        LocalDateTime nuevaFecha = LocalDateTime.now().plusDays(5);
        turnoGuardado.setFechaHora(nuevaFecha);
        Turno turnoActualizado = turnoService.actualizar(turnoGuardado);

        assertEquals(nuevaFecha, turnoActualizado.getFechaHora());
    }
}