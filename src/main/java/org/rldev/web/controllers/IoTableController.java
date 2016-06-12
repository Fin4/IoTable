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
public class IoTableController {

    @RequestMapping(value = "/info")
    public String ioTableInfo(@ModelAttribute IoTable ioTable) {

        return "info";
    }

    @RequestMapping(value = "/aiTable", method = RequestMethod.GET)
    public ModelAndView aiTable(@ModelAttribute IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("iotables/aiTable");

        modelAndView.addObject(ioTable);

        return modelAndView;
    }

    @RequestMapping(value = "/diTable", method = RequestMethod.GET)
    public ModelAndView diTable(@ModelAttribute IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("iotables/diTable");

        modelAndView.addObject(ioTable);

        return modelAndView;
    }

    @RequestMapping(value = "/aoTable", method = RequestMethod.GET)
    public ModelAndView aoTable(@ModelAttribute IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("iotables/aoTable");

        modelAndView.addObject(ioTable);

        return modelAndView;
    }

    @RequestMapping(value = "/doTable", method = RequestMethod.GET)
    public ModelAndView doTable(@ModelAttribute IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("iotables/doTable");

        modelAndView.addObject(ioTable);

        return modelAndView;
    }
}
