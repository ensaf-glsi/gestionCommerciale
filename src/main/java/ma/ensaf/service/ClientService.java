package ma.ensaf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.Getter;
import ma.ensaf.entity.Client;

public class ClientService {
	
	private static Integer autoInc = 1;
	@Getter
	private List<Client> list = new ArrayList<>();
	
	public Client create(Client client) {
		client.setId(autoInc++);
		list.add(client);
		return client;
	}
	public void update(Client client) {
		Integer index = list.indexOf(client);
		if (index == -1) {
			System.out.println("client introuvable !");
			return;
		}
		list.set(index, client);
	}
	public void delete(Integer id) {
		list.remove(Client.builder().id(id).build());
	}
	public Optional<Client> findById(Integer id) {
		return list.stream().filter(c -> Objects.equals(c.getId(), id)).findFirst();
	}
	
	public static void main(String[] args) {
		Client c1 = Client.builder().id(1).nom("zouhir").build();
		Client c2 = Client.builder().id(1).nom("zhi").build();
		System.out.println("comp ref : " + (c1 == c2));
		List<Client> db1 = new ArrayList<>();
		db1.add(c1);
		System.out.println("equals : " + c1.equals(c2));
		System.out.println("meme id meme ref : " + db1.indexOf(c1));
		System.out.println("meme id ref diff : " + db1.indexOf(c2));
	}
}
