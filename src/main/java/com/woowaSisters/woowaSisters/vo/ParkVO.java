package com.woowaSisters.woowaSisters.vo;

import com.woowaSisters.woowaSisters.domain.park.Parks;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ParkVO {
    private String parkName;
    private String parkAddress;
    private String parkPhoneNum;
    private String parkInfo;
    private double latitude;
    private double longitude;

    public ParkVO(Parks Parks){
        this.parkName = Parks.getParkName();
        this.parkAddress = Parks.getParkAddress();
        this.parkPhoneNum = Parks.getParkPhoneNum();
        this.parkInfo = Parks.getParkInfo();
        this.latitude = Parks.getLatitude();
        this.longitude = Parks.getLongitude();
    }

}