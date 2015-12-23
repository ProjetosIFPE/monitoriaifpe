<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/formulario.css" />
<link rel="stylesheet" type="text/css" href="css/sweetalert.css" />
<script type="text/javascript" src="js/valida_monitoria.js" charset="UTF-8"></script>
<script src="js/sweetalert.min.js"></script>
<title>${initParam.title}</title>

</head>
<body>
	<jsp:include page="CabecalhoUsuarioLogado.jsp" />
	<form action="cadastroMonitoria.do" method="post">
		<fieldset>
			<label>Disciplinas</label> <select required="required" name="disciplina">
				<c:forEach var="disciplina"
					items="${requestScope['listaDisciplinas']}">
					<option><c:out value="${disciplina.descricao }" /></option>
				</c:forEach>
			</select> 
			<label>Horário de entrada</label> <input required="required" type="time" name="entrada" placeholder="00:00" />
			<label>Horário de saída</label> <input required="required" type="time" name="saida" placeholder="00:00" />
			<label>Bolsista</label> <input checked="checked" type="radio" name="modalidade"
				id="bolsista" value="BOLSISTA" /> <label>Voluntário</label> <input
				type="radio" name="modalidade" id="voluntario" value="VOLUNTARIO" />
			<button type="submit">Realizar cadastro</button>
		</fieldset>
	</form>


	<jsp:include page="RodapeUsuarioLogado.jsp" />
</body>
</html>