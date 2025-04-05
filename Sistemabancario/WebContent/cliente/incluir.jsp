<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="JavaScript" type="text/javascript" src="/js/MascaraValidacao.js">
</script> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estiloListagem.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cliente - Sistema Bancário</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
<body>
<fieldset>
	<label>Para voltar clique em:</label>
	<a href="${pageContext.request.contextPath }/clientecrud"><input type="submit" value="Voltar"/></a><p/>
	<form name="cliente" method="post" action="${pageContext.request.contextPath}/clientecrud">
		    <center><h2>Cadastro de Clientes</h2></center><p/>
		    <fieldset id='filCenter'>
		    <font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font><p/>
			<input type="hidden" name="acao" value="salvar" />
			<input type="hidden" name="idCliente" value="${cliente.idCliente}" />
			<label>Cadastrar Cliente</label><p/>
			<input type="text" name="nome"  value="${cliente.nome==null?param.nome:cliente.nome}" maxlength="50" size="50" /><p />
			<label>RG</label><p/>
			<input type="text" name="rg" value="${cliente.rg==null?param.rg:cliente.rg}" maxlength="7" size="10" /><p />
			<label>CPF</label><p/>
			<input type="text" name="cpf" onBlur="ValidarCPF(cliente.cpf);" onKeyPress="MascaraCPF(cliente.cpf);"
			value="${cliente.cpf==null?param.cpf:cliente.cpf}" maxlength="11" size="20"><p />
			<input type="submit" value="Salvar" />
			</fieldset>
	</form>
	</fieldset>
	<p />
	<p />
</body>
</html>