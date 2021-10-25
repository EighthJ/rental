package com.rental.dto;

import com.rental.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;

    public UserDto(User user){
        BeanUtils.copyProperties(user,this);
    }

}
