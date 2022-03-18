package com.domashkevich.scheduler.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDate date;


    @Column(name = "event_start")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime start;

    @Column(name = "event_stop")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime stop;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "info")
    private String info;
}
