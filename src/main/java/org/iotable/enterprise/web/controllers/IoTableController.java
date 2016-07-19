package org.iotable.enterprise.web.controllers;


import org.iotable.core.model.IoTable;
import org.iotable.enterprise.service.EqualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@SessionAttributes("iotable")
public class IoTableController {

    @Autowired private EqualityService equalityService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView ioTableInfo(@ModelAttribute("iotable") IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("info");

       /* modelAndView.addObject("duplicateAis",
                equalityService.findDuplicates(ioTable.getAnalogInputs()));

        modelAndView.addObject("duplicateDis",
                equalityService.findDuplicates(ioTable.getDiscreteInputs()));

        modelAndView.addObject("duplicateAos",
                equalityService.findDuplicates(new ArrayList<>(ioTable.getAnalogOutputs())));

        modelAndView.addObject("duplicateDos",
                equalityService.findDuplicates(new ArrayList<>(ioTable.getDiscreteOutputs())));*/

        return modelAndView;
    }
}
