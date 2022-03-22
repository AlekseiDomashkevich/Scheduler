package com.domashkevich.scheduler.controller;

import com.domashkevich.scheduler.entity.Room;
import com.domashkevich.scheduler.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping()
    public String manage(Model model) {
        var rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "management";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable("id") long id){
        roomRepository.deleteById(id);
        return "redirect:/manage";
    }

    @GetMapping("/{id}")
    public String editRoom(@PathVariable("id") long id, Model model){
        var room = roomRepository.findById(id).orElse(new Room());
        model.addAttribute("room", room);
        return "room/edit";
    }

    @PatchMapping("/{id}")
    public String patchRoom(@PathVariable("id") long id, @ModelAttribute("room") Room room){
        room.setId(id);
        roomRepository.save(room);
        return "redirect:/manage";
    }

    @PostMapping()
    public String saveRoom(@RequestParam(name = "name") String name){
        var room = new Room();
        room.setName(name);
        roomRepository.save(room);
        return "redirect:/manage";
    }
}
