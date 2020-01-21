package mobigest.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mobigest.beans.Login;
import mobigest.beans.User;
import mobigest.beans.navigation.NavigationDetails;
import mobigest.database.user.UserDAO;
import mobigest.tags.navigator.MenuNavigator;

@Controller
@Scope("session")
public class MainMenuController {

	@Autowired
	UserDAO loginService;

	private User user;

	private static final String LOGIN_VIEW = "login";

	@RequestMapping(value = { "/auth/login", "login", "/" }, method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView(LOGIN_VIEW);
		Login login = new Login();
		model.addObject("login", login);
		return model;
	}

	@RequestMapping(value = { "/auth/login", "login", "/" }, method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Login login) {

		ModelAndView model;
		this.user = loginService.validateUser(login);

		if (this.user.isSuccessLogon()) {
			model = new ModelAndView(LOGIN_VIEW);
			model.addObject("redirectUrl", "/main");

		} else {
			model = new ModelAndView(LOGIN_VIEW);
			model.addObject("login", login);
			request.setAttribute("infoMsg", user.getLogonMessage());
		}

		return model;
	}

	@RequestMapping(value = { "/main" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView executeMainMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (user == null) {
			response.sendRedirect("exit");
			return null;
		}

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		List<NavigationDetails> mainMenu = new MenuNavigator().createNavigationLinks(user.getTipAngajat(), request);

		model = new ModelAndView("mainMenu");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));
		model.addObject("menu", gson.toJson(mainMenu));

		return model;
	}

	@RequestMapping(value = "/incarcare", method = RequestMethod.GET)
	public ModelAndView executeIncarcareMasini(HttpServletRequest request, HttpServletResponse response) throws IOException {

		checkSessionStatus(response);

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("incarcare_masini");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView afisUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		checkSessionStatus(response);

		ModelAndView model;
		Gson gson = new GsonBuilder().create();

		model = new ModelAndView("user");
		model.addObject("user", user);
		model.addObject("userjson", gson.toJson(user));

		return model;
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String executeExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		user = null;
		return "redirect:/login";

	}

	@RequestMapping(value = "/mainMenu", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<NavigationDetails> getMainMenu(String tipUser, HttpServletRequest request) {
		return new MenuNavigator().createNavigationLinks(tipUser, request);
	}

	private void checkSessionStatus(HttpServletResponse response) throws IOException {
		if (user == null) {
			response.sendRedirect("exit");
		}
	}

}
