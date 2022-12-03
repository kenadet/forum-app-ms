package com.kenny.forum.model;

import com.kenny.forum.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(columnDefinition = "uuid")
    UUID id;
    String author;
    String title;
    String body;
    int likes;
    int dislikes;
    LocalDateTime createdAt;
    boolean flag; // TODO
    Type type;
    String country;
    String state;
    String city;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Comment> comments = new HashSet<>();

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }

        if (this.getClass() != other.getClass())
        {
            return false;
        }

        if (this.id != ((Post)other).id)
        {
            return false;
        }

        return true;
    }
}
