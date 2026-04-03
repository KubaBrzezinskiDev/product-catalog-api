package com.kubadev.technicalassessmenttask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProducerRequestDto {

    @NotBlank
    private String name;

}
