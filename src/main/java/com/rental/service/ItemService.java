package com.rental.service;

import com.rental.entity.Item;
import com.rental.repository.ItemRepository;
import com.rental.web.dto.ItemResponseDto;
import com.rental.web.dto.ItemSaveRequestDto;
import com.rental.web.dto.ItemUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long save(ItemSaveRequestDto requestDto) {

        return itemRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ItemUpdateRequestDto requestDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id="+ id));
        item.update(requestDto.getItemName(), requestDto.getPrice(), requestDto.getItemDetail());
        return id;
    }

    public ItemResponseDto findById (Long id) {
        Item entity = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" +id));
        return new ItemResponseDto(entity);
    }

    @Transactional
    public void delete (Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
        itemRepository.delete(item);
    }


}
