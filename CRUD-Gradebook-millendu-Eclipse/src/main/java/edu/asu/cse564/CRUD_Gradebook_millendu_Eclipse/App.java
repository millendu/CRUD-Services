package edu.asu.cse564.CRUD_Gradebook_millendu_Eclipse;



import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

@Path("/")
public class App {
	
	public static GradeBook gradebook;
	
	@Context
    private static UriInfo context;
	 
	//Method for creating student
	@POST
	@Path("/Gradebook/Student")
	public static Response createStudent(@FormParam("id") String sid, @FormParam("name") String name) {
		
		if(sid.equals("") || name.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Enter student details").build();
		}
		try {
			Student st = new Student();
			st.setId(sid);
			st.setName(name);
			
			if(gradebook == null){
				gradebook = new GradeBook();
				List<Student> students = new ArrayList<Student>();
				students.add(st);
				gradebook.setStudents(students);
			}
			else{
				List<Student> tempStudents = gradebook.getStudents();
				for(Student s: tempStudents)
				{
					if(s.getId().equals(st.getId()))
						return Response.status(Response.Status.BAD_REQUEST).entity("Student with the id exists").build();
				}
				Student std = tempStudents.get(0);
				
				List<GradeItem> gradeItems = std.getGradeItems();
				
				if(gradeItems != null) {
					List<GradeItem> newGradeItems = new ArrayList<GradeItem>();
	 				for(GradeItem g : gradeItems){
	 					g.setFeedback(null);
	 					g.setGrade(null);
						newGradeItems.add(g);
					}
	 				st.setGradeItems(newGradeItems);
				}
 				tempStudents.add(st);
 				gradebook.setStudents(tempStudents);
			}
			String output = new ObjectMapper().writeValueAsString(st);
			//URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(st.getId()));
			return Response.status(Response.Status.CREATED).entity(output).build();
		} catch (JsonParseException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (JsonMappingException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (IOException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	//Methode for creating gradeItem
	@POST
	@Path("/Gradebook/GradeItem")
	public static Response createGradeItem(@FormParam("id") String gid, @FormParam("weightage") String weightage  ) {
		
		GradeItem gt = null;
		
		if(gid.equals("") || weightage.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Enter gradeitem details").build();
		}
		try {
			if(gradebook == null){
				return Response.status(Response.Status.BAD_REQUEST).entity("Grade Book is empty").build();
			}
			else{
				List<Student> tempStudents = gradebook.getStudents();
				for(Student s: tempStudents)
				{
					if(s.getGradeItems() != null) {
						for(GradeItem g : s.getGradeItems())
						{
							if(g.getId().equals(gid))
								return Response.status(Response.Status.BAD_REQUEST).entity("Grade Item already exists").build();
						}
						gt = new GradeItem();
						gt.setId(gid);
						gt.setWeightage(weightage);
						s.getGradeItems().add(gt);
					}
					else {
						List<GradeItem> gradeItems = new ArrayList<GradeItem>();
						gt = new GradeItem();
						gt.setId(gid);
						gt.setWeightage(weightage);
						gradeItems.add(gt);
						s.setGradeItems(gradeItems);
					}
				}
			}
			String output = new ObjectMapper().writeValueAsString(gt);
			//URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(gt.getId()));
			//System.out.println("I am here");
			return Response.status(Response.Status.CREATED).entity(output).build();
	            
		}catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	//Method for deleting student
	@DELETE
	@Path("/Gradebook/Student/{id}")
	public static Response deleteStudent(@PathParam("id") String id) {
		
		if(id.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Enter student id").build();
		}
		if(gradebook == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Grade Book is empty").build();
		}
		List<Student> students = gradebook.getStudents();
		boolean flag = false;
		for(Student s : students) {
			if(s.getId().equals(id)) {
				students.remove(s);
				flag = true;
				break;
			}
		}
		if(flag == false)
			return Response.status(Response.Status.BAD_REQUEST).entity("Student id doesnt exist").build();
		gradebook.setStudents(students);
		
		
		//URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(id));
		return Response.status(Response.Status.OK).entity("Student deleted").build();
	}

	//Method for deleting gradeItem for all students
	@DELETE
	@Path("/Gradebook/GradeItem/{id}")
	public static Response deleteGradeItem(@PathParam("id") String id) {
		
		if(id.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Enter grade item id").build();
		}
		if(gradebook == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Grade Book is empty").build();
		}
		List<Student> students = gradebook.getStudents();
		boolean flag = false;
		for(Student s : students) {
			List<GradeItem> gradeItems = s.getGradeItems();
			
			for(GradeItem g : gradeItems) {
				if(g.getId().equals(id)) {
					gradeItems.remove(g);
					flag = true;
					break;
				}
			}
			if(flag == false)
				return Response.status(Response.Status.BAD_REQUEST).entity("Grade id doesnt exist").build();
			s.setGradeItems(gradeItems);
		}
		gradebook.setStudents(students);
		//URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(id));
		return Response.status(Response.Status.OK).entity("GradeItem deleted").build();

	}

	//Method for deleting grade for particular student
	@DELETE
	@Path("/Gradebook/Student")
	public static Response deleteGrade(@FormParam("id") String sid , @FormParam("id") String gid) {
		if(gid.equals("") || sid.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Enter grade to delete").build();
		}
		if(gradebook == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Grade Book is empty").build();
		}
		List<Student> students = gradebook.getStudents();
		
		boolean flagGrade = false;
		boolean flagStudent = false;
		for(Student s : students) {
			if(s.getId().equals(sid)) {
				List<GradeItem> gradeItems = s.getGradeItems();
				for(GradeItem g : gradeItems) {
					if(g.getId().equals(gid)) {
						g.setGrade(null);
						g.setFeedback(null);
						flagGrade = true;
						flagStudent = true;
					}
				}
				if(flagGrade == false) {
					return Response.status(Response.Status.BAD_REQUEST).entity("Grade id doesnt exist").build();
				}
				s.setGradeItems(gradeItems);
			}
		}
		if(flagStudent == false)
			return Response.status(Response.Status.BAD_REQUEST).entity("Student id doesnt exist").build();
		gradebook.setStudents(students);
		//URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(sid) + Integer.toBinaryString(gid));
		return Response.status(Response.Status.OK).entity("Student grade updated").build();
	}
	
	//Method for updating grade for particular student
	@PUT
	@Path("/Gradebook/Student")
	public static Response updateGrade(@FormParam("sid") String sid, @FormParam("gid") String gid, @FormParam("grade") String grade, @FormParam("feedback") String feedback) {
		if(gid.equals("") || sid.equals("") || grade.equals("") || feedback.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Enter grade to update").build();
		}
		if(gradebook == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Grade Book is empty").build();
		}
		List<Student> students = gradebook.getStudents();
		
		boolean flagGrade = false;
		boolean flagStudent = false;
		for(Student s : students) {
			if(s.getId().equals(sid)) {
				List<GradeItem> gradeItems = s.getGradeItems();
				for(GradeItem g : gradeItems) {
					if(g.getId().equals(gid)) {
						g.setGrade(grade);
						g.setFeedback(feedback);
						flagGrade = true;
						flagStudent = true;
					}
				}
				if(flagGrade == false) {
					return Response.status(Response.Status.BAD_REQUEST).entity("Grade id doesnt exist").build();
				}
				s.setGradeItems(gradeItems);
			}
		}
		if(flagStudent == false){
			return Response.status(Response.Status.BAD_REQUEST).entity("Student id doesnt exist").build();
		}gradebook.setStudents(students);
		//URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(sid) + Integer.toBinaryString(gid));
		return Response.status(Response.Status.OK).build();
	}
	
	//Retrieving the information for particular student
	@GET
	@Path("/Gradebook/Student/{id}")
	public static Response getStudent(@PathParam("id") String sid) {
		
		if(sid.equals("")) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Enter student id").build();
		}
		List<Student> students = gradebook.getStudents();
		Student student = null;
		for(Student s : students) {
			if(s.getId().equals(sid)) {
				student = s;
				break;
			}
		}
		if(student == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Student id doesnt exist").build();
		}
		else {
			try {
				String output = new ObjectMapper().writeValueAsString(student);
				//URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(st.getId()));
				return Response.status(Response.Status.OK).entity(output).build();
			} catch (JsonParseException e) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} catch (JsonMappingException e) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			} catch (IOException e) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			
		}
	}
	
	//Retriveing the entire gradebook
	@GET
	@Path("/Gradebook")
	public static Response getGradeBook() {
		try {
			String output = new ObjectMapper().writeValueAsString(gradebook);
			//URI locationURI = URI.create(context.getAbsolutePath() + "/" + Integer.toString(st.getId()));
			return Response.status(Response.Status.OK).entity(output).build();
		} catch (JsonParseException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (JsonMappingException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} catch (IOException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
public static void main(String[] args){
		
		App g = new App();
		Response r = g.createStudent("2", "Vamshi");
		g.createStudent("1", "Manideep");
		System.out.println(r.getEntity().toString());
		Response r2 = g.createGradeItem("1", "20");
		System.out.println(r2.getEntity().toString());

		System.out.println(g.getStudent("").getEntity().toString());
		System.out.println(g.getStudent("1").getEntity().toString());
		g.updateGrade("1", "1", "98", "Good");
		System.out.println(g.getStudent("1").getEntity().toString());
		System.out.println(g.getStudent("2").getEntity().toString());
		System.out.println(g.getGradeBook().getEntity().toString());
		

	}
	
	
}