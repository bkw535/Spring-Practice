package com.example.simple_board.crud;

/*
dto -> entity -> dto
 */

import com.example.simple_board.common.Api;
import com.example.simple_board.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class CRUDAbstractService<DTO, ENTITY> implements CRUDInterface<DTO> {

    @Autowired(required = false)
    private JpaRepository<ENTITY, Long> jpaRepository;

    @Autowired(required = false)
    private Converter<DTO, ENTITY> converter;

    @Override
    public DTO create(DTO dto) {
        // dto -> entity 변환
        ENTITY entity = converter.toEntity(dto);
        // entity 저장
        ENTITY savedEntity = jpaRepository.save(entity);
        // 저장된 entity -> dto 변환
        return converter.toDTO(savedEntity);
    }

    @Override
    public Optional<DTO> read(Long id) {
        // id로 entity 조회 후 dto로 변환
        return jpaRepository.findById(id)
                .map(converter::toDTO);
    }

    @Override
    public DTO update(DTO dto) {
        // dto -> entity 변환 후 저장
        ENTITY entity = converter.toEntity(dto);
        ENTITY updatedEntity = jpaRepository.save(entity);
        // 업데이트된 entity -> dto 변환
        return converter.toDTO(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        // ID로 entity 삭제
        jpaRepository.deleteById(id);
    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) {
        // 페이지네이션 처리된 entity 리스트 조회
        var list = jpaRepository.findAll(pageable);

        // 페이지네이션 정보 설정
        Pagination pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        // entity 리스트 -> dto 리스트 변환
        List<DTO> dtoList = list.stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());

        // Api 응답 객체 생성
        return Api.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();
    }
}
