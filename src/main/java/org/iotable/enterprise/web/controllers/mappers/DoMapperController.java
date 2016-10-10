package org.iotable.enterprise.web.controllers.mappers;

import org.iotable.core.mappers.DoMapper;
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
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class DoMapperController {

    @Autowired private IoTableSessionBean ioTable;

    @Autowired
    private DoMapper doMapper;

    @RequestMapping(value = "/doCodeMapper", method = RequestMethod.GET)
    public String provideGenerateDoCode() {

        return "code/doCodeMapper";
    }

    @RequestMapping(value = "/doCodeMapper", method = RequestMethod.POST)
    public void generateDoCode(String template, HttpServletResponse response) {

        try {
            List<String> strings = doMapper.generateCode(ioTable.getIoTable().getDiscreteOutputs(), template);

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
