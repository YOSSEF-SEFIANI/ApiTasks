package net.youss.fontendreactclient.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "num_phone")
    private String numPhone;

    private String skype;

    @ManyToMany(mappedBy = "groups")
    private Set<Clients> clientsSet = new HashSet<>();
}



