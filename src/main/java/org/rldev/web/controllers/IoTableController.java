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

    @Autowired private EqualityService equalityService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView ioTableInfo(@ModelAttribute("iotable") IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("info");

        modelAndView.addObject("duplicateAis",
                equalityService.findDuplicates(new ArrayList<>(ioTable.getAnalogInputs())));

        modelAndView.addObject("duplicateAis",
                equalityService.findDuplicates(new ArrayList<>(ioTable.getAnalogInputs())));

        modelAndView.addObject("duplicateDis",
                equalityService.findDuplicates(new ArrayList<>(ioTable.getDiscreteInputs())));

        modelAndView.addObject("duplicateAos",
                equalityService.findDuplicates(new ArrayList<>(ioTable.getAnalogOutputs())));

        modelAndView.addObject("duplicateDos",
                equalityService.findDuplicates(new ArrayList<>(ioTable.getDiscreteOutputs())));

        return modelAndView;
    }
}
