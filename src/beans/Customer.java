package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements Serializable{
	
	enum Tip{osnovni, bronzani, srebrni, zlatni}
	
	private Membership membership;
	private ArrayList<Product> poseceniObjekti;
	private int bodovi;
	private Tip tip;
	
	
	
	public Customer()
	{
		super();
	}
	
	public Customer(String username, String password, String name, String surname, String birthday, String gender, String type) {
		super(username, password, name, surname, birthday, gender, type);
		this.membership = null;
		this.poseceniObjekti = new ArrayList<Product>();
		this.bodovi = 0;
		this.tip = Tip.osnovni;
	}
	
	public Customer(String username, String password, String name, String surname, String birthday, String gender, String type,
			Membership membership, ArrayList<Product> poseceniObjekti, int bodovi, String tip) {
		super(username, password, name, surname, birthday, gender, type);
		this.membership = membership;
		this.poseceniObjekti = poseceniObjekti;
		this.bodovi = bodovi;
		this.tip = Tip.valueOf(tip);
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}


	public Membership getMembership() {
		return membership;
	}
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	public ArrayList<Product> getPoseceniObjekti() {
		return poseceniObjekti;
	}
	public void setPoseceniObjekti(ArrayList<Product> poseceniObjekti) {
		this.poseceniObjekti = poseceniObjekti;
	}
	public int getBodovi() {
		return bodovi;
	}
	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}
	public String getTip() {
		return tip.toString();
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




	private static final long serialVersionUID = -5056364576008785298L;
}
