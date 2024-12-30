package org.jakartaeetraining;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/api/books")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class BooksController {

    @Inject
    UriInfo uriInfo;

    @Inject
    BookService bookService;

    @GET
    public Response getBooks() {
        List<Book> books = bookService.findAll();

        if(books.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(books).build();
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") @Min(1) Long id) {
        Book book = bookService.findById(id);

        if(book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book).build();
    }

    @POST
    public Response createBook(Book book) throws URISyntaxException {
        bookService.create(book);
        URI createdURI = uriInfo.getAbsolutePathBuilder()
                .path(Long.toString(book.getId())).build();
        return Response.created(createdURI).entity(book).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/count")
    @Produces(TEXT_PLAIN)
    public Response countBooks() {
        Long nbOfBooks = bookService.countAll();
        if (nbOfBooks == 0)
            return Response.noContent().build();

        return Response.ok(nbOfBooks).build();
    }
}
