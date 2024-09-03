package com.ohgiraffers.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//ㄴ 파일은 타입이 정해져있다.

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @PostMapping("/single-file")
    // 파일 한개 전송
    public String singleFileUpload(
            // @RequestParam 생략가능, 생략하면 변수명 일치하도록 해야 한다.
            @RequestParam String singleFileDescription,
            // 파일 타입 정확하게 ; MultipartFile ,,, 가공이 되어서 파일 전달된다. 이 객체를 어떻게 다루는지
            @RequestParam MultipartFile singleFile,
            Model model
    ) {

        System.out.println("singleFileDescription: " + singleFileDescription);
        System.out.println("singleFile: " + singleFile);

        /* 서버로 전송 된 MultipartFile 객체를 서버에서 지정하는 경로에 저장한다. */
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);

        // 파일 저장할 디렉토리 존재 확인
        if(!dir.exists()) dir.mkdirs();

        /* 파일명이 중복 되면 해당 경로에서 덮어쓰기 될 수 있으므로 중복 되지 않는 이름으로 변경 */
        String originFileName = singleFile.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        String savedName = UUID.randomUUID() + ext;
        // UUID.randomUUID(숫자와 영어 등 하이픈이 섞여진 문자로 변경) + ext(확장자)

        try {
            /* 파일 저장 */
            // filePath + "/" + savedName => 경로 + 변경이름
            // 파일 저장에서 IOException 처리
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 완료");
        } catch (IOException e) {
            model.addAttribute("message", "파일 업로드 실패");
            throw new RuntimeException(e);
        }

        return "result";
    }



    // 파일 여러개 전송
    @PostMapping("/multi-file")
    public String multiFileUpload(
            @RequestParam String multiFilesDescription,
            @RequestParam List<MultipartFile> multiFiles,
            Model model
    ) {

        /* 서버로 전송 된 MultipartFile 객체를 서버에서 지정하는 경로에 저장한다. */
        String root = "src/main/resources/static";
        String filePath = root + "/uploadFiles";
        File dir = new File(filePath);

        if(!dir.exists()) dir.mkdirs();

        List<FileDTO> files = new ArrayList<>();

        try {

            /* 다중 파일이므로 반복문을 이용한 처리를 하고 저장한다. */
            for(MultipartFile multiFile : multiFiles) {

                /* 파일명이 중복 되면 해당 경로에서 덮어쓰기 될 수 있으므로 중복 되지 않는 이름으로 변경 */
                String originFileName = multiFile.getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedName = UUID.randomUUID() + ext;

                /* 파일 저장 */
                multiFile.transferTo(new File(filePath + "/" + savedName));

                /* 파일에 관한 정보를 FileDTO에 담아 List에 보관 */
                files.add(new FileDTO(originFileName, savedName, filePath, multiFilesDescription));

            }

            // files 정보는 DB에 insert 된다.
            model.addAttribute("message", "다중 파일 업로드 완료");

        } catch (IOException e) {
            /* 파일 저장이 중간에 실패할 경우 이전에 저장된 파일 삭제 */
            for(FileDTO file : files) new File(filePath + "/" + file.getSavedFileName()).delete();
            model.addAttribute("message", "다중 파일 업로드 실패");
//            throw new RuntimeException(e);
        }

        return "result";
    }












}