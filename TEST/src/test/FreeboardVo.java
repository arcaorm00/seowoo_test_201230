package test;

import java.util.Date;

public class FreeboardVo {

	private String code_type;
	private int num;
	private String name;
	private String title;
	private String content;
	private Date regdate;
	
	public FreeboardVo() {
		super();
	}
	
	public FreeboardVo(String code_type, int num, String name, String title, String content, Date regdate) {
		super();
		this.code_type = code_type;
		this.num = num;
		this.name = name;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}
	
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "[CODE_TYPE: " + code_type + ", NUM: " + num + ", NAME: " + name + ", TITLE: " + title
				+ ", CONTENT: " + content + ", REGDATE: " + regdate + "]";
	}
	
	
}
