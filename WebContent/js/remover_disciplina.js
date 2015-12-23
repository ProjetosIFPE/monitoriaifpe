window.addEventListener("load", function() {
		Array.prototype.slice.call(document.querySelectorAll('.botaoRemoverDisciplina')).forEach( function(botao) {
		botao.addEventListener("click",  function(e) {
			e.preventDefault();
			swal({
				title : "Remover disciplina",
				text : "Você perderá o vínculo com esta disciplina, não podendo acessar as monitorias desta. Você tem certeza?",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Sim",
				cancelButtonText : "Não",
				closeOnConfirm : false,
				closeOnCancel : true
			}, function(isConfirm) {
				if (isConfirm) {
					botao.parentNode.submit();
					swal("Monitoria removida!");
				}
			})}, false);
	});
}, false);
