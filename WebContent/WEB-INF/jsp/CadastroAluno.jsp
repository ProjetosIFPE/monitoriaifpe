<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="js/jquery.js"></script>
<link rel="stylesheet" href="css/cadastro-aluno.css" type="text/css" />
<script type="text/javascript" src="js/cadastro-aluno.js"></script>
<script type="text/javascript" src="js/validar-senha.js"></script>

<title>${initParam.title}</title>
<style>
.true {
	border: 1px solid red;
}
</style>
</head>
<body>
	<jsp:include page="Cabecalho.jsp" />
	
	<form id="form1" action="cadastroAluno.do" method="post">
		<fieldset>
			<label>Login</label> 
			<input type="text" name="login" value="${requestScope.aluno.login }" class="<c:out value="${requestScope.CAMPOS_INVALIDOS['login']  }" />" required="required" /> 
			<label id="nome">Nome</label> 
			<input type="text" id="nome" name="nome" value="${requestScope.aluno.nome }" required="required" />
			<label id="sobrenome">Sobrenome</label> 
			<input type="text" id="sobrenome" name="sobrenome" value="${requestScope.aluno.sobrenome }" required="required" /> 
			<label id="matricula">Matricula</label>
			<input type="text" id="matricula" name="matricula" value="${requestScope.aluno.matricula }" class="${ requestScope.CAMPOS_INVALIDOS[0] }" required="required" /> 
			<label id="email">E-mail</label> 
			<input type="email" name="email" id="email" value="${requestScope.aluno.email }"  class="${ requestScope.CAMPOS_INVALIDOS[1] }" required="required" /> 
			<label>Disciplinas candidatas</label>
			<div class="selecaoDisciplina">

				<select multiple id="disciplinas">

					<c:forEach var="disciplina"
						items="${requestScope['listaDisciplinas']}">
						<option><c:out value="${disciplina.descricao }" /></option>
					</c:forEach>

				</select> <a href="#" id="add">add</a>

			</div>

			<div class="selecaoDisciplina">

				<select multiple id="disciplinas_selecionadas" name="disciplinas"></select>
				<a href="#" id="remove"> remove</a>

			</div>

			<label id="senha">Senha</label> 
			<input type="password" name="senha" required="required" />
			<label id="confirmasenha">Confirme sua senha</label> 
			<input type="password" name="confirmasenha" />
			<button type="submit" onclick="return validar()">Cadastrar</button>
		</fieldset>
	</form>

	<jsp:include page="Rodape.jsp" />
</body>
</html>