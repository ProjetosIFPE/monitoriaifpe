<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/formulario.css" />
<title>${initParam.title}</title>
</head>
<body>
	<jsp:include page="CabecalhoUsuarioLogado.jsp" />
	<form action="cadastroMonitoria.do" method="post">
		<fieldset>
			<label>Disciplinas</label> <select name="disciplina">
				<c:forEach var="disciplina"
					items="${requestScope['listaDisciplinas']}">
					<option><c:out value="${disciplina.descricao }" /></option>
				</c:forEach>
			</select> 
			<label>Hor�rio de entrada</label> <input type="time" name="entrada"/>
			<label>Hor�rio de sa�da</label> <input type="time" name="saida"/>
			<label>Bolsista</label> <input type="radio" name="modalidade"
				id="bolsista" value="BOLSISTA" /> <label>Volunt�rio</label> <input
				type="radio" name="modalidade" id="voluntario" value="VOLUNTARIO" />
			<button type="submit">Realizar cadastro</button>
		</fieldset>
	</form>


	<jsp:include page="RodapeUsuarioLogado.jsp" />
</body>
</html>