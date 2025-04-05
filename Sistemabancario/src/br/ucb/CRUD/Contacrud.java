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
import br.ucb.Bean.Cliente;
import br.ucb.Bean.Conta;
import br.ucb.DAO.AgenciaDAO;
import br.ucb.DAO.ClienteDAO;
import br.ucb.DAO.ContaDAO;


/**
 * Servlet implementation class ServicoCRUD
 */
@WebServlet("/Contacrud")
public class Contacrud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		RequestDispatcher dispatcher;
		//criei uma acao do tipo string,ou seja, as passagens de paramentros
		//Vão ser feitas através dessa ação,dependendo da requisão do usuario
		String acao = request.getParameter("acao"), pagina = null;
		
		Conta conta = new Conta();
		ContaDAO contaDao;
		ClienteDAO clienteDao;
		AgenciaDAO agenciaDao;

		List<Conta> contas;
		List<Cliente> clientes;
		List<Agencia> agencias;
		
		if (session.getAttribute("usuarioLogado") == null){
			request.setAttribute("mensagem", "Obrigado, pelo acesso");
			pagina = "/login.jsp";	
			
		}else {	
			try {
				clienteDao = new ClienteDAO();
				contaDao = new ContaDAO();
				agenciaDao = new AgenciaDAO();
	
				// Condição para listar Registros
				if ((acao == null) || (acao.equals("listar"))) {
					contas = contaDao.listar();
					request.setAttribute("contas", contas);
					pagina = "/conta/listar.jsp";
	
				}
				
				//Condição para filtrar Registros
				if ((acao != null) && (acao.equals("filtrar"))) {
					 contas = contaDao.listar(request.getParameter("nomeFiltro"));
					 request.setAttribute("contas", contas);
					 pagina = "/conta/listar.jsp";
				}
				
				//Condição para incluir Servico
				if ((acao != null) && (acao.equals("incluir"))) {
					clientes = clienteDao.listar();
					agencias = agenciaDao.listar();
					request.setAttribute("conta", conta);
					request.setAttribute("clientes", clientes);
					request.setAttribute("agencias", agencias);
					pagina = "/conta/incluir.jsp";		
				}
				
				if ((acao != null) && (acao.equals("excluir"))) {
					conta = contaDao.consultar(Long.valueOf(request.getParameter("idConta")));
					if (contaDao.excluir(conta) > 0) {
						request.setAttribute("mensagem", "Excluido com sucesso");
					} else {
						request.setAttribute("erro", "Erro de exclusão");
					}
					contas = contaDao.listar();
					request.setAttribute("contas", contas);
					pagina = "/conta/listar.jsp";
				}
	
				// Condição para alterar Registros
				if ((acao != null) && (acao.equals("alterar"))) {
					try {
						conta = contaDao.consultar(Long.valueOf(request.getParameter("idConta")));
						if (conta == null){
							request.setAttribute("erro","Erro ao localizar para alteração");
						}else {
							clientes = clienteDao.listar();
							agencias = agenciaDao.listar();
							request.setAttribute("conta", conta);
							request.setAttribute("clientes", clientes);
							request.setAttribute("agencias", agencias);
							pagina = "/conta/incluir.jsp";
						}
					} catch (NumberFormatException e) {
						request.setAttribute("erro", "Erro na alteração");
					}
				}
	
				// Condição para salvar os Registros
				if ((acao != null) && (acao.equals("salvar"))) {
					try {
							if (request.getParameter("tipoConta") != ""){
								conta.setTipoConta((request.getParameter("tipoConta")));
								
							if (request.getParameter("idConta") != "" && !request.getParameter("idConta").equals("")){
								conta.setIdConta(Long.parseLong(request.getParameter("idConta")));
							}
							
							if (request.getParameter("operacao") != "" ){
								conta.setOperacao(Long.parseLong((request.getParameter("operacao"))));
							}
							
							if (request.getParameter("conta") != "" ){
								conta.setConta(Long.parseLong((request.getParameter("conta"))));
							}
							
							if (request.getParameter("agencia") != "" ){
								conta.getAgencia().setIdAgencia(Long.valueOf((request.getParameter("agencia"))));
							}
							
							if (request.getParameter("cliente") != "" ){
								conta.getCliente().setIdCliente(Long.valueOf((request.getParameter("cliente"))));
							}
							
							}else{
								request.setAttribute("erro", "Preencha os dados corretamente!");
							}
							
						// Salva: inclui ou altera
						if (request.getParameter("idConta") == null || request.getParameter("idConta").equals("")) {
							if (contaDao.incluir(conta) > 0)
								request.setAttribute("mensagem","Conta cadastrada com sucesso!");
							else
								request.setAttribute("erro", "Erro ao Cadastrar!");
							
						} else {
							conta.setIdConta((Long.parseLong(request.getParameter("idConta"))));
							if (contaDao.alterar(conta) > 0)
								request.setAttribute("mensagem","Conta alterada com sucesso");
							else
								request.setAttribute("erro","Erro de alteração");
						}
						contas = contaDao.listar();
						request.setAttribute("contas", contas);
						pagina = "/conta/listar.jsp";
	
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("erro", "E necessario colocar os dados!" + e.getMessage());
						contas = contaDao.listar();
						request.setAttribute("contas", contas);
						pagina = "/conta/incluir.jsp";
					}
	
				}
			} catch (SQLException e) {
				request.setAttribute("erro", "Erro de banco de dados" + e.getMessage());
				pagina = "/conta/listar.jsp";
			}
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}
