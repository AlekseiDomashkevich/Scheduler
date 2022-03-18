package com.domashkevich.scheduler.repository;

import com.domashkevich.scheduler.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findAll();
}
