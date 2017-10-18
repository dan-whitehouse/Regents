package org.neric.regents.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.neric.regents.controller.AbstractController;
import org.neric.regents.model.UploadedFile;
import org.neric.regents.service.FileUploadService;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class FileUploadController extends AbstractController {

       @Autowired
       private FileUploadService uploadService;


       @RequestMapping("/dev/csv")
       public String home(Model model, HttpServletRequest request) {
              model.addAttribute("postUrl", request.getRequestURL()+"/upload");
              return "csv";
       }

       @RequestMapping(value = "/dev/csv/upload", method = RequestMethod.POST)
       @ResponseBody
       public String upload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException
       {
           if(StringUtils.endsWith(file.getOriginalFilename(), ".csv"))
           {
              // Save the file to local disk
              saveFileToLocalDisk(file);

              UploadedFile fileInfo = getUploadedFileInfo(file);

              // Save the file info to database
              fileInfo = saveFileToDatabase(fileInfo);
              redirectAttributes.addFlashAttribute("message","You successfully uploaded: " + file.getOriginalFilename() + "!");
           }
           else
           {
              redirectAttributes.addFlashAttribute("message","You failed to upload: " + file.getOriginalFilename() + "!");
           }
           return "redirect:/dev/csv";
       }

       @RequestMapping(value = { "/dev/csv/list" })
       public String listBooks(Map<String, Object> map) {
              map.put("fileList", uploadService.listFiles());
              return "/dev/csv/files";
       }

       @RequestMapping(value = "/dev/csv/{fileId}", method = RequestMethod.GET)
       public void getFile(HttpServletResponse response, @PathVariable Long fileId) {
              UploadedFile dataFile = uploadService.getFile(fileId);
              File file = new File(dataFile.getLocation(), dataFile.getName());

              try {
                     response.setContentType(dataFile.getType());
                     response.setHeader("Content-disposition", "attachment; filename=\"" + dataFile.getName() + "\"");
                     FileCopyUtils.copy(FileUtils.readFileToByteArray(file), response.getOutputStream());
              }
              catch (IOException e) {
                     e.printStackTrace();
              }
       }

       private void saveFileToLocalDisk(MultipartFile multipartFile)throws IOException, FileNotFoundException {
              String outputFileName = getOutputFilename(multipartFile);
              FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
       }

       private UploadedFile saveFileToDatabase(UploadedFile uploadedFile) {
              return uploadService.saveFile(uploadedFile);
       }

       private String getOutputFilename(MultipartFile multipartFile) {
              return getDestinationLocation() + multipartFile.getOriginalFilename();
       }

       private UploadedFile getUploadedFileInfo(MultipartFile multipartFile) throws IOException {
              UploadedFile fileInfo = new UploadedFile();
              fileInfo.setName(multipartFile.getOriginalFilename());
              fileInfo.setSize(multipartFile.getSize());
              fileInfo.setType(multipartFile.getContentType());
              fileInfo.setLocation(getDestinationLocation());

              return fileInfo;
       }

       private String getDestinationLocation() {
              return "F:/uploaded-files/";
       }
}