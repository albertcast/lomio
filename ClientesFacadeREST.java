/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Clientes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Alberto Pc
 */
@Stateless
@Path("entity.clientes")
public class ClientesFacadeREST extends AbstractFacade<Clientes> {

    @PersistenceContext(unitName = "MiServidorPU")
    private EntityManager em;

    public ClientesFacadeREST() {
        super(Clientes.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Clientes entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Clientes entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    
    @GET
    @Path("{id}")
    public Clientes find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    
    
    @GET
    @Path("id/{id}/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Clientes> editByName(@PathParam("name") String name, @PathParam("id") Integer id, Clientes entity) {
        Query q = em.createNamedQuery("Clientes.findByIdNombre");
        q.setParameter("nombre", name);
        q.setParameter("id", id);
        
        List<Clientes> lista = q.getResultList();
        return lista;
    }
    
    @PUT
    @Path("name/{name}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editByName(@PathParam("name") String name, Clientes entity) {
        Query q = em.createNamedQuery("Clientes.findByNombre");
        q.setParameter("nombre", name);
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Clientes> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Clientes> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
