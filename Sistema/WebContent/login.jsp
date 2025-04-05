 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login - Sistema Bancário</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
	<link type="text/css" rel="stylesheet" href="${raiz}/css/login.css" />
</head>
<body>
	<div id="div-login">
	<p />
	<form id="frmLogin" method="post" action="${pageContext.request.contextPath}/login">
		<fieldset>
			<center><h1>Sistema Bancário(Login)</h1></center>
			<font color="#FF0000">${erro}</font>
		    <font color="#00FF00">${mensagem}</font>
		    <p />
			<label>Usuario: </label><p />
			<input type="text" name="usuario" value="${param.usuario}" size="30" maxlength="20" /><p />
			<label>Senha: </label><p />
			<input type="password" name="senha" value="${param.senha}" size="30" maxlength="20" /><p />
			<input type="submit" value="Entrar" /><p/>
			<font color="#747474"> Desenvolvedor:</font>
			<font color="#00FF00"> Carlos pereira lopes</font>
		</fieldset>
	</form>
	<p />
	</div>
</body>
