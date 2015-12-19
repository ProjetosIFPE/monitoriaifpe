window.onload = function init() {
	var notificacoesDeSucesso = document.getElementsByClassName("my-notify-success");
	var notificacoesDeErro = document.getElementsByClassName("my-notify-error");
	notificacoesDeSucesso[0].addEventListener("click", function() {
		notificacoes[0].style.display = "none";
	}, false);
	notificacoesDeErro.addEventListener("click", function() {
		notificacoes[0].style.display = "none";
	}, false);
}
