<%
	if (session.getAttribute("login") != "true"){
		session.setAttribute("mensagem","Acesso Proibido! Por favor, faça o login novamente.");
%>
<jsp:forward page="/login.jsp"></jsp:forward>
<%
	}
%>