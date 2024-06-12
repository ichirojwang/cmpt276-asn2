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
        List<Rectangle> rectangles = rectRepo.findByOrderByRidAsc();
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

        // if (newWidth < 0 || newHeight < 0) {
        //     response.setStatus(400);
        //     response.getWriter().write("Dimensions must be positive.");
        //     return "redirect:/rectangles/view";
        // }

        rectRepo.save(new Rectangle(newName, newWidth, newHeight, newColor));
        response.setStatus(201);

        return "redirect:/rectangles/view";
    }
    
    @PostMapping("/rectangles/delete")
    public String deleteRectangle(@RequestParam("rid") int rid, HttpServletResponse response) {

        System.out.println("Removing ID: " + rid);

        rectRepo.deleteById(rid);
        response.setStatus(202);

        return "redirect:/rectangles/view";
    }

    @GetMapping("/rectangles/details")
    public String detailsRectangle(@RequestParam("rid") int rid, Model model) {

        Rectangle aRect = rectRepo.findByRid(rid);
        model.addAttribute("myRect", aRect);

        return "rectangles/showDetails";
    }
    

}
