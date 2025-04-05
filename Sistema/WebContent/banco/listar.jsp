<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Instituição - Sistema Bancário</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloListagem.css">
</head>
<body>
	<fieldset>
		<jsp:include page="/templete/cabecalho.jsp" /><br>
		<center><h2>Listagem de Bancos</h2></center>
		<p />
		<font color="#ff0000">${erro}</font><font color="#00ff00">${mensagem}</font>
		<p />
		<form method="post"
			action="${pageContext.request.contextPath}/bancocrud">
			<input type="hidden" name="acao" value="filtrar" /> <label>Consultar
				banco:</label> <input type="text" name="nomeFiltro"
				value="${param.nomeFiltro}" size="50" maxlength="50" /> <input
				type="submit" value="Buscar" />
		</form>
		<p />
		<table border="2">
			<tr>
			    <th width="10" align="center">Numero</th>
				<th width="300" align="center">Nome da Instituição Financeira</th>
				<th width="160" align="center">Alterar/Excluir</th>
			</tr>
			<c:forEach var="banco" items="${bancos}">
				<tr>
				    <td>${banco.idBanco}</td>
					<td>${banco.banco}</td>
					<td align="center">
					<a href="${pageContext.request.contextPath}/bancocrud?acao=alterar&idBanco=${banco.idBanco}"><img
							src="${pageContext.request.contextPath}/img/alterar.png"
							border="0" width="25" alt="Alterar Banco"></a>
							
							<a href="${pageContext.request.contextPath}/bancocrud?acao=excluir&idBanco=${banco.idBanco}"
						onClick="if(!confirm('Deseja realmente excluir esse Banco?'))return false;"><img
							src="${pageContext.request.contextPath}/img/excluir.png"
							border="0" width="25" alt="Excluir Banco"></a></td>
				</tr>
			</c:forEach>
		</table>
		<p />
		<a
			href="${pageContext.request.contextPath}/bancocrud?acao=incluir&idBanco=${banco.idBanco}"><input
			type="submit" value="Cadastrar Banco" /></a>&nbsp; <a
			href="${pageContext.request.contextPath}/menu.jsp"><input
			type="submit" value="Voltar" /></a>
		<p />
	</fieldset>
</body>
</html>