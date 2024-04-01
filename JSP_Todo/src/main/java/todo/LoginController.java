package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		

		MemberDTO memberDTO;
		try {
			memberDTO = memberService.login(mid, mpw);

			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", memberDTO);
			
			response.sendRedirect(request.getContextPath() + "/todo/list");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/login?result=error");
		}
		
		

	}

}
