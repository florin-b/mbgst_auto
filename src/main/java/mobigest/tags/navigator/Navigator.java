package mobigest.tags.navigator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import mobigest.beans.navigation.NavigationDetails;
import mobigest.enums.EnumMeniu;
import mobigest.helpers.HelperMeniu;


public class Navigator extends SimpleTagSupport {

	private List<NavigationDetails> navigationLinks;

	private String tipUser;
	private String codDepart;

	public String getTipUser() {
		return tipUser;
	}

	public void setTipUser(String tipUser) {
		this.tipUser = tipUser;
	}

	public List<NavigationDetails> getNavigationLinks() {
		return navigationLinks;
	}

	public void setNavigationLinks(List<NavigationDetails> navigationLinks) {
		this.navigationLinks = navigationLinks;
	}

	public String getCodDepart() {
		return codDepart;
	}

	public void setCodDepart(String codDepart) {
		this.codDepart = codDepart;
	}

	private void createNavigationLinks() {

		navigationLinks = new ArrayList<>();

		PageContext pageContext = (PageContext) getJspContext();
		String root = pageContext.getServletContext().getContextPath();

		NavigationDetails nd;

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/incarcare", root));
		nd.setText("Incarcare masini");
		nd.setNume(EnumMeniu.INCARCARE_MASINI);
		HelperMeniu.addMenuOption(navigationLinks, nd, tipUser);

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/exit", root));
		nd.setText("Iesire");
		nd.setNume(EnumMeniu.EXIT);
		navigationLinks.add(nd);

	}

	@Override
	public void doTag() throws JspException, IOException {

		createNavigationLinks();

		for (NavigationDetails nd : navigationLinks) {
			getJspContext().setAttribute("navdetails", nd);
			getJspBody().invoke(null);
		}
	}

}
