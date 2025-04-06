<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloListagem.css">
<title>Usuario - Sistema Bancário</title>
</head>
<fieldset>
<jsp:include page="/templete/cabecalho.jsp"/><br>
<h2>Listagem de Usuarios</h2><br>
${usuario.nome}
<font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font>
<p/>
<form method="post" action="${pageContext.request.contextPath}/usuariocrud"></form>
<p/>
<table border="2">
<tr>
<th width="160" align="center">Nome</th>
<th width="160" align="center">Usuario</th>
<th width="160" align="center">Perfil</th>
<th width="200" align="center">Alterar/Excluir/Senha</th>
</tr>
<c:forEach var="usuario" items="${usuarios}">
	<tr>
	<td>${usuario.nome}</td>
	<td>${usuario.usuario}</td>
	<td>${usuario.perfil}</td>
	<td align="center">
	<c:if test="${((usuarioLogado.perfil=='ADMINISTRADOR')||(usuarioLogado.idUsuario==usuario.idUsuario))}">
	        <a href="${pageContext.request.contextPath}/usuariocrud?acao=alterar&idUsuario=${usuario.idUsuario}"><img src="${pageContext.request.contextPath}/img/alterar.png" border="0" width="20" alt="Alterar Usuario"></a>
	</c:if>
    <c:if test="${usuarioLogado.perfil=='ADMINISTRADOR'}">
	        <a href="${pageContext.request.contextPath}/usuariocrud?acao=excluir&idUsuario=${usuario.idUsuario}" onClick="if(!confirm('Deseja realmente excluir esse Usuario?'))return false;"><img src="${pageContext.request.contextPath}/img/excluir.png" border="0" width="25" alt="Excluir Usuario"></a>
	</c:if>
	<c:if test="${((usuarioLogado.perfil=='ADMINISTRADOR' || usuarioLogado.perfil=='USUARIO')||(usuarioLogado.idUsuario==usuario.idUsuario))}">
			<a href="${pageContext.request.contextPath}/usuariocrud?acao=informarSenha&idUsuario=${usuario.idUsuario}"><img src="${pageContext.request.contextPath}/img/senha.png" border="0" width="25" alt="Alterar Senha"></a>
	</c:if>
	</td>
	</tr>
</c:forEach>
</table>
<p/>
<c:if test="${usuarioLogado.perfil=='ADMINISTRADOR'}">
		<a href="${pageContext.request.contextPath}/usuariocrud?acao=incluir"><input type="submit" value="Cadastrar Usuario" /></a>&nbsp;<p/>
</c:if>
<a href="${pageContext.request.contextPath}/menu.jsp"><input type="submit" value="Voltar"/></a>
<p/>
</fieldset>
</html>