package com.sdsufba.controllers;

import com.sdsufba.documentdata.DataDocment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/documents")
public class DocumentController {

    @PostMapping
    public String catchData(@RequestBody DataDocment data){
        System.out.println(data.getName());
        System.out.println(data.getCamp());
        System.out.println(data.getDate());

        return "Catch Data Successful!";
    }


}
