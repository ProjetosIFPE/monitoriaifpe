<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/monitorias.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Monitoria IFPE-TADS</title>
</head>
<body>
	<jsp:include page="CabecalhoUsuarioLogado.jsp" />
	<table>
		<tr>
			<th>Disciplina</th>
			<th><a href="cadastroDisciplinaProfessor.do">&nbsp;</a></th>
		</tr>
		<c:forEach var="disciplina" items="${requestScope['disciplinasProfessor']}">
			<tr>
				<td><a
					href=""> <c:out
							value="${disciplina.descricao }" />
				</a></td>
				<td><a
					href="removeDisciplinaProfessor.do?chaveDisciplina=${disciplina.chavePrimaria}">&nbsp;</a></td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="Rodape.jsp" />
</body>
</html>