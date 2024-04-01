package todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import todo.domain.MemberVO;

public class MemberDAO {
	public MemberVO getWithPassword(String mid, String mpw) throws Exception {
		
		String sql = "select * from tbl_member where mid = ? and mpw = ?";
		
		MemberVO vo = null;
		
		try(Connection conn = ConnectionUtil.INSTANCE.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setMid(rs.getString("mid"));
				vo.setMpw(rs.getString("mpw"));
				vo.setMname(rs.getString("mname"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
}
