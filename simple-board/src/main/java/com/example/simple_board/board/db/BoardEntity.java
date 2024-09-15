package com.example.simple_board.board.db;

import com.example.simple_board.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String boardName;

    private String status;

    @OneToMany(
            mappedBy = "board"
    )
    private List<PostEntity> postList = List.of();
}
