package br.ucb.CRUD;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ucb.Bean.Cliente;
import br.ucb.Bean.Usuario;
import br.ucb.DAO.ClienteDAO;

@WebServlet("/Clientecrud")
public class Clientecrud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		 HttpSession session = request.getSession(true);
		String acao = request.getParameter("acao"), pagina = null;
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO;
		List<Cliente> clientes;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");


		if (usuarioLogado == null) {
			request.setAttribute("erro", "Necessário logar para ter acesso");
			pagina = "/login.jsp";
		}
		else {
		try {
			clienteDAO = new ClienteDAO();
			// Condição para listar Registros
			if ((acao == null) || (acao.equals("listar"))) {
				clientes = clienteDAO.listar();
				request.setAttribute("clientes", clientes);
				pagina = "/cliente/listar.jsp";
			}

			// Condição para filtrar Registros
			if ((acao != null) && (acao.equals("filtrar"))) {
				clientes = clienteDAO.listar(request.getParameter("nomeFiltro"));
				request.setAttribute("clientes", clientes);
				pagina = "/cliente/listar.jsp";
			}

			if ((acao != null) && (acao.equals("incluir"))) {
				request.setAttribute("cliente", cliente);
				pagina = "/cliente/incluir.jsp";
			}

			// Condição para alterar Registros
			if ((acao != null) && (acao.equals("alterar"))) {
				try {
					cliente = clienteDAO.consultar(Long.parseLong(request.getParameter("idCliente")));
					if (cliente == null) {
						request.setAttribute("erro","Erro ao localizar para alteração");
					} else {
						request.setAttribute("cliente", cliente);
						pagina = "/cliente/incluir.jsp";
					}
				} catch (NumberFormatException e) {
					request.setAttribute("erro", "Erro na alteração");
				}
			}

			if ((acao != null) && (acao.equals("salvar"))) {
				try {
					if (request.getParameter("nome") != "") {
						cliente.setNome(request.getParameter("nome"));
						
						if (request.getParameter("rg") != "") {
							cliente.setRg((request.getParameter("rg")));
						}
						
						if (request.getParameter("cpf") != "") {
							cliente.setCpf((request.getParameter("cpf")));
						}
						
						if (request.getParameter("nome") != "") {
							cliente.setNome((request.getParameter("nome")));
						}

						if (request.getParameter("idCliente") != null&& !request.getParameter("idCliente").equals("")) {
							cliente.setIdCliente((Long.parseLong(request.getParameter("idCliente"))));
						}

					} else {
						request.setAttribute("erro", "Erro no Cadastro!");
					}

					// Salva: inclui ou altera
					String idCliente = request.getParameter("idCliente");
					if (idCliente == null || idCliente.equals("")) { // Incluir
						if (clienteDAO.incluir(cliente) > 0)
							request.setAttribute("mensagem","Banco cadastrado com sucesso!");
						else
							request.setAttribute("erro", "Erro de inclusão");

					} else { // Alterar

						cliente.setIdCliente((Long.valueOf(request.getParameter("idCliente"))));
						if (clienteDAO.alterar(cliente) > 0)
							request.setAttribute("mensagem","Cliente alterado com sucesso!");
						else
							request.setAttribute("erro", "Erro de alteração");
					}
					clientes = clienteDAO.listar();
					request.setAttribute("clientes", clientes);
					pagina = "/cliente/listar.jsp";

				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("erro", "Erro de conversao!");
					clientes = clienteDAO.listar();
					request.setAttribute("clientes", clientes);
					pagina = "/cliente/incluir.jsp";
				}
			}

			// Comando para excluir registros
			if ((acao != null) && (acao.equals("excluir"))) {
				cliente.setIdCliente((Long.parseLong(request.getParameter("idCliente"))));
				if (clienteDAO.excluir(cliente) > 0)
					request.setAttribute("mensagem","Cliente excluído com sucesso!");
				else
					request.setAttribute("erro", "Erro de exclusão");
				clientes = clienteDAO.listar();
				request.setAttribute("clientes", clientes);
				pagina = "/cliente/listar.jsp";
			}
		} catch (SQLException e) {
			request.setAttribute("erro", "Por favor,verifique os dados!");
			pagina = "/cliente/listar.jsp";
		}
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}
