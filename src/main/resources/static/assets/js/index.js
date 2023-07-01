$(document).ready( () => {

    //Listado de alumnos
    const list = () => {
        $.ajax({
            url: 'http://localhost:8080/directorio/list',
            type: 'GET',
            dataType: 'json',
            success: function(res){
                let data = '';
                res.forEach(element => {
                    data+= `
                        <tr personaId = ${element.id} >
                            <td>${element.id}</td>
                            <td>${element.nombre}</td>
                            <td>${element.apellidoPaterno}</td>
                            <td>${element.apellidoMaterno}</td>
                            <td>${element.identificacion}</td>

                            <td>
                                <button id="btn-delete" class="btn btn-danger">Eliminar</button>
                            </td>

                        </tr>
                    `
                });
                $('#tbody').html(data);

            }
        })

    }

    //Guardar Persona
const save = () => {
    $('#agregar').on('click', function(){
        const nombre = $('#nombre').val();
        const apellidoPaterno = $('#apellidoPaterno').val();
        const identificacion = $('#identificacion').val();

        if (nombre && apellidoPaterno && identificacion) {
            const datosPersona = {
                nombre: nombre,
                apellidoPaterno: apellidoPaterno,
                apellidoMaterno: $('#apellidoMaterno').val(),
                identificacion: identificacion
            }

            $.ajax({
                url: 'http://localhost:8080/directorio/save',
                contentType: 'application/json',
                type: 'POST',
                data: JSON.stringify(datosPersona),
                dataType: 'json',
                success: (data) => {
                    $('#messages').html('Persona creada').css('display', 'block');
                    list();
                    reset();
                    console.log('Persona registrada!');
                }
            });
        } else {
            // Mostrar un mensaje de error o realizar alguna acción cuando los campos requeridos no están completos
            alert('Por favor, complete todos los campos requeridos.Nota: Solo apellido Materno es opcional');
        }
    });
}


//Rellenar los datos de la persona en el formualario
const rellenarAlumno = () => {
    $(document).on('click', '#btn-edit', function(){
        let btnEdit= $(this)[0].parentElement.parentElement;
        let identificacion = $(btnEdit).attr('identificacion');

       $('#agregar').hide();
       $('#editar').show();

       $.ajax({
           url:'http://localhost:8080/directorio/persona/' + identificacion,
           type:  'GET',
           dataType: 'json',
           success:  (res) => {
               $('#id').val(res.id);
               $('#nombre').val(res.nombre);
               $('#apellidoPaterno').val(res.apellidoPaterno);
               $('#apellidoMaterno').val(res.apellidoMaterno);
               $('#identificacion').val(res.identificacion);

           }
       })


    })

}


//Método para limpiar el formulario
const reset = () => {
    $('#nombre').val('');
    $('#apellidoPaterno').val('');
    $('#apellidoMaterno').val('');
    $('#identificacion').val('');
}


//LLamadas a funciones
list();
save();
rellenarAlumno();
})