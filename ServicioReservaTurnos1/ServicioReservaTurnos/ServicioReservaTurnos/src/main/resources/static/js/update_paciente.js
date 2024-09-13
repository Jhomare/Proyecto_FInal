document.addEventListener('DOMContentLoaded', function () {
    // Pedir el ID del paciente
    const id = prompt('Ingrese el ID del paciente que desea modificar:');

    if (id) {
        // Cargar los datos del paciente
        fetch(`/pacientes/${id}`)
            .then(response => response.json())
            .then(data => {
                document.querySelector('#nombre').value = data.nombre;
                document.querySelector('#apellido').value = data.apellido;
                document.querySelector('#dni').value = data.dni;
                document.querySelector('#fechaAlta').value = data.fechaAlta;
                document.querySelector('#calle').value = data.domicilio.calle;
                document.querySelector('#numero').value = data.domicilio.numero;
                document.querySelector('#localidad').value = data.domicilio.localidad;
                document.querySelector('#provincia').value = data.domicilio.provincia;
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    } else {
        alert('ID no proporcionado o no válido.');
    }

    // Manejar el formulario de actualización
    document.getElementById('update_paciente').onsubmit = function (e) {
        e.preventDefault();

        if (!id) {
            alert('No se proporcionó un ID válido.');
            return;
        }

        // Obtener los datos actuales del formulario
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaAlta: document.querySelector('#fechaAlta').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value
            }
        };

        // Confirmar cada campo individualmente
        const confirmNombre = confirm('¿Desea actualizar el nombre a: ' + formData.nombre + '?');
        if (!confirmNombre) return;

        const confirmApellido = confirm('¿Desea actualizar el apellido a: ' + formData.apellido + '?');
        if (!confirmApellido) return;

        const confirmDni = confirm('¿Desea actualizar el DNI a: ' + formData.dni + '?');
        if (!confirmDni) return;

        const confirmFechaAlta = confirm('¿Desea actualizar la fecha de alta a: ' + formData.fechaAlta + '?');
        if (!confirmFechaAlta) return;

        const confirmCalle = confirm('¿Desea actualizar la calle a: ' + formData.domicilio.calle + '?');
        if (!confirmCalle) return;

        const confirmNumero = confirm('¿Desea actualizar el número de domicilio a: ' + formData.domicilio.numero + '?');
        if (!confirmNumero) return;

        const confirmLocalidad = confirm('¿Desea actualizar la localidad a: ' + formData.domicilio.localidad + '?');
        if (!confirmLocalidad) return;

        const confirmProvincia = confirm('¿Desea actualizar la provincia a: ' + formData.domicilio.provincia + '?');
        if (!confirmProvincia) return;

        // Confirmar actualización de todos los campos juntos
        const confirmUpdate = confirm('¿Está seguro de que desea actualizar estos datos?');
        if (!confirmUpdate) return;

        // Configurar la solicitud PUT
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        // Enviar la actualización
        fetch(`/pacientes/${id}`, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    'Paciente actualizado correctamente</div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = 'block';
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    'Error al actualizar, intente nuevamente</div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = 'block';
            });
    };
});