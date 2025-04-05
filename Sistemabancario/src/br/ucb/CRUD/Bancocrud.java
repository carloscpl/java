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


import br.ucb.Bean.Banco;
import br.ucb.Bean.Usuario;
import br.ucb.DAO.BancoDAO;

/**
 * Servlet implementation class Bancocrud
 */
@WebServlet("/Bancocrud")
public class Bancocrud extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		String acao = request.getParameter("acao"), pagina = null;
		Banco banco = new Banco();
		BancoDAO bancoDAO;
		List<Banco> bancos;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

		if (usuarioLogado == null) {
			request.setAttribute("erro", "Necessário logar para ter acesso");
			pagina = "/login.jsp";
		}
		else {
		try {
			bancoDAO = new BancoDAO();
			// Condição para listar Registros
			if ((acao == null) || (acao.equals("listar"))) {
				bancos = bancoDAO.listar();
				request.setAttribute("bancos", bancos);
				pagina = "/banco/listar.jsp";
			}

			// Condição para filtrar Registros
			if ((acao != null) && (acao.equals("filtrar"))) {
				bancos = bancoDAO.listar(request.getParameter("nomeFiltro"));
				request.setAttribute("bancos", bancos);
				pagina = "/banco/listar.jsp";
			}

			if ((acao != null) && (acao.equals("incluir"))) {
				request.setAttribute("banco", banco);
				pagina = "/banco/incluir.jsp";
			}

			// Condição para alterar Registros
			if ((acao != null) && (acao.equals("alterar"))) {
				try {
					banco = bancoDAO.consultar(Long.parseLong(request.getParameter("idBanco")));
					if (banco == null) {
						request.setAttribute("erro","Erro ao localizar para alteração");
					} else {
						request.setAttribute("banco", banco);
						pagina = "/banco/incluir.jsp";
					}
				} catch (NumberFormatException e) {
					request.setAttribute("erro", "Erro na alteração");
				}
			}

			if ((acao != null) && (acao.equals("salvar"))) {
				try {
					if (request.getParameter("banco") != "") {
						banco.setBanco(request.getParameter("banco"));

						if (request.getParameter("idBanco") != null&& !request.getParameter("idBanco").equals("")) {
							banco.setIdBanco((Long.parseLong(request.getParameter("idBanco"))));
						}

					} else {
						request.setAttribute("erro", "Erro no Cadastro!");
					}

					// Salva: inclui ou altera
					String idBanco = request.getParameter("idBanco");
					if (idBanco == null || idBanco.equals("")) { // Incluir
						if (bancoDAO.incluir(banco) > 0)
							request.setAttribute("mensagem","Banco cadastrado com sucesso!");
						else
							request.setAttribute("erro", "Erro de inclusão");

					} else { // Alterar

						banco.setIdBanco((Long.valueOf(request.getParameter("idBanco"))));
						if (bancoDAO.alterar(banco) > 0)
							request.setAttribute("mensagem","");
						else
							request.setAttribute("erro", "Erro de alteração");
					}
					bancos = bancoDAO.listar();
					request.setAttribute("bancos", bancos);
					pagina = "/banco/listar.jsp";

				} catch (NumberFormatException e) {
					e.printStackTrace();
					request.setAttribute("erro", "Erro de conversao!");
					bancos = bancoDAO.listar();
					request.setAttribute("bancos", bancos);
					pagina = "/banco/incluir.jsp";
				}
			}

			// Comando para excluir registros
			if ((acao != null) && (acao.equals("excluir"))) {
				banco.setIdBanco((Long.parseLong(request.getParameter("idBanco"))));
				if (bancoDAO.excluir(banco) > 0)
					request.setAttribute("mensagem","Banco excluído com sucesso!");
				else
					request.setAttribute("erro", "Erro de exclusão");
				bancos = bancoDAO.listar();
				request.setAttribute("bancos", bancos);
				pagina = "/banco/listar.jsp";
			}
		} catch (SQLException e) {
			request.setAttribute("erro", "Por favor,verifique os dados!");
			pagina = "/banco/listar.jsp";
		}
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}
