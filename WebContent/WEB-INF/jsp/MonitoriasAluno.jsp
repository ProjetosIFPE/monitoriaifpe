<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/tabela.css" />
<link rel="stylesheet" type="text/css" href="css/tabela_botoes.css" />
<link rel="stylesheet" type="text/css" href="css/sweetalert.css" />
<script src="js/sweetalert.min.js"></script>
<script src="js/remover_monitoria.js"></script>
<title>${initParam.title}</title>

</head>
<body>
	<jsp:include page="CabecalhoUsuarioLogado.jsp" />

	<table>
		<tr>
			<th>Disciplina</th>
			<th>Período</th>
			<th>Modalidade</th>
			<th><a href="cadastroMonitoria.do">&nbsp;</a></th>
		</tr>
		<c:forEach var="monitoria" items="${requestScope['listaMonitorias']}">
			<tr class="msc-action">
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
				<td>
					<form id="formRemover" class="msc-action" method="post" action="removeMonitoria.do">
						<input name="chaveMonitor" value="${monitoria.chavePrimaria}"></input>
						<input type="image" src="css/images/delete_16.png"
							id="botaoRemoverMonitoria"></input>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="RodapeUsuarioLogado.jsp" />
</body>
</html>