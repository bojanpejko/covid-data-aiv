package com.aiv.covid.dto.region;

import com.aiv.covid.dto.data.GetDataDTO;
import com.aiv.covid.vao.Region;
import com.aiv.covid.vao.ZoneStatus;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class GetRegionDTO {

    private UUID uuid;

    private String name;

    private int number;

    private UUID adminID;

    private ZoneStatus status;

    private List<GetDataDTO> calendar;

    public static GetRegionDTO createDTOFromRegion(Region region){
        GetRegionDTO getRegionDTO = new GetRegionDTO();

        getRegionDTO.setUuid(region.getUuid());
        getRegionDTO.setAdminID(region.getAdminID());
        getRegionDTO.setName(region.getName());
        getRegionDTO.setCalendar(region.getCalendar().stream().map(GetDataDTO::createDTOFromData).collect(Collectors.toList()));
        getRegionDTO.setNumber(region.getNumOfInhabitants());
        getRegionDTO.setStatus(region.getStatus());

        return getRegionDTO;
    }
}
