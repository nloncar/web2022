package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.User;
import dao.UserDAO;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
	
	@Context
	ServletContext ctx;

	public UserService() {
		
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("userDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("userDAO", new UserDAO(contextPath));
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		User loggedUser = userDao.find(user.getUsername(), user.getPassword());
		if (loggedUser == null) {
			return Response.status(400).entity("Invalid username and/or password").build();
		}
		request.getSession().setAttribute("user", loggedUser);
		System.out.println(((User) request.getSession().getAttribute("user")).getUsername());
		System.out.println("logged");
		return Response.status(200).build();
	}
	
	@POST
	@Path("/refresh")
	@Produces(MediaType.APPLICATION_JSON)
	public void refreshUser(@Context HttpServletRequest request)
	{
		
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public User register(User user, @Context HttpServletRequest request) {
		System.out.println("reg");
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		User userReg = userDao.RegisterUser(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getBirthday(), user.getGender());
		return userReg;
	}
	
	@POST
	@Path("/edit")
	@Produces(MediaType.APPLICATION_JSON)
	public User edit(User user, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		userDao.editUser(((User) request.getSession().getAttribute("user")).getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getBirthday(), user.getGender());
		
		User loggedUser = userDao.find(user.getUsername(), user.getPassword());
		
		request.getSession().setAttribute("user", loggedUser);
		return loggedUser;
	}
	
	@POST
	@Path("/logout")
	public void logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User currentUser(@Context HttpServletRequest request) {
		System.out.println("loaded");
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		User currentUser = (User) request.getSession().getAttribute("user");
		return currentUser;
	}
}
