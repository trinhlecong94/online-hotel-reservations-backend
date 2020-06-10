package com.onlinehotelreservations.controller.hotel.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelPaginationDTO {
    List<HotelDTO> hotelDTOs;
    int count;
}
