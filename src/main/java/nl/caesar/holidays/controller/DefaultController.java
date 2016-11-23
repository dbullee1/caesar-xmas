package nl.caesar.holidays.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import nl.caesar.holidays.game.model.Branch;
import nl.caesar.holidays.game.model.CookieUtil;

@Controller
@RequestMapping("/index")
public class DefaultController {

	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "branch", required = false) String theme) {
		Cookie branch = CookieUtil.getCookie(request, "branch");
		if (branch == null || branch.getValue().equals(Branch.DEFAULT.name())) {
			Branch modelTheme = null;
			if (!StringUtils.isEmpty(theme)) {
				try {
					modelTheme = Branch.valueOf(theme.toUpperCase());
				} catch (IllegalArgumentException e) {
					// falling back to default Theme
				}
			}

			if (modelTheme == null) {
				modelTheme = Branch.DEFAULT;
			}
			response.addCookie(new Cookie("branch", modelTheme.name()));

		}
		return "index";
	}
}
