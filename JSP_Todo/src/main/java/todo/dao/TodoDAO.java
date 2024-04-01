package todo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import todo.domain.TodoVO;
import todo.dto.TodoDTO;

public class TodoDAO {

	
	public void insert(TodoVO vo) throws Exception {
		String sql = "insert into tbl_todo values (seq_tno.nextval, ?, ?, 'N')";
		
		try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
				PreparedStatement pstmt = conn.prepareCall(sql)) {
			pstmt.setString(1, vo.getTitle());
			pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<TodoVO> selectAll() throws Exception {
		String sql = "select * from tbl_todo";
		
		
		
		List<TodoVO> list = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
				PreparedStatement pstmt = conn.prepareCall(sql);
				ResultSet rs = pstmt.executeQuery();
				) {
			
			while(rs.next()) {
				TodoVO vo = new TodoVO();
				vo.setTno(rs.getLong("tno"));
				vo.setTitle(rs.getString("title"));
				vo.setDueDate(rs.getDate("dueDate").toLocalDate());
				vo.setFinished(rs.getString("finished"));
				
				list.add(vo);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public TodoVO selectOne(Long tno) throws Exception {
		String sql = "select * from tbl_todo where tno = ?";
		
		TodoVO vo = null;
		
		try(Connection conn = ConnectionUtil.INSTANCE.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setLong(1, tno);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new TodoVO();
				vo.setTno(tno);
				vo.setTitle(rs.getString("title"));
				vo.setDueDate(rs.getDate("dueDate").toLocalDate());
				vo.setFinished(rs.getString("finished"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public void deleteOne(Long tno) throws Exception {
		String sql = "delete from tbl_todo where tno = ?";
		
		try(Connection conn = ConnectionUtil.INSTANCE.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, tno);
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateOne(TodoDTO todoDTO) throws Exception {
		String sql = "update tbl_todo set title = ?, dueDate = ?, finished = ? where tno = ?";
		
		try(Connection conn = ConnectionUtil.INSTANCE.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, todoDTO.getTitle());
			pstmt.setDate(2, Date.valueOf(todoDTO.getDueDate()));
			pstmt.setString(3, todoDTO.getFinished());
			pstmt.setLong(4, todoDTO.getTno());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
