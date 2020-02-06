<%@ page import="DAO.loginDAO" %>
<%@ page import="entidades.Login"%>

<jsp:declaration>

	public void init() throws ServletException{
		
		
	}

</jsp:declaration>	
	
<jsp:scriptlet>

			String cpfmascara = request.getParameter("cpf");
			cpfmascara = cpfmascara.replaceAll("[.-]", "");
			Long cpf = Long.parseLong(cpfmascara);
			String senha = request.getParameter("senha");
			loginDAO ldao = new loginDAO();
			HttpSession sessao = request.getSession();
			Login login = ldao.find(cpf, senha);

			if (login != null) {
				sessao.setMaxInactiveInterval(3000);
				sessao.setAttribute("login", "true");

				response.sendRedirect("../index.jsp");
			} else {
				sessao.setAttribute("mensagem", "Usuário não autenticado");
				sessao.setAttribute("login", "false");
				response.sendRedirect("../login.jsp");
			}
			
</jsp:scriptlet>