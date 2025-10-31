package com.ksingh.studentapp;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;


/*
 * Create a Student
curl -X POST -H "Content-Type: application/json" -d '{"name":"John", "age":20,
"major":"Computer Science"}' http://localhost:8080/YourApp/students

List All Students
curl -X GET http://localhost:8080/YourApp/students

Get a Specific Student
curl -X GET http://localhost:8080/YourApp/students/1

Update a Student
curl -X PUT -H "Content-Type: application/json" -d '{"name":"John Doe", "age":21,
"major":"Software Engineering"}' http://localhost:8080/YourApp/students/1

Delete a Student
curl -X DELETE http://localhost:8080/YourApp/students/1
 */

@Path("/students")
public class StudentController {
	private StudentDAO studentDAO = new StudentDAO();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents() {
		return studentDAO.readAll();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student createStudent(Student student) {
		return studentDAO.create(student);
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("id") int id) {
		return studentDAO.read(id);
	}
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("id") int id, Student student) {
		return studentDAO.update(id, student);
	}
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteStudent(@PathParam("id") int id) {
		studentDAO.delete(id);
	}
}