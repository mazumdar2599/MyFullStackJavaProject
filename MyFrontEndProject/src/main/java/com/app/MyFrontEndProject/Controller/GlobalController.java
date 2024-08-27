package com.app.MyFrontEndProject.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.app.MyBackEndProject.DAO.UserDAO;
import com.app.MyBackEndProject.Modal.User;
import com.app.MyFrontEndProject.model.UserModel;


@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserDAO userDAO;

	UserModel userModel;

	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		if (session.getAttribute("userModel") == null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			User user = userDAO.getUser(authentication.getName());
			
			if (user != null) {
				System.out.println(user.toString());
				userModel = new UserModel();
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				userModel.setId(user.getId());

				if (userModel.getRole().equals("USER")) {
					userModel.setCart(user.getCart());
				}

				session.setAttribute("userModel", userModel);
				return userModel;
			}
		}

		return (UserModel) session.getAttribute("userModel");
	}

}
