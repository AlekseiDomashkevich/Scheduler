package com.domashkevich.scheduler.service;

import com.domashkevich.scheduler.entity.Event;
import com.domashkevich.scheduler.entity.Room;
import com.domashkevich.scheduler.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService {
    @Autowired
    private EventRepository repository;


    public List<Event> getEventByDateAndRoom(LocalDate date, Room room) {
        return repository.getByDateAndRoom(date, room).stream()
                .sorted(Comparator.comparing(Event::getStart))
                .collect(Collectors.toList());
    }

    public List<LocalDate> getDates() {
        var now = LocalDate.now();
        return repository.getAllDate(now).stream()
                .map(Event::getDate)
                .distinct()
                .sorted()
                .collect(Collectors.toList());


    }

    public void update(Event event) {
        repository.save(event);

    }

}
