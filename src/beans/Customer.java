package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable{
	
	enum Tip{osnovni, bronzani, srebrni, zlatni}
	
	private String username;
	private Membership membership;
	private ArrayList<String> poseceniObjekti;
	private int bodovi;
	private Tip tip;
	

	
	

	public Customer(String username, Membership membership, ArrayList<String> poseceniObjekti, int bodovi, String tip) {
		super();
		this.username = username;
		this.membership = membership;
		this.poseceniObjekti = poseceniObjekti;
		this.bodovi = bodovi;
		this.tip = Tip.valueOf(tip);
	}
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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
	public ArrayList<String> getPoseceniObjekti() {
		return poseceniObjekti;
	}
	public void setPoseceniObjekti(ArrayList<String> poseceniObjekti) {
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
