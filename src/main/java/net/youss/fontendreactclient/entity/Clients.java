package net.youss.fontendreactclient.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "email", length = 100)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 60)
    private Gender gender;

    private LocalDate birthDate;

    @Column(length = 120)
    private String profession;

    @ManyToMany
    @JoinTable(
            name = "group_client",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Groups> groups = new HashSet<>();

    public enum Gender {
        MALE, FEMALE
    }
}


