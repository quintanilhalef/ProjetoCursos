package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.clienteDAO;
import DAO.cursoDAO;
import DAO.pagamentoDAO;
import entidades.Cliente;
import entidades.Curso;
import entidades.Pagamento;
import entidades.PagamentoId;

@WebServlet("/servcontrol.do")
public class ServletControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long cpf;
	String nome, email;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		clienteDAO cdao = new clienteDAO();
		cursoDAO cudao = new cursoDAO();
		pagamentoDAO pdao = new pagamentoDAO();
		int idformulario = Integer.parseInt(request.getParameter("idformulario"));
		int tipoformulario = Integer.parseInt(request.getParameter("tipoformulario"));
		HttpSession session = request.getSession();
			
		if (idformulario == 1) { // cliente

			switch (tipoformulario) {

			case 11: { //consultar todos
				
				List<Cliente> lista = cdao.findAll();
				for(Cliente c: lista) {
					System.out.println(c);
				}					
					RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/consultaTodos.jsp");
					request.setAttribute("lista", lista);
					dispatcher.forward(request, response);
					//session.setAttribute("lista", lista);
					//response.sendRedirect("clientes/consultaTodos.jsp");
					
				
				
				break;
			}
			case 12: { // consultar

				String cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				Cliente cliente = (Cliente) cdao.find(cpf);

				if (cliente != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/rConsulta.jsp");
					request.setAttribute("cliente", cliente);
					dispatcher.forward(request, response);
					
				} else {
					session.setAttribute("mensagem", "Cliente não encontrado");
					session.setAttribute("cliente", null);
					response.sendRedirect("clientes/consulta.jsp");
					
				}
				break;
				
			}

			case 15: { // excluir

				String cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				Cliente cliente = (Cliente) cdao.findOpen(cpf);
				if (cliente != null) {
					cdao.remove(cliente);
					session.setAttribute("mensagem", "Cliente Excluido com sucesso ");
					session.setAttribute("cliente", null);
					response.sendRedirect("clientes/exclusao.jsp");
				} else {
					session.setAttribute("mensagem", "CPF inexistente!");
					response.sendRedirect("clientes/exclusao.jsp");
				}

			}
			}

		} else if (idformulario == 2) { // cursos

			idformulario = Integer.parseInt(request.getParameter("idformulario"));
			tipoformulario = Integer.parseInt(request.getParameter("tipoformulario"));

			switch (tipoformulario) {

			case 21: { // consultar todos
				
				List<Curso> lista = cudao.findAll();
				for(Curso c: lista) {
					System.out.println(c);
				}					
					RequestDispatcher dispatcher = request.getRequestDispatcher("cursos/consultaTodos.jsp");
					request.setAttribute("lista", lista);
					dispatcher.forward(request, response);

				break;
			}
			case 22: { // consultar

				int cdcurso = Integer.parseInt(request.getParameter("cdcurso"));
				Curso curso = (Curso) cudao.find(cdcurso);

				if (curso != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("cursos/cConsulta.jsp");
					request.setAttribute("curso", curso);
					dispatcher.forward(request, response);
					
				} else {
					session.setAttribute("mensagem", "Curso não encontrado");
					session.setAttribute("curso", null);
					response.sendRedirect("clientes/exclusao.jsp");
				}

				break;
			}

			case 25: { // excluir
				int cdcurso = Integer.parseInt(request.getParameter("cdcurso"));
				Curso curso = (Curso) cudao.find(cdcurso);

				if (curso != null) {
					cudao.remove(curso);
					session.setAttribute("mensagem", "Curso excluido.");
					session.setAttribute("curso", null);
					response.sendRedirect("cursos/exclusao.jsp");

				} else {
					session.setAttribute("mensagem", "Curso inexistente, digite um curso válido!");
					response.sendRedirect("cursos/exclusao.jsp");
				}

				break;
			}

			}
		} else if (idformulario == 3) { // pagamento



			idformulario = Integer.parseInt(request.getParameter("idformulario"));
			tipoformulario = Integer.parseInt(request.getParameter("tipoformulario"));

			switch (tipoformulario) {

			case 31: { // consultar todos
				

				List<Pagamento> lista = pdao.findAll();
				for(Pagamento p: lista) {
					System.out.println(p);
				}					
					RequestDispatcher dispatcher = request.getRequestDispatcher("pagamentos/consultaTodos.jsp");
					request.setAttribute("lista", lista);
					dispatcher.forward(request, response);

				
				break;
			}
			case 32: { // consultar

				int cdcurso = Integer.parseInt(request.getParameter("cdcurso"));
				String cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);

				Pagamento pagamento = (Pagamento) pdao.find(cpf, cdcurso);

				if (pagamento != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("pagamentos/pConsulta.jsp");
					request.setAttribute("pagamento", pagamento);
					dispatcher.forward(request, response);

				} else {

					session.setAttribute("mensagem", "consulta não feita");
					session.setAttribute("pagamento", null);
					response.sendRedirect("pagamentos/consulta.jsp");

				}

				break;
			}

			case 35: { // excluir
				int cdcurso = Integer.parseInt(request.getParameter("cdcurso"));
				String cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				
				Pagamento pagamento = (Pagamento) pdao.find(cpf, cdcurso);

				if (pagamento != null) {
					pdao.remove(pagamento);
					session.setAttribute("mensagem", "Pagamento excluido.");
					response.sendRedirect("pagamentos/exclusao.jsp");

				} else {

					session.setAttribute("mensagem", "Pagamento não excluido");
					session.setAttribute("pagamento", null);
					response.sendRedirect("pagamentos/exclusao.jsp");

				}
				
				break;
			}
		}
	}

			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		clienteDAO cdao = new clienteDAO();
		cursoDAO cudao = new cursoDAO();
		pagamentoDAO pdao = new pagamentoDAO();
		int idformulario = Integer.parseInt(request.getParameter("idformulario"));
		int tipoformulario = Integer.parseInt(request.getParameter("tipoformulario"));
		HttpSession session = request.getSession();

		if (idformulario == 1) { // cliente

			switch (tipoformulario) {


			case 13: { // cadastrar
				String cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				Cliente cliente = new Cliente(cpf, email, nome);

				try {
					cdao.persist(cliente);
					session.setAttribute("mensagem", "cadastrado com sucesso!");
					session.setAttribute("cliente", cliente);
					response.sendRedirect("clientes/cadastro.jsp");
				} catch (Exception e) {
					session.setAttribute("mensagem", "Cadastro não feito.");
					session.setAttribute("cliente", null);
					response.sendRedirect("clientes/cadastro.jsp");
				}

				break;
			}

			case 14: { // alterar

				String cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);
				Cliente cliente = cdao.findOpen(cpf);

				if (cliente != null) {
					cliente.setNome(request.getParameter("nome"));
					cliente.setEmail(request.getParameter("email"));
					cdao.merge(cliente);
					session.setAttribute("mensagem", "Cliente alterado!");
					session.setAttribute("cliente", cliente);
					response.sendRedirect("clientes/alteracao.jsp");
				} else {
					session.setAttribute("mensagem", "Digite um cpf válido.");
					session.setAttribute("cliente", null);
					response.sendRedirect("clientes/alteracao.jsp");
				}

				break;
			}
			}
		} else if (idformulario == 2) { // cursos

			idformulario = Integer.parseInt(request.getParameter("idformulario"));
			tipoformulario = Integer.parseInt(request.getParameter("tipoformulario"));

			switch (tipoformulario) {
		
			case 23: { // cadastrar

				int cdcurso = Integer.parseInt(request.getParameter("cdcurso"));
				long valor = Long.parseLong(request.getParameter("valor"));
				String nome = request.getParameter("nome");
				String url = request.getParameter("url");
				Curso curso = new Curso(valor, url, nome, cdcurso);

				try {
					cudao.persist(curso);
					session.setAttribute("mensagem", "cadastrado com sucesso!");
					session.setAttribute("curso", curso);
					response.sendRedirect("cursos/cadastro.jsp");
				} catch (Exception e) {
					session.setAttribute("mensagem", "cadastro não feito.");
					session.setAttribute("curso", null);
					response.sendRedirect("cursos/cadastro.jsp");
				}

				break;
			}

			case 24: { // alterar
				int cdcurso = Integer.parseInt(request.getParameter("cdcurso"));

				Curso curso = cudao.findOpen(cdcurso);
				if (curso != null) {
					long valor = Long.parseLong(request.getParameter("valor"));
					String nome = request.getParameter("nome");
					String url = request.getParameter("url");
					cudao.merge(curso);
					session.setAttribute("mensagem", "Curso alterado");
					session.setAttribute("curso", curso);
					response.sendRedirect("cursos/alteracao.jsp");

				} else {
					session.setAttribute("mensagem", "Curso não encontrado");
					session.setAttribute("curso", null);
					response.sendRedirect("cursos/alteracao.jsp");
				}

				break;
			}

			}
		} else if (idformulario == 3) { // pagamento
			
			switch (tipoformulario) { 
			
			
			case 33: { // cadastrar

				String datainscricao = request.getParameter("datainscricao");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(datainscricao, formatter);
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				datainscricao = fmt.format(date);
				int cdcurso = Integer.parseInt(request.getParameter("cdcurso"));

				String cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);

				PagamentoId id = new PagamentoId(cdcurso, cpf);
				Pagamento pagamento = new Pagamento(id, datainscricao);

				try {
					pdao.persist(pagamento);
					session.setAttribute("mensagem", "cadastrado com sucesso!");
					session.setAttribute("pagamento", pagamento);
					response.sendRedirect("pagamentos/cadastro.jsp");
				} catch (Exception e) {
					session.setAttribute("mensagem", "cadastro não feito.");
					session.setAttribute("pagamento", null);
					response.sendRedirect("pagamentos/cadastro.jsp");
				}

				break;
			}

			case 34: { // alterar
				String datainscricao = request.getParameter("datainscricao");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(datainscricao, formatter);
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				datainscricao = fmt.format(date);

				int cdcurso = Integer.parseInt(request.getParameter("cdcurso"));

				String cpfmascara = request.getParameter("cpf");
				cpfmascara = cpfmascara.replaceAll("[.-]", "");
				cpf = Long.parseLong(cpfmascara);

				Pagamento pagamento = pdao.findOpen(cpf, cdcurso);

				if (pagamento != null) {
					System.out.println(pagamento);
					pagamento.setDatainscricao(datainscricao);
					pdao.merge(pagamento);
					
					session.setAttribute("mensagem", "Cadastro alterado");
					session.setAttribute("pagamento", pagamento);
					response.sendRedirect("pagamentos/alteracao.jsp");

				} else {

					session.setAttribute("mensagem", "Cadastro não alterado");
					session.setAttribute("pagamento", null);
					response.sendRedirect("pagamentos/alteracao.jsp");

				}


				break;
			
				
				}
			}

		}
	}
	
}
	

	
