<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/formulario.css" />
<title>${initParam.title}</title>
</head>
<body>
	<jsp:include page="CabecalhoProfessorLogado.jsp" />
	<form action="cadastroDisciplina.do" method="post">
		<fieldset>
			<label>Disciplinas</label> 
			<select name="disciplina" name="disciplina">
				<c:forEach var="disciplina"
					items="${requestScope['listaDisciplinasSemProfessor']}">
					<option><c:out value="${disciplina.descricao }" /></option>
				</c:forEach>
			</select>
			<button type="submit">Cadastrar disciplina</button>
		</fieldset>
	</form>

	<jsp:include page="Rodape.jsp" />
</body>
</html>