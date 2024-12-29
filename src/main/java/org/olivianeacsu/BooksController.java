package org.olivianeacsu;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/books")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class BooksController {

    @GET
    public Response getBooks() {
        List<Book> books = new ArrayList<>();
        //TODO:: get all books from DB

        if(books.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(books).build();
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Long id) {
        Book book = null; //TODO:: get the book from DB

        if(book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book).build();
    }

    @POST
    public Response createBook(Book book) throws URISyntaxException {
        //book.setId(1L);
        URI createdURI = new URI(book.getId().toString());
        return Response.created(createdURI).entity(book).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        //TODO:: delete book from DB
        return Response.noContent().build();
    }
}
