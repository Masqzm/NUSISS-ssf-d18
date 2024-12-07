package ssf.day18.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import ssf.day18.config.Constants;

@Controller
@RequestMapping("/session")
public class SessionController {
    @GetMapping()
    public ModelAndView getSessionHome() {
        ModelAndView mav = new ModelAndView();
        
        mav.addObject("session", "session");
        mav.setViewName("session");

        return mav;
    }

    @GetMapping("/create")
    public ModelAndView getCreateSession() {
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("session-create");

        return mav;
    }
    
    // TODO: FIX THIS ERROR
    @PostMapping("/save")
    public ModelAndView postSaveSession(MultiValueMap<String, String> form, HttpSession sess) {
        ModelAndView mav = new ModelAndView();

        // Get list from session if avail
        List<String> namesList = Constants.getSessionNamesList(sess);
        List<String> dobList = Constants.getSessionDOBList(sess);

        // Add to list from form
        namesList.add(form.getFirst("name"));
        dobList.add(form.getFirst("dob"));

        // Save to session
        sess.setAttribute(Constants.SESS_ATTR_NAMESLIST, namesList);
        sess.setAttribute(Constants.SESS_ATTR_DOBLIST, dobList);

        mav.setViewName("session-list");

        return mav;
    }

    @GetMapping("/list")
    public ModelAndView getSessionList(HttpSession sess) {
        ModelAndView mav = new ModelAndView();

        // Get list from session if avail
        List<String> namesList = Constants.getSessionNamesList(sess);
        List<String> dobList = Constants.getSessionDOBList(sess);
        
        mav.addObject(Constants.SESS_ATTR_NAMESLIST, namesList);
        mav.addObject(Constants.SESS_ATTR_DOBLIST, dobList);
        mav.setViewName("session-list");

        return mav;
    }

    @PostMapping("/clear")
    public ModelAndView postClearSession(HttpSession sess) {
        ModelAndView mav = new ModelAndView();

        sess.invalidate();

        mav.addObject("status", "Sessions cleared!");
        mav.setViewName("session");

        return mav;
    }
}
