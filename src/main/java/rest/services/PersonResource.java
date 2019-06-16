package rest.services;

import dataBase.DataBaseService;
import domain.Person;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/people")
public class PersonResource {

    private DataBaseService dataBaseService = new DataBaseService();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person get(@PathParam("id") int id)
    {
        return dataBaseService.getPersonById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAll()
    {
        return dataBaseService.getAllPersons();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Add(Person person){
        dataBaseService.addPerson(person);
        return Response.ok(person.getId()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Person person){
        Person result = dataBaseService.getPersonById(id);
        if(result == null)
            return Response.status(404).build();
        person.setId(id);
        dataBaseService.updatePerson(person);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id){
        Person result = dataBaseService.getPersonById(id);
        if(result == null)
            return Response.status(404).build();
        dataBaseService.removePerson(result);
        return Response.ok().build();
    }
}
