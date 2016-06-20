package org.rldev.web.controllers;

import org.rldev.iotable.codegenerators.CodeGenerator;
import org.rldev.iotable.codegenerators.exceptions.TemplateStringException;
import org.rldev.iotable.model.IoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@SessionAttributes("iotable")
public class AiCodeGenertorController {

    @Autowired private CodeGenerator simpleCodeGenerator;

    @RequestMapping(value = "/aiCodeMapper", method = RequestMethod.GET)
    public String provideGenerateCode(@ModelAttribute("iotable") IoTable ioTable, Model model) {

        model.addAttribute("iotable", ioTable);

        return "code/aiCodeMapper";
    }

    @RequestMapping(value = "/aiCodeMapper", method = RequestMethod.POST)
    public String generateCode(@ModelAttribute("iotable") IoTable ioTable,
                                     RedirectAttributes redirectAttributes, String template,
                               HttpServletResponse response) {

        try {
            List<String> strings = simpleCodeGenerator.generateCode(ioTable.getAnalogInputs(), template);
            redirectAttributes.addFlashAttribute("lines", strings);

        } catch (TemplateStringException e) {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to generate code => " + e.getMessage());
            return "redirect:/generatingError";
        }

        return "redirect:/aiGeneratedCode";
    }

    @RequestMapping(value = "/aiGeneratedCode", method = RequestMethod.GET)
    public String aiGeneratedCode(@ModelAttribute("iotable") IoTable ioTable) {

        return "code/aiGeneratedCode";
    }

    @RequestMapping(value = "/generatingError", method = RequestMethod.GET)
    public String generatingError() {
        return "code/generatingError";
    }

}