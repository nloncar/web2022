package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Product;
import dao.ProductDAO;

@Path("/products")  //u koji servis se preusmerava
public class ProductService {
	
	@Context
	ServletContext ctx;   //zajednicka baza pod, za smestanje deljenih proizvoda
	//inicjalizuje se u init, kad se startuje servis, ctx je ustvari mapa, kljuc i obj, da izbegnemo staticke liste
	
	public ProductService() {
	}
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira više puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("productDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("productDAO", new ProductDAO(contextPath));
			
			System.out.println( "Inicijalizacija: " + contextPath );
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)  //produces kada metoda vraca json fajl, consumes je ako preuzima
	public Collection<Product> getProducts() {
		ProductDAO dao = (ProductDAO) ctx.getAttribute("productDAO");
		return dao.findAll();
	}
	
	@PUT
	@Path("/find/{naziv}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Product> pretragaArtikla(@PathParam("naziv") String naziv) {
		ProductDAO dao = (ProductDAO) ctx.getAttribute("productDAO");
		return dao.findProduct(naziv);
	}
	
	@POST
	@Path("/addNew")
	@Produces(MediaType.APPLICATION_JSON)
	public Product addNewProduct(Product prod) {
		System.out.println("Naziv: " + prod.getName());
		System.out.println("Tip objekta: " + prod.getTip_objekta());
		ProductDAO dao = (ProductDAO) ctx.getAttribute("productDAO");
		return dao.save(prod);
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProducts(Product product) {
		ProductDAO dao = (ProductDAO) ctx.getAttribute("productDAO");
		return dao.save(product);
	}
}
