package com.woowaSisters.woowaSisters.domain.meeting;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.UUID;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID meetingUuid;

    @Column(name = "created_at")
    @CreationTimestamp
    private java.sql.Timestamp meetingCreatedAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private java.sql.Timestamp meetingUpdatedAt;

    @Column(name = "deleted_at")
    private java.sql.Timestamp meetingDeletedAt;

    @Column(name = "title")
    private String meetingTitle;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "attendees")
    private Integer meetingAttendees;

    @Column(name = "meeting_time")
    private long meetingTime;

    @Column(name = "meeting_location")
    private String meetingLocation;

    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String meetingContent;

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    @JsonProperty("id")
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

    @JsonProperty("deletedAt")
    public java.sql.Timestamp getDeletedAt() {
        return meetingDeletedAt;
    }

    @JsonProperty("title")
    public String getTitle() {
        return meetingTitle;
    }

    @JsonProperty("bookId")
    public Long getBookId() {
        return bookId;
    }

    @JsonProperty("attendees")
    public Integer getAttendees() {
        return meetingAttendees;
    }

    @JsonProperty("meetingTime")
    public long getMeetingTime() {
        return meetingTime;
    }

    @JsonProperty("meetingLocation")
    public String getMeetingLocation() {
        return meetingLocation;
    }

    @JsonProperty("content")
    public String getContent() {
        return meetingContent;
    }

    @Getter
    private Integer maxAttendees;

    public void setMaxAttendees(Integer maxAttendees) {
        this.maxAttendees = maxAttendees;
    }
    public void setMeetingTime(long meetingTime) {
        this.meetingTime = meetingTime;
    }
}