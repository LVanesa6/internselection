package com.uptc.bb.internselection.dto;

import com.uptc.bb.internselection.entity.Intern.State;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InternDTO implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String career;
    private Integer semester;
    private String cvPath;
    private State statev;
}
