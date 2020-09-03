package cn.edu.swpu.info.college_website.common;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class showPictureImpl implements showPicture {
    @Override
    public void responseFile(HttpServletResponse response, File imageFile) throws IOException {
       InputStream in=new FileInputStream(imageFile);
        OutputStream out=response.getOutputStream();
        System.out.println("运行了");
        byte [] buffer = new byte[1024];
        int len=0;
        while((len = in.read(buffer)) > 0){
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        // 关闭输出流
        out.close();
    }
}
