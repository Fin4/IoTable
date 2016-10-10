package org.iotable.enterprise.web.controllers;

import org.iotable.enterprise.session.IoTableSessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoTableController {

    @Autowired private IoTableSessionBean ioTable;

    private static final String IOTABLE_ATTR = "iotable";

    @RequestMapping(value = "/doTable", method = RequestMethod.GET)
    public ModelAndView doTable() {

        ModelAndView modelAndView = new ModelAndView("iotables/doTable");

        modelAndView.addObject(IOTABLE_ATTR, ioTable.getIoTable());

        return modelAndView;
    }
}
