package com.uptc.bb.internselection.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "intern")
public class Intern {

    public enum State {
        PENDIENTE,
        VIABLE,
        NO_VIABLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String career;

    @Column(nullable = false)
    private Integer semester;

    @Column(name = "cv_path", length = 512)
    private String cvPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "statev", nullable = false)
    private State statev = State.PENDIENTE;
}
