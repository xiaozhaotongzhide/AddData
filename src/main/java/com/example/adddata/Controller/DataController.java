package com.example.adddata.Controller;

import com.example.adddata.Service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/")
    public String data(Model model){
        String data = dataService.getData();
        log.info(data);
        model.addAttribute("data",data);
        return "index";
    }
    @GetMapping("/add")
    public String addData(Model model){
        dataService.addData("10");
        return "forward:/";
    }

    @GetMapping("/reduce")
    public String reduceData(Model model){
        dataService.reduceData("10");
        return "forward:/";
    }
}
