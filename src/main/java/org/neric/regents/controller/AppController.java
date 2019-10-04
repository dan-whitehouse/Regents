package org.neric.regents.controller;

import org.neric.regents.model.User;
import org.neric.regents.test.UserPassword;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController extends AbstractController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        if(isCurrentAuthenticationAnonymous()) {
            return "app/login";
        }
        model.addAttribute("loggedinusername", getPrincipal());
        return "app/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, ModelMap model) {
        if(isCurrentAuthenticationAnonymous()) {
            if(error != null) {
                model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
            }

            if(logout != null) {
                model.addAttribute("msg", "You have been logged out successfully.");
            }
            return "app/login";
        }
        else {
            return "redirect:/";
        }
    }


    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);

        //https://www.mkyong.com/spring-security/spring-security-limit-login-attempts-example/
        //https://stackoverflow.com/questions/29725829/spring-security-logout-lock-or-disable-user-by-name
        String error;
        if(exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        }
        else if(exception instanceof LockedException) {
            error = exception.getMessage();
        }
        else {
            error = "Invalid username and password!";
        }

        return error;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    /* PASSWORD RESET */
    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.GET)
    public String editUserPassword(ModelMap model) {
        User user = userService.findByUsername(getPrincipal());
        UserPassword userPassword = new UserPassword(user.getId());
        model.addAttribute("userPassword", userPassword);
        model.addAttribute("loggedinusername", getPrincipal());
        return "app/changePassword";
    }

    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.POST)
    public String updateUserPassword(UserPassword userPassword, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            for(ObjectError error : result.getAllErrors()) {
                System.err.println(error.getObjectName() + " | " + error.getCode() + " | " + error.getDefaultMessage());
            }
            return "app/changePassword";
        }

        userService.updatePassword(userPassword);

        model.addAttribute("success", "User: " + getPrincipal() + "'s password was updated successfully.");
        model.addAttribute("returnLink", "/");
        model.addAttribute("returnLinkText", "Users");
        model.addAttribute("loggedinusername", getPrincipal());
        return "message/success";
    }
}