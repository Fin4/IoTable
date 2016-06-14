package org.rldev.web.controllers;

import org.rldev.iotable.model.IoTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("iotable")
public class AoTableController {

    @RequestMapping(value = "/aoTable", method = RequestMethod.GET)
    public ModelAndView aoTable(@ModelAttribute IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("iotables/aoTable");

        modelAndView.addObject(ioTable);

        return modelAndView;
    }
}
