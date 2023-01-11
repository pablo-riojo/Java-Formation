package com.postformacion.springbatch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String location;
    private String date;
    private int temperature;
}
