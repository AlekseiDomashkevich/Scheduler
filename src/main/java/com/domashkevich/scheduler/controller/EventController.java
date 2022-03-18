package com.domashkevich.scheduler.controller;

import com.domashkevich.scheduler.entity.Event;
import com.domashkevich.scheduler.entity.Room;
import com.domashkevich.scheduler.repository.RoomRepository;
import com.domashkevich.scheduler.repository.EventRepository;
import com.domashkevich.scheduler.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller

public class EventController {
    @Autowired
    private EventService service;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        List<LocalDate> dates = service.getDates();
        model.addAttribute("newEvent", new Event());
        model.addAttribute("dates", dates);
        model.addAttribute("service", service);
        return "index";
    }


    @PostMapping("/")
    public String saveEvent(@ModelAttribute ("newEvent") Event event){
        eventRepository.save(event);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newEvent(@ModelAttribute("newEvent") Event newEvent, Model model){
//        model.addAttribute("newEvent", newEvent);
        model.addAttribute("rooms", roomRepository.findAll());
        return "new_event";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ("newEvent") Event newEvent){
        eventRepository.save(newEvent);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(Model model, @PathVariable("id") long id){
        var event = eventRepository.getById(id);
        model.addAttribute("event", event);
        model.addAttribute("rooms", roomRepository.findAll());
        return "edit";
    }

    @PatchMapping("/edit/{id}")
    public String patch(@ModelAttribute("event") Event event, @PathVariable("id") long id){
        event.setId(id);
        service.update(event);
        return "redirect:/";
    }

    @DeleteMapping("edit/{id}")
    public String delete(@PathVariable("id") long id){
        eventRepository.deleteById(id);
        return "redirect:/";
    }
}
