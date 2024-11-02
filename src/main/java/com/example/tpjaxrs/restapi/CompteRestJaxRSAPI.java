package com.example.tpjaxrs.restapi;

import com.example.tpjaxrs.entity.Compte;
import com.example.tpjaxrs.repository.CompteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Path("/banque")
public class CompteRestJaxRSAPI {

	@Autowired
	private CompteRepository compteRepository;

	@GET
	@Path("/comptes")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getComptes() {
		List<Compte> comptes = this.compteRepository.findAll();
		// Wrap the list in a GenericEntity so that Jersey knows how to serialize it
		GenericEntity<List<Compte>> entity = new GenericEntity<List<Compte>>(comptes) {};
		return Response.ok(entity).build();
	}

	@GET
	@Path("/comptes/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getCompte(@PathParam("id") Long id) {
		Compte compte = this.compteRepository.findById(id).orElse(null);
		if (compte == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(compte).build();
	}

	@POST
	@Path("/comptes")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addCompte(Compte compte) {
		Compte savedCompte = this.compteRepository.save(compte);
		return Response.ok(savedCompte).build();
	}

	@PUT
	@Path("/comptes/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateCompte(@PathParam("id") Long id, Compte compte) {
		Compte existingCompte = this.compteRepository.findById(id).orElse(null);
		if (existingCompte == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		existingCompte.setSold(compte.getSold());
		existingCompte.setDateCreation(compte.getDateCreation());
		existingCompte.setTypeCompte(compte.getTypeCompte());
		Compte updatedCompte = this.compteRepository.save(existingCompte);
		return Response.ok(updatedCompte).build();
	}

	@DELETE
	@Path("/comptes/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response deleteCompte(@PathParam("id") Long id) {
		if (!this.compteRepository.existsById(id)) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		this.compteRepository.deleteById(id);
		return Response.noContent().build();
	}
}
