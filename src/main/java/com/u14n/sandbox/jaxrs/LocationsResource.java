package com.u14n.sandbox.jaxrs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

import com.u14n.sandbox.model.DAOException;
import com.u14n.sandbox.model.geography.Location;
import com.u14n.sandbox.model.geography.LocationDAO;

/**
 * Root resource (exposed at "locations" path)
 */
@Path("/locations")
public class LocationsResource {

	/* package */ static Location redHatAmphitheater;
	/* package */ static Location redHatHeadquarters;
	/* package */ static LocationDAO locationDAO =
			new LocationDAO.ConcurrentMemory();

	static {
		try {
			redHatAmphitheater = new Location(
					"USA",
					"NC",
					"27601",
					"Raleigh",
					"S McDowell St",
					"500");
																				System.out.println("LocationsResource redHatAmphitheater.getId()=" + redHatAmphitheater.getId());
			redHatHeadquarters = new Location(
					"USA",
					"NC",
					"27601",
					"Raleigh",
					"E Davie Street",
					"100");
																				System.out.println("LocationsResource redHatHeadquarters.getId()=" + redHatHeadquarters.getId());
			locationDAO.insert(redHatAmphitheater);
			locationDAO.insert(redHatHeadquarters);
																				System.out.println("LocationsResource locationDAO.findAll().size()=" + locationDAO.findAll().size());
		} catch (DAOException e) {
			// Ignore
		}
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public List<Location> getAllTXT() throws DAOException {
		List<Location> list = this.locationDAO.findAll();
																				System.out.println("LocationsResource.getAll() list.size()=" + list.size());
		return list;
	}
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Location> getAll() throws DAOException {
		List<Location> list = this.locationDAO.findAll();
																				System.out.println("LocationsResource.getAll() list.size()=" + list.size());
		return list;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> getAllJSON() throws DAOException {
																				System.out.println("LocationsResource.getAllJSON()");
		return getAll();
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Location> getAllXML() throws DAOException {
																				System.out.println("LocationsResource.getAllXML()");
		return getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public Location getLocation(
			@PathParam("id") final Long id) throws DAOException {
		Location location = this.locationDAO.findByIdentity(id);
																				System.out.println("LocationsResource.getLocation() location=" + location);
		return location;
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Location getLocationJSON(
			@PathParam("id") final Long id) throws DAOException {
																				System.out.println("LocationsResource.getLocationJSON()");
		return getLocation(id);
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Location getLocationXML(
			@PathParam("id") final Long id) throws DAOException {
																				System.out.println("LocationsResource$LocationXML.getLocation()");
		return getLocation(id);
	}
}
