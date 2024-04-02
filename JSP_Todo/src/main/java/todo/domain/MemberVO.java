package todo.domain;

public class MemberVO {
	private String mid;
	private String mpw;
	private String mname;
	private String uuid;
	
	
	public MemberVO() {
		super();
	}


	public MemberVO(String mid, String mpw, String mname, String uuid) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.uuid = uuid;
	}


	public String getMid() {
		return mid;
	}



	public void setMid(String mid) {
		this.mid = mid;
	}



	public String getMpw() {
		return mpw;
	}



	public void setMpw(String mpw) {
		this.mpw = mpw;
	}



	public String getMname() {
		return mname;
	}



	public void setMname(String mname) {
		this.mname = mname;
	}


	

	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	@Override
	public String toString() {
		return "MemberVO [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + "]";
	}
	
	
	
}
