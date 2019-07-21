package com.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "space_id")
    private Integer spaceId;

    @Column(name = "start_date")
    private Timestamp from;

    @Column(name = "end_date")
    private Timestamp to;

    public Booking(Integer id, Long userId, Integer spaceId, Timestamp from, Timestamp to) {
        this(userId, spaceId, from, to);
        this.id = id;
    }

    public Booking(Long userId, Integer spaceId, Timestamp from, Timestamp to) {
        this.userId = userId;
        this.spaceId = spaceId;
        this.from = from;
        this.to = to;
    }

    public Booking() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }
}
