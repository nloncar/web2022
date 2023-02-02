package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Product;
import beans.TrainingSession;
import beans.User;
import dao.ProductDAO;
import dao.TrainingSessionDAO;

@Path("/sessions") 
public class TrainingSessionService {

	ServletContext ctx; 
	
	public TrainingSessionService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("trainingSessionDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainingSessionDAO", new TrainingSessionDAO(contextPath));
		}
	}
	
	@GET
	@Path("/getByUser")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingSession> getByUser(String username, @Context HttpServletRequest request) {
		TrainingSessionDAO dao = (TrainingSessionDAO) ctx.getAttribute("trainingSessionDAO");
		return dao.getByUser(username);
	}
	
	@GET
	@Path("/getByCoach")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<TrainingSession> getByCoach(String username, @Context HttpServletRequest request) {
		TrainingSessionDAO dao = (TrainingSessionDAO) ctx.getAttribute("trainingSessionDAO");
		return dao.getByCoach(username);
	}
}
