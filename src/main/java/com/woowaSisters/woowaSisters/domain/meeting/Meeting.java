package com.woowaSisters.woowaSisters.domain.meeting;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
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

    public void setMeetingTime(long meetingTime) {
        this.meetingTime = meetingTime;
    }
}