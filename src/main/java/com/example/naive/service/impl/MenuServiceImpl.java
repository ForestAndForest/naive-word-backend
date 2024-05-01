package com.example.naive.service.impl;

import com.example.naive.domain.Menu;
import com.example.naive.repository.MenuRepository;
import com.example.naive.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getTree() {
        List<Menu> menus = menuRepository.findAll();

        return menus.stream()
                .filter(m -> m.getParent() == null)
                .peek(m -> m.setChildren(getChildrens(m, menus)))
                .collect(Collectors.toList());
    }

    private List<Menu> getChildrens(Menu root, List<Menu> all) {
        return all.stream()
                .filter(m -> Objects.equals(m.getParent(), root.getId()))
                .peek(m -> m.setChildren(getChildrens(m, all)))
                .collect(Collectors.toList());
    }


}
