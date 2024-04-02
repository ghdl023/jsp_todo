package todo.service;

import todo.dao.MemberDAO;
import todo.domain.MemberVO;
import todo.dto.MemberDTO;

public enum MemberService {
	INSTANCE;
	
	private MemberDAO dao;
	
	MemberService() {
		dao = new MemberDAO();
	}
	
	
	public MemberDTO login(String mid, String mpw) throws Exception {
		MemberVO vo = dao.getWithPassword(mid, mpw);
		
		MemberDTO dto = new MemberDTO();
		dto.setMid(vo.getMid());
		dto.setMpw(vo.getMpw());
		dto.setMname(vo.getMname());
		dto.setUuid(vo.getUuid());
		
		return dto;
	}
	
	public MemberDTO selectByUuid(String uuid) throws Exception {
		MemberVO vo = dao.selectByUuid(uuid);
		
		MemberDTO dto = new MemberDTO();
		dto.setMid(vo.getMid());
		dto.setMpw(vo.getMpw());
		dto.setMname(vo.getMname());
		dto.setUuid(uuid);
		
		return dto;
	}
	
	public void updateUuid(String mid, String uuid) throws Exception {
		
		dao.updateUuid(mid, uuid);
	}
}
