package cn.edu.swpu.info.college_website.dao.Impl;

import cn.edu.swpu.info.Img;
import cn.edu.swpu.info.college_website.dao.ImgDao;
import cn.edu.swpu.info.college_website.dao.base.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ImgDaoImpl extends BaseDaoImpl<Img,Integer> implements ImgDao  {
    public final static  String NAMESPACE="cn.edu.swpu.info.college_website.dao.Impl.ImgDaoImpl." ;
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }
}
