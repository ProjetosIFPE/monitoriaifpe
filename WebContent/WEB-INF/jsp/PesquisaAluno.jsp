<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/formulario.css">
<title>${initParam.title}</title>
</head>
<body>
	<jsp:include page="CabecalhoProfessorLogado.jsp" />
	<form action="buscarAluno.do" method="post">
		<fieldset id="fieldset1">
			<label>Matricula:</label> <input type="text" name="matricula" />
		</fieldset>
		<fieldset id="fieldset2">
			<button type="submit">Buscar</button>
		</fieldset>
	</form>
	<jsp:include page="RodapeUsuarioLogado.jsp" />
</body>
</html>