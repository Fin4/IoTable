package org.iotable.enterprise.web.controllers;


import org.iotable.enterprise.service.EqualityService;
import org.iotable.enterprise.session.IoTableSessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@SessionAttributes("iotable")
public class IoTableController {

    @Autowired private EqualityService equalityService;

    @Autowired private IoTableSessionBean ioTable;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView ioTableInfo() {

        ModelAndView modelAndView = new ModelAndView("info");

        modelAndView.addObject("duplicateAis",
                equalityService.findDuplicateAis(ioTable.getIoTable()));

        modelAndView.addObject("duplicateDis",
                equalityService.findDuplicateDis(ioTable.getIoTable()));

        modelAndView.addObject("duplicateAos",
                equalityService.findDuplicateAos(ioTable.getIoTable()));

        modelAndView.addObject("duplicateDos",
                equalityService.findDuplicateDos(ioTable.getIoTable()));

        return modelAndView;
    }
}
