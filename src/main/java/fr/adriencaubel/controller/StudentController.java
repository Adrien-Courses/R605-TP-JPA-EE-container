package fr.adriencaubel.controller;

import fr.adriencaubel.entity.Student;
import fr.adriencaubel.service.StudentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class StudentController {

    @Inject
    private StudentService studentService;

    @POST
    public Response createStudent(StudentRequestModel student) {
        studentService.addStudent(student);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") Long id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return Response.ok(student).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        // return List<StudentResponseModel>
        return Response.ok(students).build();
    }

    @PUT
    public Response updateStudent(StudentRequestModel student) {
        studentService.updateStudent(student);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.ok().build();
    }
}