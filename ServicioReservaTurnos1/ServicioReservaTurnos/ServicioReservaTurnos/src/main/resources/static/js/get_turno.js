window.addEventListener('load', function () {
    const url = '/turnos';
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);  // Verifica los datos que llegan

            const turnoTableBody = document.getElementById("turnoTableBody");
            turnoTableBody.innerHTML = ""; // Limpiar el contenido anterior

            data.forEach(turno => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${turno.id}</td>
                    <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                    <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                    <td>${new Date(turno.fechaHora).toLocaleString()}</td>

                `;
                turnoTableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error al obtener los turnos:', error);
        });
});