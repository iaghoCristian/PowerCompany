package br.com.inatel.PowerCompany.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.PowerCompany.controller.dto.ClientDto;
import br.com.inatel.PowerCompany.controller.form.ClientForm;
import br.com.inatel.PowerCompany.controller.form.ClientFormUpdate;
import br.com.inatel.PowerCompany.model.Client;
import br.com.inatel.PowerCompany.repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping
	@Cacheable(value="clientList")
	public Page<ClientDto> list(@RequestParam(required=false) String name, @PageableDefault(sort = "id", size= 5, page=0) Pageable pagination){
		
		if(name == null) {
			Page<Client> clients = clientRepository.findAll(pagination);
			return ClientDto.converter(clients);
		} else {
			Page<Client> clients = clientRepository.findByName(name, pagination);
			return ClientDto.converter(clients);
		}
		
	}
	
	
	@PostMapping
	@Transactional
	@CacheEvict(value="clientList",allEntries=true)
	public ResponseEntity<Client> register(@RequestBody @Valid ClientForm form, UriComponentsBuilder uriBuilder) {
		Client client = form.converter();
		clientRepository.save(client);
		
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).body(client);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> detail(@PathVariable Long id) {
		Optional<Client> client = clientRepository.findById(id);
		if(client.isPresent()) {
			return ResponseEntity.ok(client.get());
		}
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value="clientList",allEntries=true)
	public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody @Valid ClientFormUpdate form){
		Optional<Client> optional = clientRepository.findById(id);
		if(optional.isPresent()) {
			Client client = form.update(id,clientRepository);
			return ResponseEntity.ok(client);
		}
			return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value="clientList",allEntries=true)
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Client> optional = clientRepository.findById(id);
		
		if(optional.isPresent()) {
			clientRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
