package todo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import todo.dao.TodoDAO;
import todo.domain.TodoVO;
import todo.dto.TodoDTO;

public enum TodoService {
	INSTANCE;
	
	private TodoDAO dao;
	
	TodoService() {
		dao = new TodoDAO();
	}
	
	
	public void register(TodoDTO todoDTO) throws Exception {
		TodoVO todoVO = new TodoVO();
		todoVO.setTno(todoDTO.getTno());
		todoVO.setTitle(todoDTO.getTitle());
		todoVO.setDueDate(todoDTO.getDueDate());
		todoVO.setFinished(todoDTO.getFinished());
		
		dao.insert(todoVO);
	}
	
	public List<TodoDTO> getList() throws Exception {
		
		List<TodoVO> voList = dao.selectAll();
		
		List<TodoDTO> dtoList = voList.stream()
				.map(vo -> new TodoDTO(vo.getTno(), vo.getTitle(), vo.getDueDate(), vo.getFinished()))
				.collect(Collectors.toList());
		
		return dtoList;
	}
	
	public TodoDTO getOne(Long tno) throws Exception{
		
		TodoVO todoVO = dao.selectOne(tno);
		
		TodoDTO dto = new TodoDTO();
		dto.setTno(todoVO.getTno());
		dto.setDueDate(todoVO.getDueDate());
		dto.setTitle(todoVO.getTitle());
		dto.setFinished(todoVO.getFinished());
		return dto;
	}
	
	public void modify(TodoDTO todoDTO) throws Exception {
		dao.updateOne(todoDTO);
	}
	
	public void delete(Long tno) throws Exception {
		dao.deleteOne(tno);
	}
}
