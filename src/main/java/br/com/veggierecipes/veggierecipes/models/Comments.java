package br.com.veggierecipes.veggierecipes.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name", length = 50)
    private String author_name;

    @Column(name = "author_email", length = 50)
    private String author_email;

    @DateTimeFormat(style = "dd-MM-YYYY")
    @Column(name = "commentedAt")
    private LocalDate commentedAt;

    @Column(name = "content")
    private String content;

    @Column(name = "author_image_address", length = 99)
    private String author_image_address;

}
