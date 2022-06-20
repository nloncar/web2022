package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.UserDAO;
import beans.User;

@Path("/user")
public class UserService {

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
	@Produces(MediaType.APPLICATION_JSON)
	public User login(User user, @Context HttpServletRequest request) {
		System.out.print("login");
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		User loggedInUser = userDao.loggedInUser(user.getUsername(), user.getPassword());
		if (loggedInUser == null) {
			return null;
		}
		request.getSession().setAttribute("user", loggedInUser);
		return loggedInUser;
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public User register(User user, @Context HttpServletRequest request) {
		System.out.print("registered");
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		User regUser = userDao.registerUser(user);
		return regUser;
	}
}
