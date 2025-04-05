<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloListagem.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Banco - Sistema Bancário</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
<body>
<fieldset>
	<label>Para voltar clique em:</label>
	<a href="${pageContext.request.contextPath }/bancocrud"><input type="submit" value="Voltar"/></a><p/>
	<form method="post" action="${pageContext.request.contextPath}/bancocrud">
		    <center><h2>Cadastro de Instituições</h2></center><p/>
		    <fieldset id='filCenter'>
		    <font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font><p/>
			<input type="hidden" name="acao" value="salvar" />
			<input type="hidden" name="idBanco" value="${banco.idBanco}" />
			<label>Cadastrar Bancos</label><p/>
			<input type="text" name="banco" value="${banco.banco==null?param.banco:banco.banco}" size="50" /><p />
			<input type="submit" value="Salvar" />
			</fieldset>
	</form>
	</fieldset>
	<p />
	<p />
</body>
</html>