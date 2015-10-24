<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="js/jquery-ui-1.11.4/jquery-ui.css">
<link rel="stylesheet" href="css/cadastro-relatorio.css">
<script src="js/jquery-ui-1.11.4/external/jquery/jquery.js"></script>
<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<script type="text/javascript">
	$(function() {
		$(".datepicker").datepicker(
				{
					dateFormat : 'dd/mm/yy',
					dayNames : [ 'Domingo', 'Segunda', 'Ter�a', 'Quarta',
							'Quinta', 'Sexta', 'S�bado' ],
					dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D' ],
					dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
							'S�b', 'Dom' ],
					monthNames : [ 'Janeiro', 'Fevereiro', 'Mar�o', 'Abril',
							'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro',
							'Outubro', 'Novembro', 'Dezembro' ],
					monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai',
							'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
					nextText : 'Pr�ximo',
					prevText : 'Anterior'
				});
	});
	$(function() {
		$("#accordion").accordion();
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<form action="cadastroRelatorio.do" method="post">
		<div id="accordion">
			<h3>Primeira semana</h3>
			<div>

				<textarea rows="13" cols="30" name="descricaosemana1">
					<c:out value="${ sessionScope['relatorio'].semanas[0].descricao}" />
				</textarea>

				<label>Primeiro dia</label> <input type="text" class="datepicker"
					name="semana1atividade1"
					value="<fmt:formatDate pattern="dd/MM/yyyy" value="${sessionScope['relatorio'].semanas[0].atividades[0].data}" />" />

				<label>Segundo dia</label> <input type="text" class="datepicker"
					name="semana1atividade2"
					value="${ sessionScope['relatorio'].semanas[0].atividades[1].data }" />

				<label>Terceiro dia</label> <input type="text" class="datepicker"
					name="semana1atividade3"
					value="${ sessionScope['relatorio'].semanas[0].atividades[2].data }" />

				<label>Quarto dia</label> <input type="text" class="datepicker"
					name="semana1atividade4"
					value="${ sessionScope['relatorio'].semanas[0].atividades[3].data }" />
				<label>Quinto dia</label> <input type="text" class="datepicker"
					name="semana1atividade5"
					value="${ sessionScope['relatorio'].semanas[0].atividades[4].data }" />

			</div>
			<h3>Segunda semana</h3>
			<div>

				<textarea rows="13" cols="30" name="descricaosemana2">
					<c:out value="${ sessionScope['relatorio'].semanas[1].descricao}" />
				</textarea>

				<label>Primeiro dia</label> <input type="text" class="datepicker"
					name="semana2atividade1"
					value="${ sessionScope['relatorio'].semanas[1].atividades[0].data }" />

				<label>Segundo dia</label> <input type="text" class="datepicker"
					name="semana2atividade2"
					value="${ sessionScope['relatorio'].semanas[1].atividades[1].data }" />

				<label>Terceiro dia</label> <input type="text" class="datepicker"
					name="semana2atividade3"
					value="${ sessionScope['relatorio'].semanas[1].atividades[2].data }" />

				<label>Quarto dia</label> <input type="text" class="datepicker"
					name="semana2atividade4"
					value="${ sessionScope['relatorio'].semanas[1].atividades[3].data }" />
				<label>Quinto dia</label> <input type="text" class="datepicker"
					name="semana2atividade5"
					value="${ sessionScope['relatorio'].semanas[1].atividades[4].data }" />

			</div>

			<h3>Terceira semana</h3>
			<div>

				<textarea rows="13" cols="30" name="descricaosemana3">
					<c:out value="${ sessionScope['relatorio'].semanas[2].descricao}" />
				</textarea>

				<label>Primeiro dia</label> <input type="text" class="datepicker"
					name="semana3atividade1"
					value="${ sessionScope['relatorio'].semanas[2].atividades[0].data }" />

				<label>Segundo dia</label> <input type="text" class="datepicker"
					name="semana3atividade2"
					value="${ sessionScope['relatorio'].semanas[2].atividades[1].data }" />

				<label>Terceiro dia</label> <input type="text" class="datepicker"
					name="semana3atividade3"
					value="${ sessionScope['relatorio'].semanas[2].atividades[2].data }" />

				<label>Quarto dia</label> <input type="text" class="datepicker"
					name="semana3atividade4"
					value="${ sessionScope['relatorio'].semanas[2].atividades[3].data }" />
				<label>Quinto dia</label> <input type="text" class="datepicker"
					name="semana3atividade5"
					value="${ sessionScope['relatorio'].semanas[2].atividades[4].data }" />

			</div>


			<h3>Quarta semana</h3>
			<div>

				<textarea rows="13" cols="30" name="descricaosemana4">
					<c:out value="${ sessionScope['relatorio'].semanas[3].descricao}" />
				</textarea>

				<label>Primeiro dia</label> <input type="text" class="datepicker"
					name="semana4atividade1"
					value="${ sessionScope['relatorio'].semanas[3].atividades[0].data }" />

				<label>Segundo dia</label> <input type="text" class="datepicker"
					name="semana4atividade2"
					value="${ sessionScope['relatorio'].semanas[3].atividades[1].data }" />

				<label>Terceiro dia</label> <input type="text" class="datepicker"
					name="semana4atividade3"
					value="${ sessionScope['relatorio'].semanas[3].atividades[2].data }" />

				<label>Quarto dia</label> <input type="text" class="datepicker"
					name="semana4atividade4"
					value="${ sessionScope['relatorio'].semanas[3].atividades[3].data }" />
				<label>Quinto dia</label> <input type="text" class="datepicker"
					name="semana4atividade5"
					value="${ sessionScope['relatorio'].semanas[3].atividades[4].data }" />

			</div>
			<h3>Quinta semana</h3>
			<div>

				<textarea rows="13" cols="30" name="descricaosemana5">
					<c:out value="${ sessionScope['relatorio'].semanas[4].descricao}" />
				</textarea>

				<label>Primeiro dia</label> <input type="text" class="datepicker"
					name="semana5atividade1"
					value="${ sessionScope['relatorio'].semanas[4].atividades[0].data }" />

				<label>Segundo dia</label> <input type="text" class="datepicker"
					name="semana5atividade2"
					value="${ sessionScope['relatorio'].semanas[4].atividades[1].data }" />

				<label>Terceiro dia</label> <input type="text" class="datepicker"
					name="semana5atividade3"
					value="${ sessionScope['relatorio'].semanas[4].atividades[2].data }" />

				<label>Quarto dia</label> <input type="text" class="datepicker"
					name="semana5atividade4"
					value="${ sessionScope['relatorio'].semanas[4].atividades[3].data }" />
				<label>Quinto dia</label> <input type="text" class="datepicker"
					name="semana5atividade5"
					value="${ sessionScope['relatorio'].semanas[4].atividades[4].data }" />

			</div>
		</div>
		<input type="submit" value="Save" />
	</form>


</body>
</html>