package org.rldev.web.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rldev.config.AppConfig;
import org.rldev.iotable.document.XlsxIoTable;
import org.rldev.iotable.model.IoTable;
import org.rldev.iotable.model.ioUnits.*;
import org.rldev.iotable.model.ioUnits.typeadapters.AnalogInputTypeAdapter;
import org.rldev.iotable.model.ioUnits.typeadapters.AnalogOutputTypeAdapter;
import org.rldev.iotable.model.ioUnits.typeadapters.DigitalInputTypeAdapter;
import org.rldev.iotable.model.ioUnits.typeadapters.DigitalOutputTypeAdapter;
import org.rldev.service.EqualatorService;
import org.rldev.service.IoTableService;
import org.rldev.service.IoUnitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes({"iotable"})
public class UploadController {

    @Autowired private IoTableService ioTableService;

    @Autowired private IoUnitsService ioUnitsService;

    @RequestMapping(method = RequestMethod.GET, value = "/upload")
    public String provideUploadInfo(Model model, SessionStatus sessionStatus) {

        sessionStatus.setComplete();

        File rootFolder = new File(AppConfig.IOTABLES_DIRECTORY);

        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName())
                        .collect(Collectors.toList())
        );

        return "uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (name.contains("/")) {
            redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
            return "redirect:/upload";
        }
        if (name.startsWith("/")) {
            redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
            return "redirect:/upload";
        }

        if (!file.isEmpty()) {
            try {
/*                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(AppConfig.IOTABLES_DIRECTORY + "/" + name)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();*/

                XlsxIoTable xlsxIoTable = new XlsxIoTable(new XSSFWorkbook(file.getInputStream()));

                IoTable validIoTable = ioTableService.validate(ioTableService.getFromWorkbook(xlsxIoTable));

                httpSession.setAttribute("iotable", validIoTable);
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " => " + e.getMessage());
                return "redirect:/uploadingError";
            }
        }
        else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload " + name + " because the file was empty");
            return "redirect:/uploadingError";
        }

        return "info";
    }

    @RequestMapping(value = "/uploadingError", method = RequestMethod.GET)
    public String uploadingError() {

        return "errors/uploadingError";
    }
}