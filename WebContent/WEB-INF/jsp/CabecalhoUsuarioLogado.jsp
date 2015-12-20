<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/alertas.css" />
<script src="js/alertas.js"></script>
<link rel="stylesheet" type="text/css" href="css/cabecalho.css" />
</head>
<body>
	<c:choose>
		<c:when test="${not empty requestScope.MENSAGEM_ERRO }">
			<div class="my-notify-error">${ requestScope.MENSAGEM_ERRO }</div>
		</c:when>
		<c:when test="${not empty requestScope.MENSAGEM_SUCESSO }">
			<div class="my-notify-success">${requestScope.MENSAGEM_SUCESSO }
			</div>
		</c:when>
	</c:choose>
	<!--Header Begin-->
	<div id="header">
		<div class="center">
			<div id="logo">
				<a href="acesso.do">${initParam.title}</a>
			</div>
			<!--Menu Begin-->
			<div id="menu">
				<ul>
					<li><a class="active" href="acesso.do"><span>Home</a></li>
					<li><a class="active" href="adicionaDisciplina.do"><span>Disciplina</span></a></li>
					<li><a class="active" href="alterarSenha.do"><span>Alterar senha</span></a></li>
					<li><a class="active" href="logout.do"><span>Sair</span></a></li>
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