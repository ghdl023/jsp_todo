package todo.domain;

import java.time.LocalDate;

public class TodoVO {
	private Long tno;
	private String title;
	private LocalDate dueDate;
	private String finished;
	
	
	public TodoVO() {
		super();
	}
	
	public TodoVO(Long tno, String title, LocalDate dueDate, String finished) {
		super();
		this.tno = tno;
		this.title = title;
		this.dueDate = dueDate;
		this.finished = finished;
	}
	public Long getTno() {
		return tno;
	}
	public void setTno(Long tno) {
		this.tno = tno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getFinished() {
		return finished;
	}
	public void setFinished(String finished) {
		this.finished = finished;
	}
	@Override
	public String toString() {
		return "TodoDTO [tno=" + tno + ", title=" + title + ", dueDate=" + dueDate + ", finished=" + finished + "]";
	}
	
}
