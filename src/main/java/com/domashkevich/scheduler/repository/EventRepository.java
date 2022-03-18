package com.domashkevich.scheduler.repository;

import com.domashkevich.scheduler.entity.Event;
import com.domashkevich.scheduler.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
    List<Event> getByDate(LocalDate date);

    @Query(value = "select * from scheduler.event\n" +
            "where date =:date and room_id =:room", nativeQuery = true)
    List<Event> getByDateAndRoom(@Param("date") LocalDate date, @Param("room") Room room);

    @Query(value = "select * from scheduler.event where date >=:date", nativeQuery = true)
    List<Event> getAllDate(@Param("date") LocalDate date);

    Event getById(long id);
}
