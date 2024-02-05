package com.woowaSisters.woowaSisters.domain.subBook;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sub_book")
public class SubBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private UUID id;

    @Column(name="datetime")
    private LocalDateTime datetime;

    @Column(name="user_id")
    private Long userId;

    @Column(name="book_id")
    private Long bookId;
}
