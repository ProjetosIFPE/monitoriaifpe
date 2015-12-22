<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/tabela.css" />
<link rel="stylesheet" type="text/css" href="css/tabela_botoes.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/msc-style.css" />
<script src="js/msc-script.js"></script>

<title>${initParam.title}</title>
<script type="text/javascript">
var demobtn2 = document.querySelector("#botaoRemover");
demobtn2.addEventListener("click", function(e) {
  mscConfirm("Delete", "Are you sure you want to delete this post?", function(){
    alert("Post deleted");
  },
  function() {
    alert('Cancelled');
    e.preventDefault();
  });
});
</script>

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
				<td><a id="botaoRemover"
					href="removeMonitoria.do?chaveMonitor=${monitoria.chavePrimaria}">&nbsp;</a></td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="RodapeUsuarioLogado.jsp" />
</body>
</html>