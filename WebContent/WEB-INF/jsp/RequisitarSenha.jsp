<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="css/formulario.css">
<title>${initParam.title}</title>
</head>
<body>
	<jsp:include page="Cabecalho.jsp" />
	<form action="requisitarSenha.do" method="post">
		<fieldset id="fieldset1">
			<div id="login">
				<label for="login1">Login</label> <input required="required" type="text" name="login"
					id="login1" placeholder="Informe seu login..." size="30" />
			</div>
			<br />
			<fieldset id="fieldset2">
				<button type="submit">Requisitar senha</button>
			</fieldset>

		</fieldset>
	</form>
	<jsp:include page="Rodape.jsp" />

</body>
</html>