<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/formulario.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>${initParam.title}</title>
</head>
<body>
	<jsp:include page="CabecalhoUsuarioLogado.jsp" />


	<form action="alterarSenha.do" method="post">
		<fieldset id="fieldset1">

			<fieldset id="fieldset2">

				<label for="senhaAntiga"></label> Senha Antiga <input
					type="password" id="senhaAntiga" required="required" name="senhaAntiga" /> <label
					for="senhaNova"></label> Senha Nova <input required="required" type="password"
					id="senhaNova" name="senhaNova" />
			</fieldset>
			<fieldset id="fieldset3">
				<button type="submit">Alterar Senha</button>
			</fieldset>


		</fieldset>
	</form>
	<jsp:include page="RodapeUsuarioLogado.jsp" />
</body>
</html>