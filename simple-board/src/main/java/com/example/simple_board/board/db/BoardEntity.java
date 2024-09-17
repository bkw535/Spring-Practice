package com.example.simple_board.board.db;

import com.example.simple_board.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Where;

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
    @Where(clause = "status = 'REGISTERED")
    @Builder.Default
    @OrderBy("id DESC")
    private List<PostEntity> postList = List.of();
}
