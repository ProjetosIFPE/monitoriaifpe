<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/monitorias.css" />
<title>Monitoria IFPE-TADS</title>
<script>
	
</script>
</head>
<body>
	<jsp:include page="cabecalhoUsuarioLogado.jsp" />

	<table>
		
		<tr>
			<th>Descri��o</th>
			<th>Per�odo</th>
			<th>Modalidade</th>
			<th><a href="cadastroMonitoria.do">&nbsp;</a></th>
		</tr>
		<c:forEach var="monitoria" items="${requestScope['listaMonitorias']}">
			<tr>
				<td><a
					href="relatorio.do?chaveMonitor=${monitoria.chavePrimaria}"> <c:out
							value="${monitoria.disciplina.descricao }" />
				</a></td>
				<td><c:out value=" ${monitoria.periodo }" /></td>
				<td><c:out value=" ${monitoria.modalidade }" /></td>
				<td><a href="removeMonitoria.do?chaveMonitor=${monitoria.chavePrimaria}">&nbsp;</a></td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="rodape.jsp" />
</body>
</html>