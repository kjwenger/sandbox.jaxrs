package com.u14n.sandbox.jaxrs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

import com.u14n.sandbox.model.DAOException;
import com.u14n.sandbox.model.geography.Address;
import com.u14n.sandbox.model.geography.AddressDAO;

/**
 * Root resource (exposed at "addresses" path)
 */
@Path("addresses")
public class AddressesResource {

	/* package */ Address redHatAmphitheater;
	/* package */ Address redHatHeadquartersSuite;
	/* package */ AddressDAO addressDAO = new AddressDAO.ConcurrentMemory();

	public AddressesResource() {
		try {
			redHatHeadquartersSuite = new Address(
					"USA",
					"NC",
					"27601",
					"Raleigh",
					"E Davie Street",
					"100",
					"Ste 108");
			this.addressDAO.insert(redHatHeadquartersSuite);
		} catch (DAOException e) {
			// Ignore
		}
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Address> getAll() throws DAOException {
		List<Address> list = this.addressDAO.findAll();
																				System.out.println("AddressesResource.getAll() list.size()=" + list.size());
		return list;
	}

	@Path("addresses.json")
	@Produces(MediaType.APPLICATION_JSON)
	public static class JSON extends AddressesResource {
		@GET
		public List<Address> getAllJSON() throws DAOException {
																				System.out.println("AddressesResource$JSON.getAllJSON()");
			return getAll();
		}
	}
	@Path("addresses.xml")
	public static class XML extends AddressesResource {
		@GET
		@Produces(MediaType.APPLICATION_XML)
		public List<Address> getAllXML() throws DAOException {
																				System.out.println("AddressesResource$XML.getAllXML()");
			return getAll();
		}
	}
}
