window.addEventListener("load", function() {
	document.querySelector('#botaoRemoverMonitoria').addEventListener("click",  function(e) {
		e.preventDefault();
		swal({
			title : "Remover monitoria",
			text : "Todos os Relatórios de Frequência desta monitoria serão removidos. Você tem certeza?",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Sim",
			cancelButtonText : "Não",
			closeOnConfirm : false,
			closeOnCancel : true
		}, function(isConfirm) {
			if (isConfirm) {
				document.getElementById("formRemover").submit();
				swal("Disciplina removida!");
			}
		})}, false);
}, false);