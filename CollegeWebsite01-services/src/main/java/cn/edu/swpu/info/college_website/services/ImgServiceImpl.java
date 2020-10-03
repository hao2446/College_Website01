package cn.edu.swpu.info.college_website.services;

import cn.edu.swpu.info.Img;
import cn.edu.swpu.info.college_website.dao.Impl.ImgDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImgServiceImpl {
    @Resource
    private ImgDaoImpl imgDaoImpl;

    /**
     * 添加轮播图片信息
     * @param img
     * @return
     */
    public int insertImg(Img img){
        if (img!=null){
            int number=imgDaoImpl.selectMaxId();
            img.setId(number+1);
            return imgDaoImpl.insertObject(img);
        }
        return 0;
    }

    /**
     * 查询所有的轮播图片信息
     * @return
     */
    public List<Img> selectImgs(){
        Img img=new Img();
        return imgDaoImpl.selectObiectList(img);
    }

    /**
     * 根据主键查询相应的轮播图片信息
     * @param key
     * @return
     */
    public Img selectImg(Integer key){
        if (key!=null && key!=0){
            return imgDaoImpl.selectObject(key);
        }
        return null;
    }

    /**
     * 根据之间删除相应轮播图片的信息
     * @param key
     * @return
     */
    public int deleteImg(Integer key){
        if (key!=null && key!=0){
            return imgDaoImpl.deleteObjectByKey(key);
        }
        return 0;
    }
}
