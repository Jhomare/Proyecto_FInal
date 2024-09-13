window.addEventListener('load', function () {
    (function () {
        // Con fetch invocamos a la API de pacientes con el método GET
        // Nos devolverá un JSON con una colección de pacientes
        const url = '/pacientes';
        const settings = {
            method: 'GET'
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                console.log("Datos recibidos:", data);

                for (let paciente of data) {
                    console.log("Procesando paciente", paciente);

                    // Verificar que el elemento de tabla existe
                    var table = document.getElementById("pacienteTable");
                    if (!table) {
                        console.error('No se encontró la tabla con id "pacienteTable"');
                        return;
                    }

                    // Crear una nueva fila en la tabla
                    var pacienteRow = table.insertRow();
                    let tr_id = 'tr_' + paciente.id;
                    pacienteRow.id = tr_id;

                    // Verificar si paciente.domicilio existe antes de acceder a sus propiedades
                    const calle = paciente.domicilio && paciente.domicilio.calle ? paciente.domicilio.calle.toUpperCase() : 'Desconocido';
                    const numero = paciente.domicilio && paciente.domicilio.numero ? paciente.domicilio.numero : 'Desconocido';
                    const localidad = paciente.domicilio && paciente.domicilio.localidad ? paciente.domicilio.localidad.toUpperCase() : 'Desconocido';
                    const provincia = paciente.domicilio && paciente.domicilio.provincia ? paciente.domicilio.provincia.toUpperCase() : 'Desconocido';

                    // Armar las celdas de la fila
                    pacienteRow.innerHTML = '<td class="td_id">' + paciente.id + '</td>' +
                        '<td class="td_nombre">' + (paciente.nombre ? paciente.nombre.toUpperCase() : 'Desconocido') + '</td>' +
                        '<td class="td_apellido">' + (paciente.apellido ? paciente.apellido.toUpperCase() : 'Desconocido') + '</td>' +
                        '<td class="td_dni">' + (paciente.dni ? paciente.dni.toUpperCase() : 'Desconocido') + '</td>' +
                        '<td class="td_fechaAlta">' + (paciente.fechaAlta ? paciente.fechaAlta.toUpperCase() : 'Desconocido') + '</td>' +
                        '<td class="td_calle">' + calle + '</td>' +
                        '<td class="td_numero">' + numero + '</td>' +
                        '<td class="td_localidad">' + localidad + '</td>' +
                        '<td class="td_provincia">' + provincia + '</td>';
                }

            })
            .catch(error => console.error('Error:', error));
    })();

    (function () {
        let pathname = window.location.pathname;
        if (pathname == "/pacienteList.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
