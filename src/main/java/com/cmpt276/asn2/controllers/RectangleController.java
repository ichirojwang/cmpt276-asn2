package com.cmpt276.asn2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cmpt276.asn2.models.Rectangle;
// import org.springframework.web.bind.annotation.RequestParam;


@Controller //listen for requests from external source
public class RectangleController {
    @GetMapping("/rectangles/view")
    public String getAllRectangles(Model model) {
        System.out.println("Getting all rectangles");
        // TODO: get all users from database
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle("LeBron", 23, 23, "166088"));
        rectangles.add(new Rectangle("JR \"hennything is possible and I forgot what the score is\" Smith", 99, 93, "123456"));
        rectangles.add(new Rectangle("Kendrick Perkins", 10, 15, "4A6FA5"));
        rectangles.add(new Rectangle("Lance'll make em dance Stephenson", 100, 5, "4F6D7A"));
        rectangles.add(new Rectangle("Jimmer Freddette", 43, 12, "DDDDDD"));
        rectangles.add(new Rectangle("Jimmer Freddette version 2", 33, 33, "FFFFFF"));
        //end of database call
        rectangles.remove(2);
        model.addAttribute("rect", rectangles);
        return "rectangles/showAll";
    }

    @GetMapping("/rectangles/details")
    public String showDetails() {
        return "rectangles/details";
    }
    

}
