package com.woowaSisters.woowaSisters.service;

import com.woowaSisters.woowaSisters.domain.park.Parks;
import com.woowaSisters.woowaSisters.vo.ParkVO;

import java.util.List;
import java.util.UUID;

public interface ParkService {

    public List<Parks> viewParkList();

    public List<Parks> searchParkList(List<UUID> parkUuids);

    public ParkVO findPark(UUID uuid);
}
