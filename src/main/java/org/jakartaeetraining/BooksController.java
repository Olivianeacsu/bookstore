package org.jakartaeetraining;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

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
    @Operation(summary = "Returns all the books from DB")
    @APIResponse(responseCode = "200", description = "A list of books",
            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class, type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "204", description = "No books found")
    public Response getBooks() {
        List<Book> books = bookService.findAll();

        if(books.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(books).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Returns a book for a given identifier if it exists")
    @APIResponse(responseCode = "200", description = "A book",
            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Book.class)))
    @APIResponse(responseCode = "404", description = "No book found for the identifier")
    public Response getBookById(@PathParam("id") @Min(1) Long id) {
        Book book = bookService.findById(id);

        if(book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book).build();
    }

    @POST
    @Operation(summary = "Creates a valid book")
    public Response createBook(Book book) throws URISyntaxException {
        bookService.create(book);
        URI createdURI = uriInfo.getAbsolutePathBuilder()
                .path(Long.toString(book.getId())).build();
        return Response.created(createdURI).entity(book).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a book based on the provided identifier")
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
