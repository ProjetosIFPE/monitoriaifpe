window.onload = function init() {
	var notificacoesDeSucesso = document
			.getElementsByClassName("my-notify-success");
	var notificacoesDeErro = document.getElementsByClassName("my-notify-error");
	if (notificacoesDeSucesso[0] != undefined) {
		notificacoesDeSucesso[0].addEventListener("click", function() {
			notificacoesDeSucesso[0].style.display = "none";
		}, false);
	}
	if (notificacoesDeErro[0] != undefined) {
		notificacoesDeErro[0].addEventListener("click", function() {
			notificacoesDeErro[0].style.display = "none";
		}, false);
	}

}
