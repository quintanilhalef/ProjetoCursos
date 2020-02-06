package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import DAO.loginDAO;
import entidades.Login;

@WebServlet("/servaut.do")
public class ServletAutenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;

	public ServletAutenticador() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpfmascara = request.getParameter("cpf");
		cpfmascara = cpfmascara.replaceAll("[.-]", "");
		Long cpf = Long.parseLong(cpfmascara);
		String senha = request.getParameter("senha");
		loginDAO ldao = new loginDAO();
		HttpSession sessao = request.getSession();
		Login login = ldao.find(cpf, senha);
		
		if(login != null) {
			sessao.setMaxInactiveInterval(3000);
			sessao.setAttribute("mensagem", "Usuário autenticado");
			sessao.setAttribute("login", "true");
			
			response.sendRedirect("index.jsp");
		} else {
			sessao.setAttribute("mensagem", "Usuário não autenticado");
			response.sendRedirect("login.jsp");
		}
	}

}
