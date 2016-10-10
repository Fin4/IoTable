package org.iotable.enterprise.web.controllers.mappers;

import org.iotable.core.mappers.AoMapper;
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
public class AoMapperController {

    @Autowired private IoTableSessionBean ioTable;

    @Autowired
    private AoMapper aoMapper;

    @RequestMapping(value = "/aoCodeMapper", method = RequestMethod.GET)
    public String provideGenerateAoCode(Model model) {

        return "code/aoCodeMapper";
    }

    @RequestMapping(value = "/aoCodeMapper", method = RequestMethod.POST)
    public void generateAoCode(String template, HttpServletResponse response) {

        try {
            List<String> strings = aoMapper.generateCode(ioTable.getIoTable().getAnalogOutputs(), template);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + "aoCode.txt" + "\"");
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
