<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="${raiz}/css/login.css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<div>
		<c:if test="${usuarioLogado != null}">
			<div style="float: right;" style ="width: 100px;" >
				<form method="get" action="${pageContext.request.contextPath}/login">
					<input type="submit" value="Sair" />
				</form>
			</div>
		</c:if>
	</div>
	<p />
	<p />
</div>
<p />