package ssf.day18.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ssf.day18.models.Carpark;
import ssf.day18.restcontroller.CarparkRestController;

@Controller
@RequestMapping
public class CarparkController {
    @Autowired
    CarparkRestController cpRestController;

    @GetMapping("/carparks")
    public ModelAndView getCarparks() {
        ModelAndView mav = new ModelAndView();

        List<Carpark> cpList = cpRestController.getCarparks().getBody();

        // 200
        mav.setViewName("carpark-list");
        mav.addObject("carparksList", cpList);
        mav.setStatus(HttpStatusCode.valueOf(200));

        return mav;
    }
}
