package com.example.itemService.web.item.basic;


import com.example.itemService.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/basic/items")
@Controller
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;


    public String items(Model model) {

    }
}
