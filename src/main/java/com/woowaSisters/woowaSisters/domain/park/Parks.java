package com.woowaSisters.woowaSisters.domain.park;


import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@Table(name = "park")
@NoArgsConstructor
public class Parks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "park_uuid", columnDefinition = "BINARY(16)")
    private UUID parkUuid;

    @Column(name = "park_name", nullable = false)
    private String parkName;

    @Column(name = "park_address", nullable = false)
    private String parkAddress;

    @Column(name = "park_phone_num")
    private String parkPhoneNum;

    @Column(name = "park_info", columnDefinition = "LONGTEXT")
    private String parkInfo;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @JsonProperty("parkUuid")
    public UUID getParkUuid() {return parkUuid;}

    @JsonProperty("parkName")
    public String getParkName() {return parkName;}

    @JsonProperty("parkAddress")
    public String getParkAddress() {return parkAddress;}

    @JsonProperty("parkPhoneNum")
    public String getParkPhoneNum() {return parkPhoneNum;}

    @JsonProperty("parkInfo")
    public String getParkInfo() {return parkInfo;}

    @JsonProperty("latitude")
    public double getLatitude() {return latitude;}

    @JsonProperty("longitude")
    public double getLongitude() {return longitude;}

}