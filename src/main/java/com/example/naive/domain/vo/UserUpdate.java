package com.example.naive.domain.vo;

import lombok.Data;

/**
 * @author 29002
 */
@Data
public class UserUpdate {
    private String uid;
    private Long time;

    @Override
    public String toString(){
        return uid+time;
    }
}
