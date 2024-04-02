package todo;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.dto.MemberDTO;
import todo.service.MemberService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final MemberService memberService = MemberService.INSTANCE;
	
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String auto = request.getParameter("auto");
		
		boolean rememberMe = auto != null && auto.equals("on");
		

		MemberDTO memberDTO;
		try {
			memberDTO = memberService.login(mid, mpw);
			
			if(rememberMe) {
				String uuid = UUID.randomUUID().toString();
				memberService.updateUuid(mid, uuid);
				memberDTO.setUuid(uuid);
				
				Cookie rememberCookie = new Cookie("remember-me", uuid);
				rememberCookie.setMaxAge(60*60*24*7);
				rememberCookie.setPath("/");
				
				response.addCookie(rememberCookie);
			}
			

			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", memberDTO);
			
			response.sendRedirect(request.getContextPath() + "/todo/list");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/login?result=error");
		}
		
		

	}

}
