package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dto.TodoDTO;
import todo.service.TodoService;

@WebServlet("/todo/detail")
public class TodoDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoDetailController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long tno = Long.parseLong(request.getParameter("tno"));
		
		TodoDTO todoDTO;
		try {
			todoDTO = TodoService.INSTANCE.getOne(tno);
	
			request.setAttribute("dto", todoDTO);
			
			// 쿠키 찾기
			Cookie viewTodoCookie = findCookie(request.getCookies(), "viewTodos");
			String todoListStr = viewTodoCookie.getValue();
			boolean exist = false;
			
			if(todoListStr != null && todoListStr.indexOf(tno+"-") > -1) {
				exist = true;
			}
			
			System.out.println("exist : " + exist);
			
			if(!exist) {
				todoListStr += tno + "-";
				viewTodoCookie.setValue(todoListStr);
				viewTodoCookie.setMaxAge(60*60*24);
				viewTodoCookie.setPath("/");
				response.addCookie(viewTodoCookie);
			}
			
			
			request.getRequestDispatcher("/views/todo/detail.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private Cookie findCookie(Cookie[] cookies, String cookieName ) {
		Cookie targetCookie = null;
		
		if(cookies != null && cookies.length >0) {
			for(Cookie c : cookies) {
				if(c.getName().equals(cookieName)) {
					targetCookie = c;
					break;
				}
			}
		}
		
		if(targetCookie == null) {
			targetCookie = new Cookie(cookieName, "");
			targetCookie.setPath("/");
			targetCookie.setMaxAge(60*60*24);
		}
		
		return targetCookie;
	}
}
