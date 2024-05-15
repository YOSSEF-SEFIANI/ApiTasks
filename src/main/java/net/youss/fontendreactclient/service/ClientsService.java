package net.youss.fontendreactclient.service;


import net.youss.fontendreactclient.entity.Clients;
import net.youss.fontendreactclient.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    private final ClientRepository clientRepository;

    public ClientsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Page<Clients> clientList(Pageable pageable) {
        Sort sort = pageable.getSortOr(Sort.by("name"));
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return clientRepository.findAll(pageable);
    }

    public Clients createClient(Clients newClient) {
        Clients byEmail = clientRepository.findByEmail(newClient.getEmail());

        if (byEmail != null ) {
            throw new RuntimeException("Email is Exist");
        }
        return clientRepository.save(newClient);

    }

    public void removeClients(String id) {
        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove client with id: " + id, e);
        }
    }


}
