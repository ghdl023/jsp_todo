package todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.dto.TodoDTO;
import todo.service.TodoService;

@WebServlet("/todo/register")
public class TodoRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoService todoService = TodoService.INSTANCE;
	private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       
    public TodoRegisterController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("입력화면을 볼 수 있또록 구성");
		
		HttpSession session = request.getSession();
		
		if(session.isNew()) { // 기존에 JSESSIONID가 없는 새로운 사용자
			response.sendRedirect(request.getContextPath() + "/login");
			return;
			
		}
		
		request.getRequestDispatcher("/views/todo/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("입력을 처리하고 목록 페이지로 이동");
		
		TodoDTO todoDTO = new TodoDTO();
		todoDTO.setTitle(request.getParameter("title"));
		todoDTO.setDueDate(LocalDate.parse(request.getParameter("dueDate"), DATEFORMATTER));

		try {
			todoService.register(todoDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/todo/list");
	}

}
