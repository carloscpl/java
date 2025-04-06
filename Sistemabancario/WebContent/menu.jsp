<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu-Sistema Bancário</title>
<c:set var="raiz" value="${pageContext.request.contextPath}" />
<link type="text/css" rel="stylesheet" href="${raiz}/css/menu.css" />
</head>
<body>
	<fieldset>
		<h1>Bem vindo, ao Sistema Bancário</h1>
		<font color="#00FF00">${mensagem}</font>
		<div id="menu">
		<ul id="menuver">
			<li>
			 <a href="${pageContext.request.contextPath}/bancocrud">Gestão de Bancos</a>
			 <a href="${pageContext.request.contextPath}/agenciacrud">Gestão de Agências</a>
			 <a href="${pageContext.request.contextPath}/clientecrud">Gestão de Clientes</a>
			 <a href="${pageContext.request.contextPath}/contacrud">Gestão de Contas</a>		
			 <a href="${pageContext.request.contextPath}/usuariocrud">Gestão de Usuarios</a>
			 </li>
		</ul>
		</div>
	</fieldset>
</body>
</html>