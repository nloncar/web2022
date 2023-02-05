package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Product;


public class ProductDAO {
	
	private HashMap<String, Product> products = new HashMap<String, Product>();
	private String contextPath;
	
	public ProductDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */
	public ProductDAO(String contextPath) {
		loadProducts(contextPath);
		this.contextPath = contextPath;
	}

	/***
	 * Vra�a sve proizvode.
	 * @return
	 */
	public Collection<Product> findAll() {
		return products.values();
	}

	/***
	 *  Vraca proizvod na osnovu njegovog id-a. 
	 *  @return Proizvod sa id-em ako postoji, u suprotnom null
	 */
	public Product findProduct(String id) {
		return products.containsKey(id) ? products.get(id) : null;
	}
	
	/***
	 * Dodaje proizvod u mapu proizvoda. Id novog proizvoda �e biti postavljen na maxPostojeciId + 1.
	 * @param product
	 */
	public Product save(Product product) {
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
		return product;
	}

	/**
	 * U�itava korisnike iz WebContent/users.txt fajla i dodaje ih u mapu {@link #products}.
	 * Klju� je id proizovda.
	 * @param contextPath Putanja do aplikacije u Tomcatu
	 */
	private void loadProducts(String contextPath) {
		BufferedReader in = null;
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
						.parseDouble(ocena), type) );
				System.out.println(type);
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
		}
		
	}
}
