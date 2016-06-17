package org.rldev.web.controllers;


import org.rldev.iotable.model.IoTable;
import org.rldev.service.EqualityService;
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

    @Autowired private EqualityService equalatorService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView ioTableInfo(@ModelAttribute("iotable") IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("info");

        modelAndView.addObject("duplicateAis",
                equalatorService.findEqualsByNumber(new ArrayList<>(ioTable.getAnalogInputs())));

        modelAndView.addObject("duplicateAis",
                equalatorService.findEqualsByNumber(new ArrayList<>(ioTable.getAnalogInputs())));

        modelAndView.addObject("duplicateDis",
                equalatorService.findEqualsByNumber(new ArrayList<>(ioTable.getDigitalInputs())));

        modelAndView.addObject("duplicateAos",
                equalatorService.findEqualsByNumber(new ArrayList<>(ioTable.getAnalogOutputs())));

        modelAndView.addObject("duplicateDos",
                equalatorService.findEqualsByNumber(new ArrayList<>(ioTable.getDigitalOutputs())));

        return modelAndView;
    }
}
