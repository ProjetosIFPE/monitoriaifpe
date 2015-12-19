<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/jquery-ui.css" type="text/css" />
<script src="js/jquery-ui-1.11.4/external/jquery/jquery.js"></script>
<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>
<script src="js/trocar-persistencia.js"></script>
<script src="js/alertas.js"></script>
<link rel="stylesheet" type="text/css" href="css/cabecalho.css" />
<link rel="stylesheet" type="text/css" href="css/toggle-button.css" />
<link rel="stylesheet" type="text/css" href="css/alertas.css" />
<script type="text/javascript">
	t = 1000; // Valor default pra cada titulo
	tr = 300; // tempo de espera pra mudar de titulo (fica com _ durante esse tempo)
	title = {
		tag : [ 'Monitoria IFPE-TADS', 'Equipe:', 'Douglas Albuquerque',
				'Edmilson Santana', 'Laura Regina' ],
		temp : [ 1400, 1400, 1400, 1400 ]
	};
	// title.temp só foi atribuido os três primeiros e o ultimo tempo, assim os outros terão o tempo default
	b = title.tag;
	c = title.temp;
	d = b.length;
	D = document;
	e = -1;
	p = D.title.lastIndexOf('|'); // Separador para que não seja alterado todo o titulo
	if (p > -1)
		p = D.title.substr(0, p + 2);
	else
		p = '';
	function titulo() {
		e++;
		if (e == d) {
			last();
		}
		;
		et(b[e]);
	}
	function t2(b, f) {
		var i = b.substr(0, --f);
		D.title = p + i + '_';
		if (f >= 0)
			setTimeout('t2("' + i + '",' + f + ')', 50);
		else
			setTimeout('titulo()', tr);
	}
	function et(b, i) {
		var l = b.length, f = (i) ? i : 0, i = b.substr(0, f) + '_';
		D.title = p + i;
		f++;
		if (f <= l)
			setTimeout('et("' + b + '",' + f + ')', 50);
		else
			setTimeout('t2("' + b + '","' + l + '")', c[e] || t);
	}
	function last() {
		D.title = "Monitoria IFPE-TADS";
	}
	titulo()
</script>
</head>
<body>
	<c:choose>
		<c:when test="${not empty requestScope.MENSAGEM_ERRO }">
			<div class="my-notify-error">${ requestScope.MENSAGEM_ERRO }</div>
		</c:when>
		<c:when test="${not empty requestScope.MENSAGEM_SUCESSO }">
			<div class="my-notify-warning">${requestScope.MENSAGEM_SUCESSO } </div>
		</c:when>
	</c:choose>
	
	
	<!--Header Begin-->
	<div id="header">
		<div class="center">
			<div id="logo">
				<a href="acesso.do">Monitoria IFPE-TADS</a>
			</div>
			<!--Menu Begin-->
			<div id="menu">
				<ul>
					<li><a class="active" href="acesso.do"><span>Home</span> </a></li>
					<li><a class="active" href="cadastroAluno.do"><span>Aluno</span>
					</a></li>
					<li><a class="active" href="cadastroProfessor.do"><span>Professor</span>
					</a></li>
					<li><a class="active" href="configuracoes.do"><span>Configurações</span></a></li>
				</ul>
			</div>
			<!--Menu END-->
		</div>
	</div>
	<!--Header END-->
	<!--Toprow Begin-->
	<div id="toprow">
		<div class="center">
			<div id="cubershadow">
</body>
</html>