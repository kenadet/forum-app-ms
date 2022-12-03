package com.kenny.forum.model.dto;

import com.kenny.forum.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {
    Type type;
    String country;
    String state;
    String city;
}
