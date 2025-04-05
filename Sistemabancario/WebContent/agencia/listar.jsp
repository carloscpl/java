<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agência - Sistema Bancário</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloListagem.css">
</head>
<body>
<fieldset>
<jsp:include page="/templete/cabecalho.jsp"/><br>
<center><h2>Lista de Agências</h2></center>
<p/>
<font color="#ff0000">${erro}</font><font color="#00ff00">${mensagem}</font>
<p/>
<form method="post" action="${pageContext.request.contextPath}/agenciacrud">
		<input type="hidden" name="acao" value="filtrar" />
			<label>Consultar agência:</label> <input type="text" name="nomeFiltro" value="${param.nomeFiltro}" size="50" maxlength="50"/>
			<input type="submit" value="Buscar"/>
	</form>
<p/>
<table border="2">
<tr>
<th width="100" align="center">Num.Agência</th>
<th width="400" align="center">Banco</th>
<th width="300" align="center">Nome Agência</th>
<th width="120" align="center">Alterar/Excluir</th>
</tr>
<c:forEach var="agencia" items="${agencias}">
	<tr>
	<td>${agencia.idAgencia}</td>
	<td>${agencia.banco.banco}</td>
	<td>${agencia.nomeAgencia}</td>
	<td align="center">
	<a href="${pageContext.request.contextPath}/agenciacrud?acao=alterar&idAgencia=${agencia.idAgencia}"><img src="${pageContext.request.contextPath}/img/alterar.png" border="0" width="25" alt="Alterar Agência"></a>
	<a href="${pageContext.request.contextPath}/agenciacrud?acao=excluir&idAgencia=${agencia.idAgencia}" onClick="if(!confirm('Deseja realmente excluir essa Agência?'))return false;"><img src="${pageContext.request.contextPath}/img/excluir.png" border="0" width="25" alt="Excluir Agência"></a>
	</td>
	</tr>
</c:forEach>
</table>
<p/>
<a href="${pageContext.request.contextPath}/agenciacrud?acao=incluir&idAgencia=${agencia.idAgencia}"><input type="submit" value="Cadastrar Agencia"/></a>&nbsp;<p/>
<a href="${pageContext.request.contextPath}/menu.jsp"><input type="submit" value="Voltar" /></a>
<p/>
</fieldset>
</body>
</html>