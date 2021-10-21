package com.rental.web.controller;

import com.rental.service.ItemService;
import com.rental.web.dto.ItemResponseDto;
import com.rental.web.dto.ItemSaveRequestDto;
import com.rental.web.dto.ItemUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ItemApiController {

    private final ItemService itemService;

    @PostMapping("/api/v1/item")
    public Long save(@RequestBody ItemSaveRequestDto requestDto) {
        return itemService.save(requestDto);
    }

    @PutMapping("/api/v1/item/{id}")
    public Long update(@PathVariable Long id, @RequestBody ItemUpdateRequestDto requestDto) {
        return itemService.update(id, requestDto);
    }

    @GetMapping("/api/v1/item/{id}")
    public ItemResponseDto findById (@PathVariable Long id) {
        return itemService.findById(id);
    }

    @DeleteMapping("/api/v1/item/{id}")
    public Long delete(@PathVariable Long id) {
        itemService.delete(id);
        return id;
    }
}
