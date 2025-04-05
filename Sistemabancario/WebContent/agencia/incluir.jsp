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
	<title>Agência - Sistema Bancário</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
<body>
<fieldset>
	<label>Para voltar clique em:</label>
	<a href="${pageContext.request.contextPath }/agenciacrud"><input type="submit" value="Voltar"/></a><p/>
	<form method="post" action="${pageContext.request.contextPath}/agenciacrud">
		    <center><h2>Cadastro de Agências</h2></center><p/>
		    <fieldset id='filCenter'>
		    <font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font><p/>
			<input type="hidden" name="acao" value="salvar" />
			<input type="hidden" name="idAgencia" value="${agencia.idAgencia}" />
			<label>Cadastrar agência</label><p/>
			<input type="text" name="nomeAgencia" value="${agencia.nomeAgencia==null?param.nomeAgencia:agencia.nomeAgencia}" size="50" /><p />
			<label>Nome do Banco</label><p/>
			<select name="banco">
			 <c:forEach var="banco" items="${bancos}">
				<option selected="selected"  ${(agencia.banco.idBanco==agencia.idAgencia)||(param.idBanco==banco.idBanco)?'selected':''}
					value="${banco.idBanco}">${banco.banco}</option>
			 </c:forEach>
		   </select><p/>
			<input type="submit" value="Salvar" />
			</fieldset>
	</form>
	</fieldset>
	<p />
	<p />
</body>
</html>