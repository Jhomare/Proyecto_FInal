window.addEventListener('load', function () {
    const form = document.getElementById('delete_odontologo_form');
    const responseDiv = document.getElementById('response');

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const odontologoId = document.getElementById('odontologo_id').value;

        if (!odontologoId) {
            responseDiv.innerHTML = '<div class="alert alert-warning">Por favor, ingrese el ID del odontólogo.</div>';
            responseDiv.style.display = 'block';
            return;
        }

        const url = `/odontologos/${odontologoId}`;
        const settings = {
            method: 'DELETE'
        };

        fetch(url, settings)
            .then(response => {
                if (response.ok) {
                    responseDiv.innerHTML = '<div class="alert alert-success">Odontólogo eliminado con éxito.</div>';
                } else {
                    responseDiv.innerHTML = '<div class="alert alert-danger">Error al eliminar el odontólogo. Verifique el ID.</div>';
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