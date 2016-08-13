package org.iotable.enterprise.web.controllers.mappers;

import org.iotable.core.mappers.DiMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
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
public class DiMapperController {

    @Autowired
    private DiMapper diMapper;

    @RequestMapping(value = "/diCodeMapper", method = RequestMethod.GET)
    public String provideGenerateDiCode(@ModelAttribute("iotable") IoTable ioTable, Model model) {

        return "code/diCodeMapper";
    }

    @RequestMapping(value = "/diCodeMapper", method = RequestMethod.POST)
    public void generateDiCode(@ModelAttribute("iotable") IoTable ioTable,
                             String template,
                             HttpServletResponse response) {

        try {
            List<String> strings = diMapper.generateCode(ioTable.getDiscreteInputs(), template);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + "diCode.txt" + "\"");
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
