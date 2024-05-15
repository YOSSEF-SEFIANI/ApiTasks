package net.youss.fontendreactclient.web;


import lombok.RequiredArgsConstructor;
import net.youss.fontendreactclient.entity.Clients;
import net.youss.fontendreactclient.service.ClientsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
public class ClientController {


    private final ClientsService clientsService;

    @GetMapping
    public Page<Clients> findAll(Pageable pageable) {
        return clientsService.clientList(pageable);
    }

    @GetMapping("/auth")
    public Authentication auth(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/auth/api")
    public Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PostMapping
    public Clients save(@RequestBody Clients clients) {
        return clientsService.createClient(clients);
    }

    @DeleteMapping
    public void deleteById(@PathVariable String id) {
        clientsService.removeClients(id);
    }


}
