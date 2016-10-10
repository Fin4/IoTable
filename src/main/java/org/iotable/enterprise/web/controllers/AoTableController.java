package org.iotable.enterprise.web.controllers;

import org.iotable.enterprise.session.IoTableSessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AoTableController {

    @Autowired
    private IoTableSessionBean ioTable;

    private static final String IOTABLE_ATTR = "iotable";

    @RequestMapping(value = "/aoTable", method = RequestMethod.GET)
    public ModelAndView aoTable() {

        ModelAndView modelAndView = new ModelAndView("iotables/aoTable");

        modelAndView.addObject(IOTABLE_ATTR, ioTable.getIoTable());

        return modelAndView;
    }
}
