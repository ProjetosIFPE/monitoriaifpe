<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/tabela.css" />
<link rel="stylesheet" type="text/css" href="css/tabela_botoes.css" />
<link rel="stylesheet" type="text/css" href="css/sweetalert.css" />
<script src="js/sweetalert.min.js"></script>
<script src="js/remover_disciplina.js" charset="UTF-8"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>${initParam.title}</title>

</head>
<body>
	<jsp:include page="CabecalhoProfessorLogado.jsp" />
	<table>
		<tr>
			<th>Disciplina</th>
			<th><a href="cadastroDisciplina.do">&nbsp;</a></th>
		</tr>
		<c:forEach var="disciplina" items="${requestScope['disciplinasProfessor']}">
			<tr>
				<td><a
					href="disciplina.do?chaveDisciplina=${disciplina.chavePrimaria}"> <c:out
							value="${disciplina.descricao }" />
				</a></td>
				<td>
					<form id="formRemover" method="post" action="removeDisciplinaProfessor.do">
						<input name="chaveDisciplina" value="${disciplina.chavePrimaria}"></input>
						<input id="botaoRemoverDisciplina" type="image" src="css/images/delete_16.png" id="botaoRemover"></input>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="RodapeUsuarioLogado.jsp" />
</body>
</html>