package org.rldev.web.controllers;


import org.rldev.iotable.model.IoTable;
import org.rldev.service.IoUnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@SessionAttributes("iotable")
public class IoTableController {

    @Autowired private IoUnitsService ioUnitsService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView ioTableInfo(@ModelAttribute("iotable") IoTable ioTable) {

        ModelAndView modelAndView = new ModelAndView("info");


        modelAndView.addObject("duplicateAis",
                ioUnitsService.findEqualsByNumber(new ArrayList<>(ioTable.getAnalogInputs())));

        modelAndView.addObject("duplicateAis",
                ioUnitsService.findEqualsByNumber(new ArrayList<>(ioTable.getAnalogInputs())));

        modelAndView.addObject("duplicateDis",
                ioUnitsService.findEqualsByNumber(new ArrayList<>(ioTable.getDigitalInputs())));

        modelAndView.addObject("duplicateAos",
                ioUnitsService.findEqualsByNumber(new ArrayList<>(ioTable.getAnalogOutputs())));

        modelAndView.addObject("duplicateDos",
                ioUnitsService.findEqualsByNumber(new ArrayList<>(ioTable.getDigitalOutputs())));

        return modelAndView;
    }
}
