package com.woowaSisters.woowaSisters.domain.meeting;
//import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@Table(name = "meeting")
@NoArgsConstructor
@EnableJpaRepositories
public class Meeting {

    @Id
    //UUID를 자동으로 생성
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meeting_uuid")
    @Type(type = "uuid-char")
    private UUID meetingUuid;

    @Column(name = "created_at")
    @CreationTimestamp
    private java.sql.Timestamp meetingCreatedAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private java.sql.Timestamp meetingUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @Column(name = "title")
    private String meetingTitle;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "attendees")
    private Integer meetingAttendees;

    @Column(name = "meeting_time")
    private long meetingTime;

    @Column(name = "meeting_location")
    private String meetingLocation;

    @Column(name = "content", columnDefinition = "LONGTEXT")
    @JsonProperty("content")
    private String meetingContent;

    @JsonProperty("uuid")
    public UUID getId() {
        return meetingUuid;
    }

    @JsonProperty("createdAt")
    public java.sql.Timestamp getCreatedAt() {
        return meetingCreatedAt;
    }

    @JsonProperty("updatedAt")
    public java.sql.Timestamp getUpdatedAt() {
        return meetingUpdatedAt;
    }

    @JsonProperty("title")
    public String getTitle() {
        return meetingTitle;
    }

    @JsonProperty("bookId")
    public String getBookId() { return bookId; }

    @JsonProperty("attendees")
    public Integer getAttendees() {
        return meetingAttendees;
    }

    @JsonProperty("time")
    public long getMeetingTime() {
        return meetingTime;
    }

    @JsonProperty("location")
    public String getMeetingLocation() {
        return meetingLocation;
    }

    @JsonProperty("content")
    public String getContent() {
        return meetingContent;
    }


    @Builder
    public Meeting(String meetingTitle, User user ,Integer meetingAttendees,
                   long meetingTime,String meetingLocation,String meetingContent) {
        this.meetingTitle = meetingTitle;
        this.user = user;
        this.meetingAttendees = meetingAttendees;
        this.meetingTime = meetingTime;
        this.meetingLocation = meetingLocation;
        this.meetingContent = meetingContent;
    }
    @ManyToMany
    @JoinTable(
            name = "meeting_members",
            joinColumns = @JoinColumn(name = "meeting_uuid"),
            inverseJoinColumns = @JoinColumn(name = "user_uuid")
    )
    private Set<User> members = new HashSet<>();

    public boolean isFull() {
        return members.size() >= meetingAttendees;
    }

    @Getter
    @ManyToMany
    @JoinTable(
            name = "meeting_subscribers",
            joinColumns = @JoinColumn(name = "meeting_uuid"),
            inverseJoinColumns = @JoinColumn(name = "user_uuid")
    )
    private Set<User> subscribers = new HashSet<>();

}