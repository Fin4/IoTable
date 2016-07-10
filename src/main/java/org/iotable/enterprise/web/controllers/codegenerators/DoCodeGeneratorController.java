/*
package org.iotable.enterprise.web.controllers.codegenerators;

import org.iotable.core.codegenerators.CodeGenerator;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.IoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@SessionAttributes("iotable")
public class DoCodeGeneratorController {

    @Autowired
    private CodeGenerator simpleCodeGenerator;

    @RequestMapping(value = "/doCodeMapper", method = RequestMethod.GET)
    public String provideGenerateCode(@ModelAttribute("iotable") IoTable ioTable, Model model) {

        return "code/doCodeMapper";
    }

    @RequestMapping(value = "/doCodeMapper", method = RequestMethod.POST)
    public void generateDoCode(@ModelAttribute("iotable") IoTable ioTable,
                             String template,
                             HttpServletResponse response) {

        try {
            List<String> strings = simpleCodeGenerator.generateCode(ioTable.getDiscreteOutputs(), template);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + "doCode.txt" + "\"");
            OutputStream os = response.getOutputStream();
            for(String s : strings) {
                os.write(s.getBytes());
                os.write(System.lineSeparator().getBytes());
            }
            os.flush();
            os.close();
        } catch (TemplateStringException | IOException e) {
        }
    }
}
*/
