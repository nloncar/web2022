package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Customer;
import beans.Product;


public class ProductDAO {
	
	private HashMap<String, Product> products = new HashMap<String, Product>();
	private String contextPath;
	
	public ProductDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Može se pristupiti samo iz servleta.
	 */
	public ProductDAO(String contextPath) {
		loadProducts(contextPath);
		this.contextPath = contextPath;
	}

	/***
	 * Vraæa sve proizvode.
	 * @return
	 */
	public Collection<Product> findAll() {
		return products.values();
	}

	/***
	 *  Vraca proizvod na osnovu njegovog id-a. 
	 *  @return Proizvod sa id-em ako postoji, u suprotnom null
	 
	public Product findProduct(String naziv) {
		return products.containsKey(naziv) ? products.get(naziv) : null;
	}*/
	
	public Collection<Product> findProduct(String naziv) {
		
		ArrayList<Product> ret=new ArrayList<Product>();
		
		for(Product r : products.values())
		{
			if(r.getName().equals(naziv) )
			{
				ret.add(r);
				System.out.println("Naziv pret " + r.getName());
			}
		}
		
		return ret;
	}
	
	/***
	 * Dodaje proizvod u mapu proizvoda. Id novog proizvoda æe biti postavljen na maxPostojeciId + 1.
	 * @param product
	 */
	public Product save(Product product) {

		System.out.println("Naziv dodaj " + product.getName());
		Integer maxId = -1;
		for (String id : products.keySet()) {
			int idNum =Integer.parseInt(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		product.setId(maxId.toString());
		products.put(product.getId(), product);
		writeProducts();
		return product;
	}
	
	public boolean writeProducts()
	{
		File file = new File(this.contextPath + "/products.json");
		System.out.println(this.contextPath);
		
		if(!file.exists()){
	     	   try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	}
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(file, products);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
}

	/**
	 * Uèitava objekte iz WebContent/products.txt fajla i dodaje ih u mapu {@link #products}.
	 * Kljuè je id proizovda.
	 * @param contextPath Putanja do aplikacije u Tomcatu
	 */
	private void loadProducts(String contextPath) {
		
		File file = new File(contextPath + "/products.json");
		try {
			ObjectMapper mapper = new ObjectMapper();
			products = mapper.readValue(file, new TypeReference<Map<String, Product>>(){});
			System.out.println("Loaded customers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*BufferedReader in = null;
		try {
			File file = new File(contextPath + "/products.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, id = "", name = "", ocena = "", type = "";
			String sadrzaj, status = "", radno_vreme = "";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();
					name = st.nextToken().trim();
					ocena = st.nextToken().trim();
					type = st.nextToken().trim();
					sadrzaj = st.nextToken().trim();
					status = st.nextToken().trim();
					radno_vreme = st.nextToken().trim();
				}
				products.put(id, new Product(id, name, Double
						.parseDouble(ocena), type, status, radno_vreme) );
				System.out.println(name);
				System.out.println(radno_vreme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}*/
		
	}
}
