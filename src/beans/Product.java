package beans;

public class Product {
	
	private String id;
	private String name;
	private double price;
	private String tip_objekta;
	private String sadrzaj;
	private boolean status;
	private String logo;
	private double ocena;
	private String radno_vreme;
	
	
	public Product() {
		
	}

	public Product(String id, String name, double ocena, String tip_objekta) {
		this.id = id;
		this.name = name;
		this.ocena = ocena;
		this.tip_objekta = tip_objekta;
	}
	
	public String getTip_objekta() {
		return tip_objekta;
	}

	public void setTip_objekta(String tip_objekta) {
		this.tip_objekta = tip_objekta;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double o) {
		ocena = o;
	}

	public String getRadno_vreme() {
		return radno_vreme;
	}

	public void setRadno_vreme(String radno_vreme) {
		this.radno_vreme = radno_vreme;
	}


	public void setId(String i) {
		id = i;
	}

	public String getId() {
		return id;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setPrice(double p) {
		price = p;
	}

	public double getPrice() {
		return price;
	}

}
