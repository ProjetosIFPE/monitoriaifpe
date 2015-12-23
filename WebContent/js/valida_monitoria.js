window.addEventListener("load", function() {
	function verificaHora() {
		campoHorarioEntrada = document.querySelector("[name='entrada']");
		campoHorarioEntrada.addEventListener("focusout", function() {
			if (!validaHorario(campoHorarioEntrada)) {
				swal("Horário de entrada inválido");
				
			}
			if ( !comparaHorario(campoHorarioEntrada, campoHorarioSaida) ){
				swal("Horário de Monitoria inválido. Horário de saída ocorrendo antes do Horário de entrada.");
			}
		}, false);

		campoHorarioSaida = document.querySelector("[name='saida']");
		campoHorarioSaida.addEventListener("focusout", function() {
			if (!validaHorario(campoHorarioSaida)) {
				swal("Horário de saída inválido");
			}
			if ( !comparaHorario(campoHorarioEntrada, campoHorarioSaida) ){
				swal("Horário de Monitoria inválido. Horário de saída ocorrendo antes do Horário de entrada.");
			}
		}, false);
	}

	function validaHorario(campo) {
		estado = true;
		if (campo.value != "") {
			hrs = (campo.value.substring(0, 2));
			min = (campo.value.substring(3, 5));
			if ((hrs < 00) || (hrs > 23) || (min < 00) || (min > 59)) {
				estado = false;
			}
			if (!estado) {
				campo.focus();
			}
		}
		return estado;
	}
	
	function comparaHorario(campoHorarioEntrada, campoHorarioSaida) {
		horaValida = true;
		if(campoHorarioEntrada.value != "" && campoHorarioSaida.value != "" ) {
			var hrsEntrada = (campoHorarioEntrada.value.substring(0, 2));
			var minEntrada = (campoHorarioEntrada.value.substring(3, 5));
			var hrsSaida = (campoHorarioSaida.value.substring(0, 2));
			var minSaida = (campoHorarioSaida.value.substring(3, 5));
			if ( ( hrsSaida < hrsEntrada) || (hrsSaida == hrsEntrada && minSaida < minEntrada )) {
				horaValida = false;
			}
		}
		return horaValida;
		
	}
	verificaHora();
}, false);
