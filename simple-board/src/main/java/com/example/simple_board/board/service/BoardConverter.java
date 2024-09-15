package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.post.service.PostConverter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BoardConverter {
    private final PostConverter postConverter;

    public BoardConverter(PostConverter postConverter) {
        this.postConverter = postConverter;
    }

    public BoardDto toDto(BoardEntity boardEntity) {

        var postList =  boardEntity.getPostList()
                .stream()
                .map(postConverter::toDto)
                .collect(Collectors.toList());

        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build();
    }
}
