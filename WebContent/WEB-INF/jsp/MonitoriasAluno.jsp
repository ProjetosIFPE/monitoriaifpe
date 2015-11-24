<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/tabela.css" />
<link rel="stylesheet" type="text/css" href="css/tabela_botoes.css" />
<title>${initParam.title}</title>


</head>
<body>
	<jsp:include page="CabecalhoUsuarioLogado.jsp" />

	<table>
		<tr>
			<th>Disciplina</th>
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
				<td><a
					href="relatorio.do?chaveMonitor=${monitoria.chavePrimaria}"> <c:out
							value=" ${monitoria.periodo }" /></a></td>
				<td><a
					href="relatorio.do?chaveMonitor=${monitoria.chavePrimaria}"> <c:out
							value=" ${monitoria.modalidade }" /></a></td>
				<td><a
					href="removeMonitoria.do?chaveMonitor=${monitoria.chavePrimaria}">&nbsp;</a></td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="Rodape.jsp" />
</body>
</html>