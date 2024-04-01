package todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dto.TodoDTO;
import todo.service.TodoService;

@WebServlet("/todo/modify")
public class TodoModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TodoService todoService = TodoService.INSTANCE;
	private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
    public TodoModifyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long tno = Long.parseLong(request.getParameter("tno"));
		
		TodoDTO dto;
		try {
			dto = todoService.getOne(tno);
			request.setAttribute("dto", dto);
			
			request.getRequestDispatcher("/views/todo/modify.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String finishedStr =request.getParameter("finished");
		
		TodoDTO dto = new TodoDTO();
		dto.setTno(Long.parseLong(request.getParameter("tno")));
		dto.setTitle(request.getParameter("title"));
		dto.setDueDate(LocalDate.parse(request.getParameter("dueDate"), DATEFORMATTER));
		dto.setFinished(finishedStr != null && finishedStr.equals("on") ? "Y" : "N");
		
		try {
			todoService.modify(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/todo/detail?tno=" + dto.getTno());
	}

}
