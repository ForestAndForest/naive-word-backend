package com.example.naive.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class HotNoteRequest {
    private List<Integer> ids;
    private int num;
}
