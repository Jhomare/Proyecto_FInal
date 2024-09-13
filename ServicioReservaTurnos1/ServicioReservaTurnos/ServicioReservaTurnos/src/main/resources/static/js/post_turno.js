document.addEventListener('DOMContentLoaded', function() {
window.addEventListener('load', function () {
    const form = document.getElementById('add_new_turno');
    const responseDiv = document.getElementById('response');

    if (form) {
        form.addEventListener('submit', function (event) {
            event.preventDefault();  // Evita el envío por defecto del formulario

            const pacienteId = document.getElementById('pacienteId').value;
            const odontologoId = document.getElementById('odontologoId').value;
            const fechaHora = document.getElementById('fechaHora').value;

            const turno = {
                paciente: { id: pacienteId },
                odontologo: { id: odontologoId },
                fechaHora: fechaHora
            };

            fetch('http://localhost:8081/turnos', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(turno)
            })
            .then(response => response.json())
            .then(data => {
                responseDiv.style.display = 'block';
                responseDiv.innerHTML = `<div class="alert alert-success">Turno agregado exitosamente. ID: ${data.id}</div>`;
            })
            .catch(error => {
                responseDiv.style.display = 'block';
                responseDiv.innerHTML = `<div class="alert alert-danger">Error al agregar el turno: ${error.message}</div>`;
            });
        });
    } else {
        console.error('El formulario con ID "add_new_turno" no se encontró.');
    }
});
});