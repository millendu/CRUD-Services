package edu.asu.cse564.CRUD_Gradebook_Client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;


public class ClientApp 
{
	private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/crudservices";

    public ClientApp() {
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("/");
    }
    
    //Creating student by giving id and name
    public ClientResponse createStudent(String sid, String name) throws UniformInterfaceException{
    	Form params = new Form();
    	params.add("id", sid);
    	params.add("name", name);
    	return webResource.path("Gradebook/Student").post(ClientResponse.class, params);
    }
    
    //Creating gradeitem by giving id and name
    public ClientResponse createGradeItem(String gid, String weightage) throws UniformInterfaceException{
    	Form params = new Form();
    	params.add("id", gid);
    	params.add("weightage", weightage);
    	return webResource.path("Gradebook/GradeItem").post(ClientResponse.class, params);
    }

    //Getting student based on the id
    public ClientResponse getStudent(String id) throws UniformInterfaceException {
        return webResource.path("Gradebook/Student").path(id).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }
    
    //Getting the entire gradebook
    public ClientResponse getGradeBook() throws UniformInterfaceException {
        return webResource.path("Gradebook").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }
    
    //Updating grade by giving student id, grade id, grade and feedback if any
    public ClientResponse updateGrade(String sid, String gid, String grade, String feedback) throws UniformInterfaceException {
    	Form params = new Form();
    	params.add("sid", sid);
    	params.add("gid", gid);
    	params.add("grade", grade);
    	params.add("feedback", feedback);
    	return webResource.path("Gradebook/Student").put(ClientResponse.class, params);
    }

    //Delete student based on the student id
    public ClientResponse deleteStudent(String id) throws UniformInterfaceException {
        return webResource.path("Gradebook/Student").path(id).delete(ClientResponse.class);
    }
    
    //Delete gradeitem based on grade id
    public ClientResponse deleteGradeItem(String id) throws UniformInterfaceException {
        return webResource.path("Gradebook/GradeItem").path(id).delete(ClientResponse.class);
    }
    
    //Delete grade for a particular student
    public ClientResponse deleteGrade(String sid, String gid) throws UniformInterfaceException {
    	Form params = new Form();
    	params.add("sid", sid);
    	params.add("gid", gid);
        return webResource.path("Gradebook/Student").delete(ClientResponse.class, params);
    }

    public void close() {
        client.destroy();
    }
    
public static void main(String[] args){
		
	ClientApp g = new ClientApp();
	ClientResponse r = g.createStudent("2", "Vamshi");
	g.createStudent("1", "Manideep");
	System.out.println(g.createStudent("1", "Manideep").toString());
	System.out.println(r.toString());
	ClientResponse r2 = g.createGradeItem("1", "20");
	System.out.println(r2.toString());

	System.out.println(g.getStudent("2").toString());
	System.out.println(g.getStudent("1").toString());
	g.updateGrade("1"," 1", "98", "Good");
	System.out.println(g.getStudent("1").toString());
	System.out.println(g.getStudent("2").toString());
	System.out.println(g.getGradeBook().toString());

		
	}


}
