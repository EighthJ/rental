package com.rental.web.controller;

import com.rental.service.ItemService;
import com.rental.web.dto.ItemResponseDto;
import com.rental.web.dto.ItemSaveRequestDto;
import com.rental.web.dto.ItemUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor //final 필드가 포함된 생성자를 생성(final이 없는 필드는 생성자에 포함 X)
@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌
public class ItemApiController {

    private final ItemService itemService;
    //상품 저장
    @PostMapping("/api/v1/item") //@PostMapping은 데이터를 전송하고 @ResponseBody 가 붙은 파라미터에는 HTTP 요청의 분문 body 부분이 그대로 전달된다.
    public Long save(@RequestBody ItemSaveRequestDto requestDto, MultipartFile file) throws Exception {
        return itemService.save(requestDto, file);
    }
    //상품 수정
    @PutMapping("/api/v1/item/{id}") //
    public Long update(@PathVariable Long id, @RequestBody ItemUpdateRequestDto requestDto) {
        return itemService.update(id, requestDto);
    }
    //상품 id로 조회
    @GetMapping("/api/v1/item/{id}") //HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어 줌(@RequestMapping 대신 사용)
    public ItemResponseDto findById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    //상품 삭제
    @DeleteMapping("/api/v1/item/{id}")
    public Long delete(@PathVariable Long id) {
        itemService.delete(id);
        return id;
    }

}
