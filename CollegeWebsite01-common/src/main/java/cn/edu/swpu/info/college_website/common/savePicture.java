package cn.edu.swpu.info.college_website.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class savePicture {
    public static String uploadPicture(MultipartFile file){
        if (file!=null){
            String fileName=file.getOriginalFilename();
            String suffix=fileName.substring(fileName.lastIndexOf("."));
            String newFileName= UUID.randomUUID().toString()+suffix;
            String filePath=picturePath.path+newFileName;
            File save=new File(filePath);
            try{
                file.transferTo(save);
            }catch (Exception e ){
                e.printStackTrace();
            }
            return newFileName;
        }
        return null;
    }
}
