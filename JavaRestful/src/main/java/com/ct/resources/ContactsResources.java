package com.ct.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ct.dao.ContactsDao;
import com.ct.dao.DaoException;
import com.ct.dao.JdbcContactsDao;
import com.ct.entity.Contacts;

@Path("/contacts")
public class ContactsResources {

	private ContactsDao dao = new JdbcContactsDao();
	
	@GET
	@Produces({"application/json"})
	public Response gelAllContacts() throws DaoException{
		return Response.ok(dao.findAll()).build();
	}
	

	@Path("/{id}")
	@Produces({"application/json"})
	@GET
	public Response getById(@PathParam("id") Integer id) throws DaoException {
		return Response.ok(dao.findById(id)).build();
	}
	
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response addContact(Contacts contact) throws DaoException {
		contact = dao.addContact(contact);
		return Response.ok(contact).build();
	}
	
	@Path("/{id}")
	@PUT
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Response updateContact(@PathParam("id") Integer id, Contacts contact) throws DaoException {
		contact.setId(id);
		contact = dao.updateContact(contact);
		return Response.ok(contact).build();
	}
	
	@Path("/{id}")
	@DELETE
	@Consumes({"application/json"})
	public Response deleteContact(@PathParam("id") Integer id) throws DaoException {
		dao.deleteContact(id);
		return Response.ok().build();
	}
}
