package cn.edu.swpu.info.college_website.dao.base;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

abstract class Mybaties {
//    @Resource
//    SqlSessionFactory sqlSessionFactory;
    @Resource
    SqlSessionTemplate sqlSessionTemplate;

    /**
     *添加新对象
     * @param statement
     * @param object
     * @return
     */
    protected int insert(String statement,Object object){
        int result=0;
        try {
            if (object!=null){
                //SqlSession sqlSession=sqlSessionFactory.openSession(true);
                result=sqlSessionTemplate.insert(statement,object);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    /**
     * 删除相关对象
     * @param statement
     * @param object
     * @return 删除操作影响的行数
     */
    protected int delete(String statement,Object object){
        int result=0;
        try {
            //SqlSession sqlSession=sqlSessionFactory.openSession(true);
            result=sqlSessionTemplate.delete(statement,object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新操作
     * @param statement
     * @param object
     * @return 返回更新的行数
     */
    protected int update(String statement,Object object){
        int result=0;
        try {
           // SqlSession sqlSession=sqlSessionFactory.openSession(true);
            result=sqlSessionTemplate.update(statement,object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询某个对象
     * @param statement
     * @param object
     * @param <T>
     * @return
     */
    protected  <T> T select(String statement,Object object){
        T t=null;
        try{
            //SqlSession sqlSession=sqlSessionFactory.openSession();
            t=sqlSessionTemplate.selectOne(statement,object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }
    protected Integer select(String statement){
        int t=0;
        try{
           // SqlSession sqlSession=sqlSessionFactory.openSession();
            t=sqlSessionTemplate.selectOne(statement);
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 查询列表
     * @param statement
     * @param object
     * @param <T>
     * @return
     */
    protected <T> List<T> selectList(String statement, Object object){
        List<T> objectList=null;
        try{
           // SqlSession sqlSession=sqlSessionFactory.openSession();
            objectList=sqlSessionTemplate.selectList(statement,object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return objectList;
    }

    /**
     * 返回查询map（主要用于登录验证）
     * @param statement
     * @param object
     * @param mapKey
     * @param <T>
     * @param <Key>
     * @return
     */
    protected <T,Key> Map<T ,Key> selectMap(String statement,Object object,String mapKey){
        Map<T,Key> objectMap=null;
        try{
            //SqlSession sqlSession=sqlSessionFactory.openSession();
            objectMap=sqlSessionTemplate.selectMap(statement,object,mapKey);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return objectMap;
    }

}
