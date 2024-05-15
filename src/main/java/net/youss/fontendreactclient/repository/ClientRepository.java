package net.youss.fontendreactclient.repository;

import net.youss.fontendreactclient.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository
        extends JpaRepository<Clients, String> {

    Clients findByEmail(String email);
}
