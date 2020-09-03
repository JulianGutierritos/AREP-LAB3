var api = (function () {
	var filas = 0;

	function borrarFilas() {
		while (filas > 0){
			$("#fila" + filas).remove();
			filas += -1;
		}
	}

	function agregarUsuario(usuario){
		filas+= 1;
		$("#Tabla").append("<tr id=\"fila" + filas + "\">" +
							"<td>" + usuario.Nombre + "</td>" +
							"<td>" + usuario.Apellido + "</td>"+
							"<td>" + usuario.Documento + "</td>"+
							"<td>" + usuario.Telefono + "</td>" +
							"</tr>");
		
	}

	function agregarUsuarios(lista){
		for (var i = 0; i < lista.length; i++){
			agregarUsuario(lista[i].Usuario);
		}
	}

	return {
		getUsuario: function (callback) {
			borrarFilas();
			var n = $("#documento").val();
			$.ajax({
				url: 'App/getUsuario?id=' + n ,
				type: 'get',
				success: function(respuesta) {
					agregarUsuario(respuesta.Usuario);
				},
				error: function(respuesta) {
					alert("Usuario no registrado");
				}
			});
		},

		getUsuarios: function (callback) {
			borrarFilas();
			$.ajax({
				url: 'App/getUsuarios' ,
				type: 'get',
				success: function(respuesta) {
					console.log("Exitosa");
					console.log(respuesta);
					agregarUsuarios(respuesta.Lista);
				},
				error: function(respuesta) {
					alert("No hay usuarios registrados");
				}
			});
		}
	};
})();
