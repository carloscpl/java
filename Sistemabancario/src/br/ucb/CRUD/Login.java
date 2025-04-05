package br.ucb.CRUD;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ucb.Bean.Usuario;
import br.ucb.DAO.UsuarioDAO;



@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		String acao=request.getParameter("acao"), pagina=null;
		Usuario usuario = new Usuario();
		
		try {
			if (acao == null) {
				String nomeUsuario = request.getParameter("usuario");
				String senha = request.getParameter("senha");
				usuario = new UsuarioDAO().autenticar(nomeUsuario, senha);
				
				if (usuario != null){
					session.setAttribute("usuarioLogado", usuario);
					request.setAttribute("mensagem", "Usuario logado");
					pagina = "/menu.jsp";
				}
				else {
					request.setAttribute("erro", "Usuario deslogado");
					pagina = "/login.jsp";
				}
			}
			
			if ((acao != null) && (acao.equals("logoff"))) {
				session.removeAttribute("usuarioLogado");
				request.setAttribute("mensagem", "Obrigado, pelo acesso");
				pagina = "/login.jsp";
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro de banco de dados");
			pagina = "/login.jsp";
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession(true);
		session.invalidate();
		dispatcher = request.getRequestDispatcher( "/login.jsp");
		dispatcher.forward(request, response);
	}
}
