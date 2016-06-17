package org.rldev.web.controllers;

import org.rldev.iotable.model.IoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("iotable")
public class AiTableController {

    @RequestMapping(value = "/aiTable", method = RequestMethod.GET)
    public ModelAndView aiTable(@ModelAttribute IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("iotables/aiTable");

        modelAndView.addObject(ioTable);

        return modelAndView;
    }
}
