package org.iotable.enterprise.web.controllers.mappers;

import org.iotable.core.mappers.AiMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.IoTable;
import org.iotable.enterprise.session.IoTableSessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class AiMapperController {

    @Autowired private AiMapper aiMapper;

    @Autowired private IoTableSessionBean ioTable;

    @RequestMapping(value = "/aiCodeMapper", method = RequestMethod.GET)
    public String provideGenerateCode(Model model) {

        //model.addAttribute("iotable", ioTable);

        return "code/aiCodeMapper";
    }

    @RequestMapping(value = "/aiCodeMapper", method = RequestMethod.POST)
    public void generateAiCode(String template, HttpServletResponse response) {

        try {
            List<String> strings = aiMapper.generateCode(ioTable.getIoTable().getAnalogInputs(), template);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + "aiCode.txt" + "\"");

            OutputStream os = response.getOutputStream();
            for(String s : strings) {
                String resultString = s + "\n";
                os.write(resultString.getBytes());
            }
            os.flush();
            os.close();
        } catch (TemplateStringException | IOException e) {
            /*redirectAttributes.addFlashAttribute("message",
                    "You failed to generate code => " + e.getMessage());
            return "redirect:/generatingError";*/
        }
    }

    @RequestMapping(value = "/aiGeneratedCode", method = RequestMethod.GET)
    public String aiGeneratedCode() {

        return "code/aiGeneratedCode";
    }

    @RequestMapping(value = "/generatingError", method = RequestMethod.GET)
    public String generatingError() {
        return "code/generatingError";
    }

}