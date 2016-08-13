package org.iotable.enterprise.web.controllers;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.iotable.core.document.IoTableDocument;
import org.iotable.core.document.XlsxIoTable;
import org.iotable.core.model.IoTable;
import org.iotable.enterprise.service.IoTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UploadController {

    @Autowired private IoTableService ioTableService;

    @RequestMapping(method = RequestMethod.GET, value = "/upload")
    public String provideUploadInfo(Model model, SessionStatus sessionStatus) {

        //sessionStatus.setComplete();

/*        File rootFolder = new File(org.iotable.enterprise.config.AppConfig.IOTABLES_DIRECTORY);

        List<String> fileNames = Arrays.stream(rootFolder.listFiles())
                .map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles())
                        .sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName())
                        .collect(Collectors.toList())
        );*/

        return "uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(/*@RequestParam("name") String name,*/
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, HttpSession httpSession) {

        httpSession.removeAttribute("iotable");

        if (!file.isEmpty()) {
            try {
                IoTableDocument xlsxIoTable = new XlsxIoTable(new XSSFWorkbook(file.getInputStream()));

                IoTable ioTable = ioTableService.validate(ioTableService.getFromWorkbook(xlsxIoTable));

                httpSession.setAttribute("iotable", ioTable);
            }
            catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload file  => " + e.getMessage());
                return "redirect:uploadingError";
            }
        }
        else {
            redirectAttributes.addFlashAttribute("message",
                    "You failed to upload because the file was empty");
            return "redirect:uploadingError";
        }

        return "redirect:/info";
    }

    @RequestMapping(value = "/uploadingError", method = RequestMethod.GET)
    public String uploadingError() {

        return "errors/uploadingError";
    }
}