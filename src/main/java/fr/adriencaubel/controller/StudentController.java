package fr.adriencaubel.controller;

import fr.adriencaubel.entity.Student;
import fr.adriencaubel.service.StudentService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

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
    
    /**
     * Dans cette application nous séparons pour bien illustrer les concepts
     * - le getAllStudents
     * - le getAllStudentsPagination
     * - le getAllStudentsSearch
     * 
     * Mais normalement, un seul endpoint serait développé
     */
    @GET
    public Response getAllStudents() {
        List<Student> students = studentService.getAllStudents();

        // Préférer utiliser des DTO
        JsonObject response = Json.createObjectBuilder()
                .add("students", Json.createArrayBuilder(
                        students.stream().map(s -> Json.createObjectBuilder()
                                .add("id", s.getId())
                                .add("name", s.getName())
                                .add("age", s.getAge())
                                .build())
                                .collect(Collectors.toList()))
                )
                .build();

        return Response.ok(response).build();
    }
    
    @GET
    @Path("/only-pagination")
    public Response getAllStudentsPagination(@QueryParam("page") @DefaultValue("0") int page,
                                   			@QueryParam("size") @DefaultValue("10") int size) {
        List<Student> students = studentService.getStudentsPaginated(page, size);
        long total = studentService.getTotalStudentsCount();

        // Préférer utiliser des DTO
        JsonObject response = Json.createObjectBuilder()
                .add("total", total)
                .add("page", page)
                .add("size", size)
                .add("students", Json.createArrayBuilder(
                        students.stream().map(s -> Json.createObjectBuilder()
                                .add("id", s.getId())
                                .add("name", s.getName())
                                .add("age", s.getAge())
                                .build())
                                .collect(Collectors.toList()))
                )
                .build();

        return Response.ok(response).build();
    }
    
    @GET
    @Path("/only-pagination-with-join")
    public Response getAllStudentsPaginationWithJoin(@QueryParam("page") @DefaultValue("0") int page,
                                   			@QueryParam("size") @DefaultValue("10") int size) {
        List<Student> students = studentService.getStudentsPaginatedWithJoin(page, size);
        long total = studentService.getTotalStudentsCount();

        // Utilisation DTO
        List<StudentResponseModel> studentResponseModels = students
                .stream()
                .map(StudentResponseModel::new)
                .collect(Collectors.toList());
        
        
        return Response.ok(new PaginatedResponse<>(page, size, total, studentResponseModels)).build();    
    }
    
    @GET
    @Path("/only-search")
    public Response getAllStudentsSearch(
                                   @QueryParam("name") String name,
                                   @QueryParam("age") Integer age) {
    	List<Student> students = studentService.searchStudents(name, age);
    	
        List<StudentResponseModel> studentResponseModels = students
                .stream()
                .map(StudentResponseModel::new)
                .collect(Collectors.toList());
        
        
        return Response.ok(studentResponseModels).build();    
    }

    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") Long id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return Response.ok(new StudentResponseModel(student)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}