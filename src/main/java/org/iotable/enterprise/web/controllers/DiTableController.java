package org.iotable.enterprise.web.controllers;

import org.iotable.core.model.IoTable;
import org.iotable.enterprise.session.IoTableSessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@SessionAttributes("iotable")
public class DiTableController {

    @Autowired
    private IoTableSessionBean ioTable;

    private static final String IOTABLE_ATTR = "iotable";

    @RequestMapping(value = "/diTable", method = RequestMethod.GET)
    public ModelAndView diTable() {

        ModelAndView modelAndView = new ModelAndView("iotables/diTable");

        modelAndView.addObject(IOTABLE_ATTR, ioTable.getIoTable());

        return modelAndView;
    }
}
