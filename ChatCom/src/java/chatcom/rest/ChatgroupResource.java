/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

import chatcom.hibernateutil.HibernateUtil;
import chatcom.model.Chatgroup;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Uberti Davide
 */
@Stateless
@Path("chatcom.model.chatgroup")
public class ChatgroupFacadeREST extends AbstractFacade<Chatgroup> {

    @PersistenceContext(unitName = "")
    private EntityManager em;
    private Connection connection;

    public ChatgroupFacadeREST() {
        super(Chatgroup.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body) {
        
        Gson gson = new Gson();
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        
        Chatgroup chatGroup = gson.fromJson(body, Chatgroup.class);
        
        //Codice hibernate per il salvataggio
        session.beginTransaction();
        session.save(chatGroup);
        session.getTransaction().commit();

        //deallochiamo le risorse
        session.close();
        sessionFactory.close();
        
        return Response.ok(chatGroup).build();
        
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id) {
        
        try {
            Gson g = new Gson();
            
            String newGroupName = "";  //inserire codice che prenda il nuovo nome della chat che verra modificato nella parte grafica e lo metta nella stringa
            
            
            String query = "UPDATE `chatgroup` SET `name` =" + newGroupName+ " WHERE `id` =" + id + ";"; //o al posto di id mettere message.getId();
            //message = g.fromJson(body, Message.class);
            PreparedStatement s1 = connection.prepareStatement(query);
            int r = s1.executeUpdate(query);
            
            
            //DatabaseManager.getInstance().setLog(log);
        } catch (SQLException ex) {
            Logger.getLogger(MessageFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Chatgroup find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public List<Chatgroup> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Chatgroup> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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