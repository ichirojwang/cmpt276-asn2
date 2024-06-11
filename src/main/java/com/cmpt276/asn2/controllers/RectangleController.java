package com.cmpt276.asn2.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt276.asn2.models.Rectangle;
import com.cmpt276.asn2.models.RectangleRepository;

import jakarta.servlet.http.HttpServletResponse;



@Controller //listen for requests from external source
public class RectangleController {

    @Autowired
    private RectangleRepository rectRepo;

    @GetMapping("/rectangles/view")
    public String getAllRectangles(Model model) {
        System.out.println("Getting all rectangles from database.");
        // get all users from database
        List<Rectangle> rectangles = rectRepo.findAll();
        // rectangles.add(new Rectangle("LeBron", 23, 23, "166088"));
        // rectangles.add(new Rectangle("JR \"hennything is possible and I forgot what the score is\" Smith", 99, 93, "123456"));
        // rectangles.add(new Rectangle("Kendrick Perkins", 10, 15, "4A6FA5"));
        // rectangles.add(new Rectangle("Lance'll make em dance Stephenson", 100, 5, "4F6D7A"));
        // rectangles.add(new Rectangle("Jimmer Freddette", 43, 12, "DDDDDD"));
        // rectangles.add(new Rectangle("Jimmer Freddette version 2", 33, 33, "FFFFFF"));
        // end of database call
        model.addAttribute("rect", rectangles);
        return "rectangles/showAll";
    }

    @PostMapping("/rectangles/add")
    public String addRectangle(@RequestParam Map<String, String> newrect, HttpServletResponse response) {
        System.out.println("ADD rectangle");
        String newName = newrect.get("name");
        int newWidth = Integer.parseInt(newrect.get("width"));
        int newHeight = Integer.parseInt(newrect.get("height"));
        String newColor = newrect.get("color");

        rectRepo.save(new Rectangle(newName, newWidth, newHeight, newColor));

        response.setStatus(201);
        return "rectangles/addedRect";
    }
    

    // @GetMapping("/rectangles/details")
    // public String showDetails() {
    //     return "rectangles/details";
    // }
    

}
