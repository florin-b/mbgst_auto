package mobigest.tags.navigator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mobigest.beans.navigation.NavigationDetails;
import mobigest.enums.EnumMeniu;
import mobigest.helpers.HelperMeniu;


public class MenuNavigator {

	public List<NavigationDetails> createNavigationLinks(String tipUser,  HttpServletRequest request) {

		ArrayList<NavigationDetails> navigationLinks = new ArrayList<>();

		String root = request.getServletContext().getContextPath();

		NavigationDetails nd;

		nd = new NavigationDetails();
		nd.setLink(String.format("%s/user", root));
		nd.setText("Utilizator");
		nd.setNume(EnumMeniu.UTILIZATOR);
		navigationLinks.add(nd);

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

		return navigationLinks;

	}

}
