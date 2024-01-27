package com.woowaSisters.woowaSisters.domain.subscribeMeeting;

import com.woowaSisters.woowaSisters.domain.meeting.Meeting;
import com.woowaSisters.woowaSisters.domain.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@Table(name = "subscribeMeeting")
@NoArgsConstructor
@EnableJpaRepositories
public class SubscribeMeeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscribeMeeting_uuid")
    private UUID subscribeMeetingUuid;

    @ManyToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "user_uuid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "meeting_uuid")
    private Meeting meeting;

    @CreationTimestamp
    @Builder.Default
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


}
