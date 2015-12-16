<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>

<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/formulario.css" />
<link rel="stylesheet" type="text/css" href="css/login.css" />
<title>${initParam.title}</title>
<style>
@import
	url('//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css')
	;

.my-notify-info, .my-notify-success, .my-notify-warning,
	.my-notify-error {
	padding: 10px;
	margin: 10px 0;
}

.my-notify-info:before, .my-notify-success:before, .my-notify-warning:before,
	.my-notify-error:before {
	font-family: FontAwesome;
	font-style: normal;
	font-weight: 400;
	speak: none;
	display: inline-block;
	text-decoration: inherit;
	width: 1em;
	margin-right: .2em;
	text-align: center;
	font-variant: normal;
	text-transform: none;
	line-height: 1em;
	margin-left: .2em;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale
}

.my-notify-info:before {
	content: "\f05a";
}

.my-notify-success:before {
	content: '\f00c';
}

.my-notify-warning:before {
	content: '\f071';
}

.my-notify-error:before {
	content: '\f057';
}

.my-notify-info {
	color: #00529B;
	background-color: #BDE5F8;
}

.my-notify-success {
	color: #4F8A10;
	background-color: #DFF2BF;
	position: absolute;
}

.my-notify-warning {
	color: #9F6000;
	background-color: #FEEFB3;
}

.my-notify-error {
	color: #D8000C;
	background-color: #FFBABA;
}
</style>
</head>

<body>
	<c:if test="${not empty requestScope.MENSAGEM_DE_ALERTA}">
		<div class="my-notify-success">${requestScope.MENSAGEM_DE_ALERTA }</div>
	</c:if>
	
	<jsp:include page="Cabecalho.jsp" />

	<form action="efetuarLogon.do" method="post">

		<fieldset id="fieldset1">
			<div id="login">
				<label for="login">Login</label>
				<c:choose>
					<c:when test="${not empty requestScope.ERRO_ACESSO_NEGADO}">
						<input type="text" name="login" id="login"
							value="${requestScope.ERRO_ACESSO_NEGADO }"
							placeholder="Informe seu nome..." size="30" />
					</c:when>
					<c:otherwise>
						<input type="text" name="login" id="login"
							placeholder="Informe seu login..." size="30" />
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<label for="senha">Senha</label> <input type="password" id="senha"
					name="senha" size="30" placeholder="Informe sua senha..." />
			</div>
			<h1>
				<a href="requisitarSenha.do">Esqueceu sua senha ?</a>
			</h1>
			<fieldset id="fieldset2">
				<button type="submit">Login</button>
			</fieldset>
		</fieldset>
	</form>
	<jsp:include page="Rodape.jsp" />
</body>

</html>