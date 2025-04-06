<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Conta - Sistema Bancário</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloListagemContas.css">
</head>
<body>
<fieldset>
<jsp:include page="/templete/cabecalho.jsp"/><br>
<center><h2>Lista de Contas</h2></center>
<p/>
<font color="#ff0000">${erro}</font>
<font color="#00ff00">${mensagem}</font>
<p/>
<form method="post" action="${pageContext.request.contextPath}/contacrud">
		<input type="hidden" name="acao" value="filtrar" />
			<label>Consultar cliente:</label> <input type="text" name="nomeFiltro" value="${param.nomeFiltro}" size="30" maxlength="30"/>
			<input type="submit" value="Buscar"/>
	</form>
<p/>
<table border="2">
<tr>
<th width="400" align="center">Agência</th>
<th width="30" align="center">Operação</th>
<th width="700" align="center">Conta</th>
<th width="700" align="center">Tipo Conta</th>
<th width="700" align="center">Cliente</th>
<th width="120" align="center">Alterar/Excluir</th>
</tr>
<c:forEach var="conta" items="${contas}">
	<tr>
	<td>${conta.agencia.nomeAgencia}</td>
	<td>${conta.operacao}</td>
	<td>${conta.conta}</td>
	<td>${conta.tipoConta}</td>
	<td>${conta.cliente.nome}</td>
	<td align="center">
	<a href="${pageContext.request.contextPath}/contacrud?acao=alterar&idConta=${conta.idConta}"><img src="${pageContext.request.contextPath}/img/alterar.png" border="0" width="25" alt="Alterar Conta"></a>
	<a href="${pageContext.request.contextPath}/contacrud?acao=excluir&idConta=${conta.idConta}" onClick="if(!confirm('Deseja realmente excluir essa Conta?'))return false;"><img src="${pageContext.request.contextPath}/img/excluir.png" border="0" width="25" alt="Excluir Conta"></a>
	</td>
	</tr>
</c:forEach>
</table>
<p/>
<a href="${pageContext.request.contextPath}/contacrud?acao=incluir&idConta=${conta.idConta}"><input type="submit" value="Cadastrar Conta"/></a>&nbsp;<p/>
<a href="${pageContext.request.contextPath}/menu.jsp"><input type="submit" value="Voltar" /></a>
<p/>
</fieldset>
</body>
</html>