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

import br.ucb.Bean.Usuario;
import br.ucb.DAO.UsuarioDAO;
import br.ucb.enumerador.Perfil;


/**
 * Servlet implementation class UsuarioCRUD
 */
@WebServlet("/UsuarioCRUD")
public class UsuarioCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
	    HttpSession session = request.getSession(true);
		String acao=request.getParameter("acao"), pagina=null;
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDao;
		List <Usuario> usuarios = null;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		if (usuarioLogado == null) {
			request.setAttribute("erro", "Necessário logar para ter acesso");
			pagina = "/login.jsp";
		}
		else {
			try {
				usuarioDao = new UsuarioDAO();
				if ((acao == null) || (acao.equals("listar"))) {
					usuarios = usuarioDao.listar(usuario);
					request.setAttribute("usuarios", usuarios);
					pagina = "/usuario/usuarioLista.jsp";
				}
				
				// Condição para filtrar Registros
				if ((acao != null) && (acao.equals("filtrar"))) {
					usuarios = usuarioDao.listar(usuario,request.getParameter("nomeFiltro"));
					request.setAttribute("usuarios", usuarios);
					pagina = "/usuario/usuarioLista.jsp";
				}
				
				if ((acao != null) && (acao.equals("incluir"))) {
					if (usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) {
						usuarios = usuarioDao.listar(usuario);
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/usuario/usuarioLista.jsp";
					}
					else {
						request.setAttribute("usuario", usuario);
						request.setAttribute("perfis", Perfil.USUARIO);
						pagina = "/usuario/usuarioEntrada.jsp";
					}
				}
				if ((acao != null) && (acao.equals("alterar"))) {
					Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));
					if ((usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) && (usuarioLogado.getIdUsuario() != idUsuario)) {
						usuarios = usuarioDao.listar(usuario);
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/usuario/usuarioLista.jsp";
					}
					else {
						usuario = usuarioDao.consultar(idUsuario);
						request.setAttribute("usuario", usuario);
						request.setAttribute("perfis", Perfil.USUARIO);
						pagina = "/usuario/usuarioEntrada.jsp";
					}
				}
			
				if ((acao != null) && (acao.equals("salvar"))) {
					if (usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) {
						usuarios = usuarioDao.listar(usuario);
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/usuario/usuarioLista.jsp";
					}
					else {
						try {
							// Recebe valores do formulario
							if (request.getParameter("nome") != null)
								usuario.setNome(request.getParameter("nome"));
							if (request.getParameter("usuario") != null)
								usuario.setUsuario(request.getParameter("usuario"));
							if (request.getParameter("senha") != null)
								usuario.setSenha(request.getParameter("senha"));
							if (request.getParameter("perfil") != null)
								usuario.setPerfil(Perfil.valueOf(request.getParameter("perfil")));
							
							// Salva: inclui ou alterar
							if (request.getParameter("idUsuario") == null || request.getParameter("idUsuario").equals("")) { // Incluir
								if (usuarioDao.consultar(usuario.getUsuario()) == null ) {
									if (usuarioDao.incluir(usuario) > 0)
										request.setAttribute("mensagem", "Usuario cadastrado com sucesso");
									else
										request.setAttribute("erro", "Erro de inclusão");
									usuarios = usuarioDao.listar(usuario);
									request.setAttribute("usuarios", usuarios);
									pagina = "/usuario/usuarioLista.jsp";
								}
								else {
									request.setAttribute("erro", "Usuario já existe");
									request.setAttribute("perfis", Perfil.USUARIO);
									pagina = "/usuario/usuarioEntrada.jsp";		
								}
							} else { // Alterar
								usuario.setIdUsuario(Long.valueOf(request.getParameter("idUsuario")));
								if (usuarioDao.alterar(usuario) > 0)
									request.setAttribute("mensagem", "Usuario alterado com sucesso");
								else
									request.setAttribute("erro", "Erro de alteração");
								usuarios = usuarioDao.listar(usuario);
								request.setAttribute("usuarios", usuarios);
								pagina = "/usuario/usuarioLista.jsp";
							}
						} catch (Exception e) {
							System.out.println(e);
							e.printStackTrace();
							request.setAttribute("erro", "Erro de conversao");
							request.setAttribute("perfis", Perfil.USUARIO);
							pagina = "/usuario/usuarioEntrada.jsp";		
						}
					}
				}
				
				// Função para exclusão de registros
				if ((acao != null) && (acao.equals("excluir"))) {
					if (usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) {
						usuarios = usuarioDao.listar(usuario);
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/usuario/usuarioLista.jsp";
					}
					else {
						usuario = usuarioDao.consultar(Long.valueOf(request.getParameter("idUsuario")));
						if (usuarioDao.excluir(usuario) > 0)
							request.setAttribute("mensagem", "Usuario excluído com sucesso");
						else
							request.setAttribute("erro", "Erro de exclusão");
						    usuarios = usuarioDao.listar(usuario);
						    request.setAttribute("usuarios", usuarios);
						    pagina = "/usuario/usuarioLista.jsp";
						    
					}
				}
				if ((acao != null) && (acao.equals("informarSenha"))) {
					Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));
					if ((usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) && (usuarioLogado.getIdUsuario() != idUsuario)) {
						usuarios = usuarioDao.listar(usuario);
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/usuario/usuarioLista.jsp";
					}
					else {
						usuario = usuarioDao.consultar(idUsuario);
						request.setAttribute("usuario", usuario);
						pagina = "/trocaSenha.jsp";
					}
				}
				if ((acao != null) && (acao.equals("trocarSenha"))) {
					Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));
					if ((usuarioLogado.getPerfil() != Perfil.ADMINISTRADOR) && (usuarioLogado.getIdUsuario() != idUsuario)) {
						usuarios = usuarioDao.listar(usuario);
						request.setAttribute("usuarios", usuarios);
						request.setAttribute("erro", "Funcionalidade não acessível para seu perfil");
						pagina = "/usuario/usuarioLista.jsp";
					}
					else {
						usuario = usuarioDao.consultar(Long.valueOf(request.getParameter("idUsuario")));
						String senha = request.getParameter("senha1");
						String novaSenha1 = request.getParameter("senha2");
						String novaSenha2 = request.getParameter("novaSenha");
						if (!novaSenha1.equals(novaSenha2)) {
							request.setAttribute("erro", "Novas senhas informadas com valores diferentes");
							pagina = "/trocaSenha.jsp";
						}
						else {
							if (!usuario.validarSenha(senha)) {
								request.setAttribute("erro", "Senha nao confere");
								pagina = "/trocaSenha.jsp";
							}
							else {
								usuario.setSenha(novaSenha2);
								usuarioDao.trocaSenha(usuario);
								request.setAttribute("mensagem", "Senha alterada com sucesso");
								usuarios = usuarioDao.listar(usuario);
								request.setAttribute("usuarios", usuarios);
								pagina = "/usuario/usuarioLista.jsp";
							}
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("erro", "Erro de banco de dados");
				pagina = "/usuario/usuarioLista.jsp";
			}
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}
}
