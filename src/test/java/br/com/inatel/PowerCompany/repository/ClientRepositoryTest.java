package br.com.inatel.PowerCompany.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.inatel.PowerCompany.model.Client;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientRepositoryTest {
	
	private ClientRepository repository;
	Client client;
	
	@Autowired
	public void ClientRepositoryTeste(ClientRepository repository) {
		this.repository = repository;
		
	}
	
	/*@Before
	void before() {
		client = new Client("18765373908", "Teste","36198672" ,"Rua Teste", "Distrito Teste", "1276");
		repository.save(client);
	}*/
	
	@SuppressWarnings("deprecation")
	@Test
	void shouldReturnName() {
		Pageable pageable = null;
		Page<Client> test = repository.findByName("Teste", pageable);
		Assert.assertEquals("Teste", test.getContent().get(0).getName());
	} 

}
