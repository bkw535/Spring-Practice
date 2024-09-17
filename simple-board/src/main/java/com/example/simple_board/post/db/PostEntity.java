package com.example.simple_board.post.db;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private BoardEntity board;  // board+ _id => board_id
    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime postedAt;

//    @Transient  // 데이터베이스의 컬럼으로 쓰지 않기 위해 설정
    @OneToMany(
            mappedBy = "post"
    )
    @Builder.Default
    private List<ReplyEntity> replyList = List.of();
}
