package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements Serializable{
	
	enum Tip{osnovni, bronzani, srebrni, zlatni}
	
	private int clanarina;
	private List<Product> poseceniObjekti;
	private int bodovi;
	private Tip tip;
	
	public Customer() {
		super();
	}
	public int getClanarina() {
		return clanarina;
	}
	public void setClanarina(int clanarina) {
		this.clanarina = clanarina;
	}
	public List<Product> getPoseceniObjekti() {
		return poseceniObjekti;
	}
	public void setPoseceniObjekti(List<Product> poseceniObjekti) {
		this.poseceniObjekti = poseceniObjekti;
	}
	public int getBodovi() {
		return bodovi;
	}
	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}
	public Tip getTip() {
		return tip;
	}
	public void setTip() {
		if(getBodovi() < 100)
		{
			this.tip = Tip.osnovni;
		}
		else if(getBodovi() < 300)
		{
			this.tip = Tip.bronzani;
		}
		else if(getBodovi() < 1000)
		{
			this.tip = Tip.srebrni;
		}
		else
		{
			this.tip = Tip.zlatni;
		}
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Customer(String username, String password, String name, String surname, String birthday, String gender) {
		super(username, password, name, surname, birthday, gender);
		this.clanarina = 0;
		this.poseceniObjekti = new ArrayList<Product>();
		this.bodovi = 0;
		this.tip = Tip.osnovni;
	}






	private static final long serialVersionUID = -5056364576008785298L;
}
