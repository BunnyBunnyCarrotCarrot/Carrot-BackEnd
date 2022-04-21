package com.carrot.hanghae.service;

import com.carrot.hanghae.domain.Good;
import com.carrot.hanghae.domain.Item;
import com.carrot.hanghae.domain.User;
import com.carrot.hanghae.dto.GoodRequestDto;
import com.carrot.hanghae.repository.GoodRepository;
import com.carrot.hanghae.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class GoodService {

    private final ItemRepository itemRepository;
    private final GoodRepository goodRepository;

    @Transactional
    public boolean getGoods(GoodRequestDto goodRequestDto, User user) {
        Long userId= user.getId();
        Item item = itemRepository.findById(goodRequestDto.getItemId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );
        //본인의 글에는 좋아요 누를 수 없도록 설정~~~
        if(userId.equals(item.getUser().getId())){
            throw new IllegalArgumentException("본인의 글에 좋아요를 누를 수 없어요!");
        }

        Good savedGood = goodRepository.findByItemAndUserId(item, userId);

        if(savedGood != null){
            goodRepository.deleteById(savedGood.getId());
            return false;
        } else{
            Good good = new Good(user, item);
            goodRepository.save(good);
            return true;
        }
    }

    @Transactional
    public boolean goodStatus(GoodRequestDto goodRequestDto, User user) {
        Long userId= user.getId();
        Item item = itemRepository.findById(goodRequestDto.getItemId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글이 없습니다.")
        );

        Good savedGood = goodRepository.findByItemAndUserId(item, userId);

        if(savedGood != null){
            return true;
        } else{
            return false;
        }
    }
}
