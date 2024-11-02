package com.example.tpjaxrs.restapi;

import com.example.tpjaxrs.entity.Compte;
import com.example.tpjaxrs.repository.CompteRepository;
import jakarta.ws.rs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Path("/banque")
public class CompteRestJaxRSAPI {

	@Autowired
	private CompteRepository compteRepository;

	@GET
	@Path("/comptes")
	@Produces({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<Compte> getComptes() {
		return this.compteRepository.findAll();
	}

	@GET
	@Path("/comptes/{id}")
	@Produces({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Compte getCompte(@PathParam("id") Long id) {
		return this.compteRepository.findById(id).orElse(null);
	}

	@POST
	@Path("/comptes")
	@Produces({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Consumes({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Compte addCompte(Compte compte) {
		return this.compteRepository.save(compte);
	}

	@PUT
	@Path("/comptes/{id}")
	@Produces({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Consumes({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Compte updateCompte(@PathParam("id") Long id, Compte compte) {
		Compte existingCompte = this.compteRepository.findById(id).orElse(null);
		if (existingCompte != null) {
			existingCompte.setSold(compte.getSold());
			existingCompte.setDateCreation(compte.getDateCreation());
			existingCompte.setTypeCompte(compte.getTypeCompte());
			return this.compteRepository.save(existingCompte);
		}

		return null;
	}

	@DELETE
	@Path("/comptes/{id}")
	@Produces({ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void deleteCompte(@PathParam("id") Long id) {
		this.compteRepository.deleteById(id);
	}

}