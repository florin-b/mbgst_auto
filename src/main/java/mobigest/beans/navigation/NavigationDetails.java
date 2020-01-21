package mobigest.beans.navigation;

import java.io.Serializable;

import mobigest.enums.EnumMeniu;

public class NavigationDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String link;
	private String text;
	private EnumMeniu nume;
	private int navNumber;

	public NavigationDetails() {

	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public EnumMeniu getNume() {
		return nume;
	}

	public void setNume(EnumMeniu nume) {
		this.nume = nume;
	}

	public int getNavNumber() {
		return navNumber;
	}

	public void setNavNumber(int navNumber) {
		this.navNumber = navNumber;
	}

	@Override
	public String toString() {
		return "NavigationDetails [link=" + link + ", text=" + text + ", nume=" + nume + ", navNumber=" + navNumber + "]";
	}
	
	

}
