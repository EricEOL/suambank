package br.com.ericeol.suambank.entities.dto;

import br.com.ericeol.suambank.entities.Client;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClientDTO {

    private Long id;
    private String name;
    private List<AccountDTO> accounts;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.accounts = AccountDTO.convert(client.getAccounts());
    }

    public static List<ClientDTO> convert(List<Client> clients) {
        return clients.stream().map(ClientDTO::new).collect(Collectors.toList());
    }
}
