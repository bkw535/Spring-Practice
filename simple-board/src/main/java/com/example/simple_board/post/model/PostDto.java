package com.example.simple_board.post.model;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {
    private Long id;
    private Long boardId;  // board+ _id => board_id
    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    private String content;
    private LocalDateTime postedAt;
}
