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

import br.ucb.Bean.Agencia;
import br.ucb.Bean.Banco;
import br.ucb.Bean.Usuario;
import br.ucb.DAO.AgenciaDAO;
import br.ucb.DAO.BancoDAO;

/**
 * Servlet implementation class Agenciacrud
 */
@WebServlet("/Agenciacrud")
public class Agenciacrud extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		String acao = request.getParameter("acao"), pagina = null;
		Agencia agencia = new Agencia();
		AgenciaDAO agenciaDao;
		BancoDAO bancoDao;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		List<Agencia> agencias;
		List<Banco> bancos;
		
		if (usuarioLogado == null) {
			request.setAttribute("erro", "Necessário logar para ter acesso");
			pagina = "/login.jsp";
		}
		else {

		try {
			agenciaDao = new AgenciaDAO();
			bancoDao = new BancoDAO();

			// Condição para listar Registros
			if ((acao == null) || (acao.equals("listar"))) {
				agencias = agenciaDao.listar();
				request.setAttribute("agencias", agencias);
				pagina = "/agencia/listar.jsp";

			}

			if ((acao != null) && (acao.equals("incluir"))) {
				bancos = bancoDao.listar();
				if (bancos.size() == 0) {
					request.setAttribute("erro","Não existe banco para essa agência ainda. E necessário cadastrar!");
					pagina = "/agencia/listar.jsp";
				} else {
					request.setAttribute("agencia", agencia);
					request.setAttribute("bancos", bancos);
					pagina = "/agencia/incluir.jsp";
				}
			}

			if ((acao != null) && (acao.equals("filtrar"))) {
				agencias = agenciaDao.listar(request.getParameter("nomeFiltro"));
				request.setAttribute("agencias", agencias);
				pagina = "/agencia/listar.jsp";
			}

			if ((acao != null) && (acao.equals("alterar"))) {
				try {
					agencia = agenciaDao.consultar(Long.valueOf(request.getParameter("idAgencia")));
					if (agencia == null) {
						request.setAttribute("erro","Erro ao localizar para alteração!");
					} else {
						bancos = bancoDao.listar();
						request.setAttribute("agencia", agencia);
						request.setAttribute("bancos", bancos);
						pagina = "/agencia/incluir.jsp";
					}
				} catch (NumberFormatException e) {
					request.setAttribute("erro", "Erro na alteração");
				}
			}

			// Condição para salvar os Registros
			// Se acao igual diferente de nulo e igual a salvar

			if ((acao != null) && (acao.equals("salvar"))) {
				try {
					if (request.getParameter("nomeAgencia") != "") {

						if (request.getParameter("idAgencia") != "" && !request.getParameter("idAgencia").equals(""))
							agencia.setIdAgencia((Long.parseLong(request.getParameter("idAgencia"))));

						if (request.getParameter("nomeAgencia") != "") {
							agencia.setNomeAgencia((request.getParameter("nomeAgencia")));
						}

						if (request.getParameter("banco") != null)
							agencia.getBanco().setIdBanco(Long.valueOf(request.getParameter("banco")));

					} else {
						request.setAttribute("erro","Por favor,preencha os dados do Contato corretamente!");
					}

					// Salva: inclui ou altera

					if (request.getParameter("idAgencia") == null || request.getParameter("idAgencia").equals("")) { // Incluir
						if (agenciaDao.incluir(agencia) > 0)
							request.setAttribute("mensagem","Agência cadastrada com sucesso!");
						else
							request.setAttribute("erro", "Erro de inclusão");

					} else {
						agencia.setIdAgencia((Long.parseLong(request
								.getParameter("idAgencia"))));
						if (agenciaDao.alterar(agencia) > 0)
							request.setAttribute("mensagem",
									"");
						else
							request.setAttribute("erro", "Erro na alteração dos registros!");
					}
					agencias = agenciaDao.listar();
					request.setAttribute("agencias", agencias);
					pagina = "/agencia/listar.jsp";

				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("erro","E necessario colocar os dados da Agencia!");
					bancos = bancoDao.listar();
					request.setAttribute("bancos", bancos);
					pagina = "/agencia/incluir.jsp";
				}

			}

			// Condição para excluir Registros
			if ((acao != null) && (acao.equals("excluir"))) {
				agencia = agenciaDao.consultar(Long.valueOf(request
						.getParameter("idAgencia")));
				if (agenciaDao.excluir(agencia) > 0) {
					request.setAttribute("mensagem", "Agência excluida com sucesso!");
				} else {
					request.setAttribute("erro", "Erro de exclusão");
				}
				agencias = agenciaDao.listar();
				request.setAttribute("agencias", agencias);
				pagina = "/agencia/listar.jsp";
			}

		} catch (SQLException e) {
			request.setAttribute("erro",
					"Erro no banco de dados");
			pagina = "/agencia/listar.jsp";
		}
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}
