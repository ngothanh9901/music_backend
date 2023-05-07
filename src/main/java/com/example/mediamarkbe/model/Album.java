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
public class Album extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "album_song",
            joinColumns = @JoinColumn(name = "abum_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs;

    private String avatarUrl;
    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
