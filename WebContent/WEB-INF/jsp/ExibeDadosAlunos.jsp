<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/formulario.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>${initParam.title}</title>
</head>
<body>
	<jsp:include page="CabecalhoProfessorLogado.jsp" />
	<form action="">
		<fieldset>
			<label>Matricula:</label> <input type="text"  value="${requestScope.aluno.matricula }" disabled="disabled" />
			<label>Nome</label> <input type="text"  value="${requestScope.aluno.nome }" disabled="disabled" />
			<label>Sobrenome </label> <input type="text"  value="${requestScope.aluno.sobrenome }" disabled="disabled" />
			<label>Email </label> <input type="email"  value="${requestScope.aluno.email }" disabled="disabled" />
			<label>Disciplinas</label> 
			<select disabled="disabled" multiple="multiple" size="${fn:length(requestScope['monitoriasDoAluno'])}"  >
				<c:forEach var="monitoria"
					items="${requestScope['monitoriasDoAluno']}">
					<option ><c:out value="${monitoria.disciplina.descricao }" /></option>
				</c:forEach>
			</select>
		</fieldset>
	</form>
	<jsp:include page="RodapeUsuarioLogado.jsp" />
	
</body>
</html>