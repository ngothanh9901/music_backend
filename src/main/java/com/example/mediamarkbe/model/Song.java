package com.example.mediamarkbe.model;

import com.example.mediamarkbe.model.support.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Song extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String avatarLink;
    private String musicLink;
    private String category;

    @ManyToMany(mappedBy = "songs")
    private List<Album> albums;
}
