package com.cmpt276.asn2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        List<Rectangle> Rectangle = rectRepo.findByOrderByRidAsc();
        // end of database call
        model.addAttribute("rect", Rectangle);
        return "rectangles/showAll";
    }

    @PostMapping("/rectangles/add")
    public String addRectangle(@RequestParam Map<String, String> newRect, HttpServletResponse response) {
        System.out.println("ADD rectangle");
        String newName = newRect.get("name");
        int newWidth = Integer.parseInt(newRect.get("width"));
        int newHeight = Integer.parseInt(newRect.get("height"));
        String newColor = newRect.get("color");

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
    public String detailsRectangle(@RequestParam("rid") String rid, Model model) {

        try {
            int rectId = Integer.parseInt(rid);
            Rectangle aRect = rectRepo.findByRid(rectId);
            if (aRect != null) {
                model.addAttribute("myRect", aRect);
                return "rectangles/showDetails";
            }        
            else {
                return "rectangles/notFound";
            }
        }
        catch (NumberFormatException exception) {
            return "rectangles/notFound";
        }
        
    }

    @PostMapping("/rectangles/edit")
    public String editRectangle(@RequestParam Map<String, String> editRect, HttpServletResponse response) {

        int rectId = Integer.parseInt(editRect.get("rid"));
        Rectangle toEdit = rectRepo.findByRid(rectId);
        toEdit.setName(editRect.get("name"));
        toEdit.setWidth(Integer.parseInt(editRect.get("width")));
        toEdit.setHeight(Integer.parseInt(editRect.get("height")));
        toEdit.setColor(editRect.get("color"));

        response.setStatus(200);

        // System.out.println(editRect.get("name"));
        // System.out.println(toEdit.getName());
        // System.out.println(rectRepo.findByRid(rectId).getName());
        rectRepo.save(toEdit);

        return "redirect:/rectangles/details?rid=" + rectId;
    }
    
    @PutMapping("path/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {

        return entity;
    }

}
