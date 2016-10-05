package edu.asu.cse564.CRUD_Gradebook_millendu_Eclipse;

import java.util.List;

public class Student {
	
	public String name;
	public String id;
	public List<GradeItem> gradeItems;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<GradeItem> getGradeItems() {
		return gradeItems;
	}
	public void setGradeItems(List<GradeItem> gradeItems) {
		this.gradeItems = gradeItems;
	}

}
