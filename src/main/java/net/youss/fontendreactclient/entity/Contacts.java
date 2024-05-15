package net.youss.fontendreactclient.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="num_phone")
    private String numPhone;

    private String skype;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;
}


