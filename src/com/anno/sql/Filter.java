package com.anno.sql;

@Table("department")
public class Filter {

	@Column("depart_id")
	private int departId;
	
	@Column("depart_name")
	private String departName;
	
	@Column("teacher")
	private String teacher;
	
	@Column("stu_count")
	private int stuCount;

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public int getStuCount() {
		return stuCount;
	}

	public void setStuCount(int stuCount) {
		this.stuCount = stuCount;
	}
	
}
