document.addEventListener('DOMContentLoaded', function () {
    // Pedir el ID del odontólogo
    const id = prompt('Ingrese el ID del odontólogo que desea modificar:');

    if (id) {
        // Cargar los datos del odontólogo
        fetch(`/odontologos/${id}`)
            .then(response => response.json())
            .then(data => {
                document.querySelector('#nombre').value = data.nombre;
                document.querySelector('#apellido').value = data.apellido;
                document.querySelector('#matricula').value = data.matricula;
            })
            .catch(error => console.error('Error al cargar los datos:', error));
    } else {
        alert('ID no proporcionado o no válido.');
    }

    // Manejar el formulario de actualización
    document.getElementById('update_odontologo').onsubmit = function (e) {
        e.preventDefault();

        if (!id) {
            alert('No se proporcionó un ID válido.');
            return;
        }

        // Obtener los datos actuales del formulario
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };

        // Confirmar cada campo individualmente
        const confirmNombre = confirm('¿Desea actualizar el nombre a: ' + formData.nombre + '?');
        if (!confirmNombre) return;

        const confirmApellido = confirm('¿Desea actualizar el apellido a: ' + formData.apellido + '?');
        if (!confirmApellido) return;

        const confirmMatricula = confirm('¿Desea actualizar la matrícula a: ' + formData.matricula + '?');
        if (!confirmMatricula) return;

        // Confirmar actualización de todos los campos juntos
        const confirmUpdate = confirm('¿Está seguro de que desea actualizar estos datos?');
        if (!confirmUpdate) return;

        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(`/odontologos/${id}`, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Odontologo actualizado </div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = 'block';
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Error, intente nuevamente</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = 'block';
            });
    };
});