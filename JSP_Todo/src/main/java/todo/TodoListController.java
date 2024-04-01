package todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.dto.TodoDTO;
import todo.service.TodoService;

@WebServlet("/todo/list")
public class TodoListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<TodoDTO> list;
		try {
			list = TodoService.INSTANCE.getList();
			
			request.setAttribute("list", list);
			
			response.setContentType("text/html;charset=UTF-8");
			
			request.getRequestDispatcher("/views/todo/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
