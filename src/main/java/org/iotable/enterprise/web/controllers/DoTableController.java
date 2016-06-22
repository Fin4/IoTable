package org.iotable.enterprise.web.controllers;

import org.iotable.core.model.IoTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("iotable")
public class DoTableController {

    @RequestMapping(value = "/doTable", method = RequestMethod.GET)
    public ModelAndView doTable(@ModelAttribute IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("iotables/doTable");

        modelAndView.addObject(ioTable);

        return modelAndView;
    }
}
