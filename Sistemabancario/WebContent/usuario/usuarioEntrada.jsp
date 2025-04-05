<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Usuario - Sistema Bancário</title>
	 <c:set var="raiz" value="${pageContext.request.contextPath}" />
	<script type="text/javascript" src="${raiz}/jquery/jquery-1.2.6.pack.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery.validate.js"></script>
	<script type="text/javascript" src="${raiz}/jquery/validacao/jquery_funcao_validacao.js"></script>
	<link type="text/css" rel="stylesheet" href="${raiz}/css/estiloListagem.css" />
	<script type="text/javascript">
		$(document).ready( function() {
			$("#frmUsuario").validate({
	      		rules:{
	        		nome:{
	          			required: true,
	          			minlength: 10
	        		},
	        		usuario:{
	          			required: true,
	          			minlength: 5
	        		},
	        		senha:{
	            		required: true,
	            		minlength: 4
	        		}
	      		},
	      		messages:{
	        		nome:{
	            		required: "Nome obrigatorio",
	            		minlength: "No minino 10 caracter"
	        		},
	        		usuario:{
	          			required: "Senha obrigatorio",
	          			minlength: "No minino 5 caracter"
	        		},
	        		senha:{
	          			required: "Senha obrigatoria",
	          			minlength: "Minimo 4 caracteres"
	        		}
	      		}
	    	});
	  	});
	</script>
	</head>
    <body>
    
	<div id="div-login">
	 <fieldset>
	 <jsp:include page="/templete/cabecalho.jsp"/><br>
	<font color="#FF0000">${erro}</font>
	<form id="frmUsuario" method="post" action="${raiz}/usuariocrud">
		    <h2>Cadastrar Usuario</h2>
			<input type="hidden" name="acao" value="salvar" />
			<input type="hidden" name="idUsuario" value="${usuario.idUsuario==null?param.idUsuario:usuario.idUsuario}" />
			<p />
			<p />
			<label>Nome</label><p/>
			<input type="text" name="nome" value="${usuario.nome==null?param.nome:usuario.nome}" size="50" maxlength="50" /><p />
			<label>Usuario</label><p/>
			<input type="text" name="usuario" ${((usuario.idUsuario== null)||(param.idUsuario== null))?'':'disabled'} value="${usuario.usuario==null?param.usuario:usuario.usuario}" size="15" maxlength="15" /><p />
			<c:if test="${((usuario.idUsuario == null)||(param.idUsuario == null))}">
				<label>Senha</label><p/>
				<input type="password" name="senha" value="${param.senha}" size="15" maxlength="15" /><p />
			</c:if>
			<label>Perfil</label><p/>
			<c:if test="${usuarioLogado.perfil=='ADMINISTRADOR'}">
				<select name="perfil">
					<c:forEach var="perfil" items="${perfis.valores}">
						<option ${(usuario.perfil==perfil)||(param.perfil==perfil)?'selected':''}>${perfil}</option>
					</c:forEach>
				</select><p />
			</c:if>
			<c:if test="${usuarioLogado.perfil!='ADMINISTRADOR'}">
				<input type="text" disabled="disabled" value="${usuario.perfil==null?param.perfil:usuario.perfil}" size="15" maxlength="15" /><p />
				<input type="hidden" name="perfil" value="${usuario.perfil==null?param.perfil:usuario.perfil}"  />
			</c:if>
			 <input type="submit" value="Salvar" />
	    </form>
	   
			 <a href="${pageContext.request.contextPath }/usuariocrud"><input type="submit" value="Voltar" /></a>
		</fieldset>
	    <p/>
	    </div>
</body>
</html>