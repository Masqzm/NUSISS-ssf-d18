package ssf.day18.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ssf.day18.models.Person;
import ssf.day18.services.PersonService;

@Controller
@RequestMapping
public class PersonController {
    @Autowired
    PersonService personSvc;

    @GetMapping(path={"/", "index"})
    public String getIndex(Model model) {
        Person p = new Person();

        model.addAttribute("person", p);

        return "register";
    }

    @PostMapping("/register")
    public String postPerson(@Valid Person person, BindingResult bindings, Model model) {
        // Go back to original page if form has invalid inputs
        if(bindings.hasErrors())
        {
            System.out.println("ERROR");
            return "register";
        }

        personSvc.createPerson(person);

        model.addAttribute("name", person.getFullName());

        return "register-success";
    }

    @GetMapping("persons")
    public String getPersonsList(Model model) {
        //personSvc.getPersonList();

        return "register-success";
    }
}
