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
	href="${pageContext.request.contextPath}/css/estiloListagemContas.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Conta - Sistema Bacário</title>
    <c:set var="raiz" value="${pageContext.request.contextPath}" />
<body>
<fieldset>
	<label>Para voltar clique em:</label>
	<a href="${pageContext.request.contextPath }/contacrud"><input type="submit" value="Voltar"/></a><p/>
	<form method="post" action="${pageContext.request.contextPath}/contacrud">
		    <center><h2>Cadastro de Contas</h2></center><p/>
		    <fieldset id='filCenter'>
		    <font color="#FF0000">${erro}</font><font color="#00FF00">${mensagem}</font><p/>
			<input type="hidden" name="acao" value="salvar" />
			<input type="hidden" name="idConta" value="${conta.idConta}" />
			<label>Operação</label>
			<input type="text" name="operacao" value="${conta.operacao==null?param.operacao:conta.operacao}" maxlength="3" size="20" /><p />
			<label>Num. Conta</label>
			<input type="text" name="conta" value="${conta.conta==null?param.conta:conta.conta}" maxlength="7" size="20" /><p />
			<label>Tipo Conta</label>
			<input type="text" name="tipoConta" value="${conta.tipoConta==null?param.tipoConta:conta.tipoConta}" maxlength="20" size="20" /><p />
			<label>Cliente</label>
			<select name="cliente">
			 <c:forEach var="cliente" items="${clientes}">
				<option selected="selected"  ${(conta.cliente.idCliente==conta.idConta)||(param.idConta==conta.idConta)?'selected':''}
					value="${cliente.idCliente}">${cliente.nome}</option>
			 </c:forEach>
		   </select><p/>
		   <label>Agência</label>
			<select name="agencia">
			 <c:forEach var="agencia" items="${agencias}">
				<option selected="selected"  ${(conta.agencia.idAgencia==conta.idConta)||(param.idConta==conta.idConta)?'selected':''}
					value="${agencia.idAgencia}">${agencia.nomeAgencia}</option>
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