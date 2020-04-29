package org.voiculescu.siit.recipes.model;

import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Recipe {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min=6,message = "minimum 6 characters")
    private String name;
    @Size(min=20,message = "minimum 20 characters")
    private String shortDescription;
    @Size(min=20,message = "minimum 20 characters")
    @Column(length=3000)
    private String ingredients;
    @Size(min=20,message = "minimum 20 characters")
    @Column(length=3000)
    private String directions;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @NotEmpty(message = "an image should be provided")
    @Lob
    byte[] image;
    @Enumerated(EnumType.STRING)
    private RecipeCategory recipeCategory;
    @CreationTimestamp
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate created;
    @UpdateTimestamp
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate lastModified;

    public Recipe(String name,
                  String shortDescription,
                  String ingredients,
                  String directions,
                  byte[] image,
                  RecipeCategory recipeCategory,
                  LocalDate created,
                  LocalDate lastModified) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.ingredients = ingredients;
        this.directions = directions;
        this.image = image;
        this.recipeCategory = recipeCategory;
        this.created = created;
        this.lastModified = lastModified;
    }

    public String getImage() {
        if (image !=null) {
            return new String(Base64.encodeBase64(image), StandardCharsets.UTF_8);
        }
        return null;
    }

    public void setImage(MultipartFile image) {
        try {
            this.image = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
