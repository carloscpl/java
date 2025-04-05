<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Trocar senha</title>
<c:set var="raiz" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${raiz}/jquery/jquery-1.2.6.pack.js"></script>
<script type="text/javascript"
	src="${raiz}/jquery/validacao/jquery.validate.js"></script>
<script type="text/javascript"
	src="${raiz}/jquery/validacao/jquery_funcao_validacao.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/estiloListagem.css">
<script type="text/javascript">
	$(document).ready(function() {
		$("#frmTroca").validate({
			rules : {
				senha1 : {
					required : true,
					minlength : 4
				},
				senha2 : {
					required : true,
					minlength : 4
				},
				novaSenha : {
					required : true,
					minlength : 4
				}
			},
			messages : {
				senha1 : {
					required : "Senha obrigatoria",
					minlength : "Minimo 4 caracteres"
				},
				senha2 : {
					required : "Senha obrigatoria",
					minlength : "Minimo 4 caracteres"
				},
				novaSenha : {
					required : "Senha obrigatoria",
					minlength : "Minimo 4 caracteres"
				}
			}
		});
	});
</script>
</head>
<body>
	<div id="div-login">
		<p />
		<p />
		<fieldset>
		<jsp:include page="/templete/cabecalho.jsp"/><br>
		<font color="#FF0000">${erro}</font> <font color="#00FF00">${mensagem}</font>
		<font color="#747474"> Nome::</font>
		<font color="#00FF00"> ${usuario.nome}</font>
		<br>
		<form id="frmTroca" method="post" action="${raiz}/usuariocrud">
				<input type="hidden" name="acao" value="trocarSenha" /> <input
					type="hidden" name="idUsuario"
					value="${usuario.idUsuario==null?param.idUsuario:usuario.idUsuario}" />
				<h2>Trocar Senha</h2>
				<br>
				<br>
				<label>Senha antiga</label><br /><br /> <input type="password"
					name="senha1" value="${param.senha1}" size="15" maxlength="15" />
				<p />
				<label>Nova senha</label><br /><br /> <input type="password"
					name="senha2" value="${param.senha2}" size="15" maxlength="15" />
				<p />
				<label>Nova senha</label><br /><br /> <input type="password"
					name="novaSenha" value="${param.novaSenha}" size="15"
					maxlength="15" />
				<p />
				<input type="submit" value="Alterar" />
		</form>
		<a href="${pageContext.request.contextPath }/usuariocrud"><input type="submit" value="Voltar"/></a>
		</fieldset>
		<p />
	</div>
</body>
</html>