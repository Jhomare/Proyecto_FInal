window.addEventListener('load', function () {
    const form = document.getElementById('delete_paciente_form');
    const responseDiv = document.getElementById('response');

    // Función para verificar si un ID ya fue eliminado
    function checkIfDeleted(id) {
        const deletedIds = JSON.parse(localStorage.getItem('deletedIds')) || [];
        return deletedIds.includes(id);
    }

    // Función para marcar un ID como eliminado
    function markAsDeleted(id) {
        let deletedIds = JSON.parse(localStorage.getItem('deletedIds')) || [];
        deletedIds.push(id);
        localStorage.setItem('deletedIds', JSON.stringify(deletedIds));
    }

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const pacienteId = document.getElementById('paciente_id').value;

        if (!pacienteId) {
            responseDiv.innerHTML = '<div class="alert alert-warning">Por favor, ingrese el ID del paciente.</div>';
            responseDiv.style.display = 'block';
            return;
        }

        // Verificar si el ID ya fue eliminado
        if (checkIfDeleted(pacienteId)) {
            responseDiv.innerHTML = '<div class="alert alert-warning">Este paciente ya fue eliminado anteriormente.</div>';
            responseDiv.style.display = 'block';
            return;
        }

        const url = `/pacientes/${pacienteId}`;
        const settings = {
            method: 'DELETE'
        };

        fetch(url, settings)
            .then(response => {
                if (response.ok) {
                    markAsDeleted(pacienteId); // Marcar ID como eliminado
                    responseDiv.innerHTML = '<div class="alert alert-success">Paciente eliminado con éxito.</div>';
                } else {
                    responseDiv.innerHTML = '<div class="alert alert-danger">Error al eliminar el paciente. Verifique el ID.</div>';
                }
                responseDiv.style.display = 'block';
                form.reset();
            })
            .catch(error => {
                console.error('Error:', error);
                responseDiv.innerHTML = '<div class="alert alert-danger">Error al realizar la solicitud. Intente nuevamente.</div>';
                responseDiv.style.display = 'block';
            });
    });
});