package com.rental.service;

import com.rental.entity.Item;
import com.rental.repository.ItemRepository;
import com.rental.web.dto.ItemResponseDto;
import com.rental.web.dto.ItemSaveRequestDto;
import com.rental.web.dto.ItemUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional //@Transactional이 붙은 메서드는 메서드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다.
@RequiredArgsConstructor
@Slf4j //이미지 저장 로직에서 추후 예외처리를 위한 로그 삽입이 필요한 경우 로그 처리를 사용하기 위한 어노테이션
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    //상품 등록
    @Transactional
    public Long save(ItemSaveRequestDto requestDto, MultipartFile file) throws Exception {
        //저장할 경로 설정
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        //파일 이름을 랜덤으로 설정
        UUID uuid = UUID.randomUUID();
        //원래 파일 이름과 합쳐서 중복된 이름이 없게 설정
        String fileName = uuid + "_" + file.getOriginalFilename();
        //saveFile이란 파일을 생성해 저장경로를 넣어주고 이름을 'name'이라고 설정
        File saveFile = new File(filePath, "name");
        //file을 saveFile로 보내줌
        file.transferTo(saveFile);

        return itemRepository.save(requestDto.toEntity()).getId(); //
    }

    //상품 수정
    @Transactional
    public Long update(Long id, ItemUpdateRequestDto requestDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id)); //if else문 없이 예외처리를 하기 위해 .orElseThrow ~ 사용
        item.update(requestDto.getItemName(), requestDto.getPrice(), requestDto.getItemDetail(), requestDto.getFileName(), requestDto.getUuid(), requestDto.getContentType());
        return id;
    }

    //상품 id로 조회
    @Transactional
    public ItemResponseDto findById(Long id) {
        Item entity = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
        return new ItemResponseDto(entity);
    }
    //상품명으로 조회
    @Transactional
    public List<ItemResponseDto> findByItemName(String itemName) {
        List<Item> itemList = itemRepository.findByName(itemName);
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        if (itemList.isEmpty()) return itemResponseDtoList;

        for (Item item : itemList) {
            itemResponseDtoList.add(new ItemResponseDto(item));
        }
        return itemResponseDtoList;
    }

    //전체 상품 조회
    @Transactional
    public List<ItemResponseDto> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());
    }

    //상품 삭제
    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
        itemRepository.delete(item);
    }
}
