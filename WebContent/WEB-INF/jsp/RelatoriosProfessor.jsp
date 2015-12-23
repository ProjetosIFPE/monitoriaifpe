<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery-ui-1.11.4/external/jquery/jquery.js"></script>
<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="js/jquery-ui-1.11.4/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="css/relatorios-professor.css" />


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>${initParam.title}</title>

</head>
<body>
	<jsp:include page="CabecalhoProfessorLogado.jsp" />
	<form action="aprovarRelatorio.do" method="post">
		<table class="table-fill">
			<thead>
				<tr>
					<th colspan="3">Relatorios Mensais</th>
				</tr>
			</thead>
			<tbody class="table-hover">
				<c:choose>
					<c:when
						test="${sessionScope['monitoria'].periodo.semestre == 'PRIMEIRO' }">

						<tr>
							<td class="text-left"><a href="cadastroRelatorio.do?mes=1">Janeiro</a></td>
							<td class="text-center"><a href="gerarDocumento.do?mes=1">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][0]}"><c:if
									test="${ requestScope['situacaoRelatorios'][0] == 'ESPERA' }">
									<button type="submit" value="1" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
						<tr>
							<td class="text-left"><a href="cadastroRelatorio.do?mes=2">Fevereiro</a></td>
							<td class="text-center"><a href="gerarDocumento.do?mes=2">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][1]}"><c:if
									test="${ requestScope['situacaoRelatorios'][1] == 'ESPERA' }">
									<button type="submit" value="2" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
						<tr>
							<td class="text-left"><a href="cadastroRelatorio.do?mes=3">Março</a></td>
							<td class="text-center"><a href="gerarDocumento.do?mes=3">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][2]}"><c:if
									test="${ requestScope['situacaoRelatorios'][2] == 'ESPERA' }">
									<button type="submit" value="3" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
						<tr>
							<td class="text-left"><a href="cadastroRelatorio.do?mes=4">Abril</a></td>
							<td class="text-center"><a href="gerarDocumento.do?mes=4">&nbsp; </a></td>
							<td class="${requestScope['situacaoRelatorios'][3]}"><c:if
									test="${ requestScope['situacaoRelatorios'][3] == 'ESPERA' }">
									<button type="submit" value="4" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
						<tr>
							<td class="text-left"><a href="cadastroRelatorio.do?mes=5">Maio</a></td>
							<td class="text-center"><a href="gerarDocumento.do?mes=5">&nbsp; </a></td>
							<td class="${requestScope['situacaoRelatorios'][4]}"><c:if
									test="${ requestScope['situacaoRelatorios'][4] == 'ESPERA' }">
									<button type="submit" value="5" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
						<tr>
							<td class="text-left"><a href="cadastroRelatorio.do?mes=6">Junho</a></td>
							<td class="text-center"><a href="gerarDocumento.do?mes=6">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][5]}"><c:if
									test="${ requestScope['situacaoRelatorios'][5] == 'ESPERA' }">
									<button type="submit" value="6" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="text-left"><a>Julho</a></td>
							<td class="text-center"><a href="gerarDocumento.do?mes=7">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][0]}"><c:if
									test="${ requestScope['situacaoRelatorios'][0] == 'ESPERA' }">
									<button type="submit" value="7" name="mes">Aprovar
										relatório</button>
								</c:if></td>

						</tr>
						<tr>
							<td class="text-left"><a>Agosto</a></td>
							<td><a href="gerarDocumento.do?mes=8">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][1]}"><c:if
									test="${ requestScope['situacaoRelatorios'][1] == 'ESPERA' }">
									<button type="submit" value="8" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
						<tr>
							<td class="text-left"><a>Setembro</a></td>
							<td><a href="gerarDocumento.do?mes=9">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][2]}"><c:if
									test="${ requestScope['situacaoRelatorios'][2] == 'ESPERA' }">
									<button type="submit" value="9" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
						<tr>
							<td class="text-left"><a>Outubro</a></td>
							<td><a href="gerarDocumento.do?mes=10">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][3]}"><c:if
									test="${ requestScope['situacaoRelatorios'][3] == 'ESPERA' }">
									<button type="submit" value="10" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
						<tr>
							<td class="text-left"><a>Novembro</a></td>
							<td><a href="gerarDocumento.do?mes=11">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][4]}"><c:if
									test="${ requestScope['situacaoRelatorios'][4] == 'ESPERA' }">
									<button type="submit" value="11" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
						<tr>
							<td class="text-left"><a>Dezembro</a></td>
							<td><a href="gerarDocumento.do?mes=12">&nbsp;</a></td>
							<td class="${requestScope['situacaoRelatorios'][5]}"><c:if
									test="${ requestScope['situacaoRelatorios'][5] == 'ESPERA' }">
									<button type="submit" value="12" name="mes">Aprovar
										relatório</button>
								</c:if></td>
						</tr>
					</c:otherwise>
				</c:choose>

			</tbody>
		</table>
	</form>
	<jsp:include page="RodapeUsuarioLogado.jsp" />
</body>
</html>