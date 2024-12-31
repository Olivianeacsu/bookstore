package org.jakartaeetraining;

import jakarta.json.bind.annotation.JsonbNumberFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "T_BOOK")
@Schema(name= "Book", description = "POJO that represents a book")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String isbn;

    @Column(length = 200)
    @NotNull
    private String title;

    @Column(length = 10000)
    @Size(min = 10, max = 10000)
    private String description;

    @Min(1)
    @JsonbNumberFormat(locale = "en_US", value="$#0.00")
    private BigDecimal price;

    @Column(name = "publication_date")
    @JsonbProperty("publication-date")
    @Past
    private LocalDate publicationDate;

    @Column(name = "nb_of_pages")
    @JsonbProperty("nb-of-pages")
    @Min(10)
    private Integer nbOfPages;

    @Column(name = "image_url")
    //@JsonbTransient
    private String imageURL;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getNbOfPages() {
        return nbOfPages;
    }

    public void setNbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
