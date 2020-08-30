package cn.edu.swpu.info.college_website.dao.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T,Key extends Serializable> {
    /**
     * 添加对象
     * @param t 条件可以是多个
     * @return 影响的行数
     */
   int  insertObject(T... t);

    /**
     * 通过主键(数组)删除对象
     * @param keys
     * @return 删除的行数
     */
   int  deleteObjectByKey(List<Key> keys);

    /**
     * 通过对象进行更新
     * @param t
     * @return 返回影响行数
     */
   int  updateObject(T t);

    /**
     * 通过非主键条件进行查询
     * @param contion
     * @return
     */
   List<T> selectObiectList(T contion);

    /**
     * 通过主键进行查询
     * @param key
     * @return
     */
   T selectObject(Key key);

    /**
     * 根据数组主键，查找相应的对象，并封装为链表
     * @param key
     * @return
     */
   List<T> selectObjectListbyKey(Key... key);

    /**
     * 查询最大的messageid
     *
     * @return
     */
   int selectMaxId();
}
