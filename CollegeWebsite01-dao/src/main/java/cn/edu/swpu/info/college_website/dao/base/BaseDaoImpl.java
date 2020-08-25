package cn.edu.swpu.info.college_website.dao.base;

import cn.edu.swpu.info.MessagesExample;
import cn.edu.swpu.info.college_website.dao.Impl.MessageDaoImpl;

import java.io.Serializable;
import java.util.List;

public abstract class BaseDaoImpl<T,Key extends Serializable> extends Mybaties implements BaseDao<T,Key> {
    private final static  String DEFAULT_INSERT_KEY="insertObject";
    private final static  String DEFAULT_DELETE_Key="deleteByKey";
    private  final  static String DEFAULT_UPDATE_Contion="updateContion";
    private  final static String DEFAULT_SELECT_Contion="selectObjectList";
    private static final String DEFAULT_SELECT_KEY = "selectOneByKey";
    //private static final String DEFAULT_SELECT_ARRY_KEY="selec"
   /**
     * 获取命名前缀
     * @param statement
     * @return
     */
    public abstract String getNameSpace(String statement);

    /**
     *增加对象
     * @param t 条件可以是多个
     * @return
     */
    @Override
    public int insertObject(T... t) {
        int result=0;
        if (t==null && t.length<=0){
            return 0;
        }
        for (T t1: t) {
            if (t1!=null){
                result+=this.insert(getNameSpace(DEFAULT_INSERT_KEY),t1);
               // MessagesExample.Criteria criteria=
            }
        }
        return result;
    }

    @Override
    public int deleteObjectByKey(Key key) {

        return this.delete(getNameSpace(DEFAULT_DELETE_Key),key);
    }

    @Override
    public int updateObject(T t) {
        return this.update(getNameSpace(DEFAULT_UPDATE_Contion),t);
    }

    @Override
    public List<T> selectObiectList(T contion) {
        if (contion==null){
            return null;
        }
        return this.selectList(getNameSpace(DEFAULT_SELECT_Contion),contion);
    }
    public T selectObject(Key key){
        return this.select(getNameSpace(DEFAULT_SELECT_KEY),key);
    }

    @Override
    public List<T> selectObjectListbyKey(Key... key) {
        List<T> list=null;
        if (key.length>0){
            for (Key key1: key) {
                list.add(this.select(getNameSpace(DEFAULT_SELECT_KEY),key1));
            }
            return list;
        }
        return null;
    }
}
