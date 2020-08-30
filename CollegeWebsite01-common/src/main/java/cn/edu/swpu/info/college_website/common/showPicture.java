package cn.edu.swpu.info.college_website.common;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface showPicture {
    public void responseFile(HttpServletResponse response, File imageFile) throws IOException;
}
