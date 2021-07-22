/*
 * Application: REal Estate E-Business System 
 * Version: 1.0
 * Date: 26/09/2020
 */
package sessionBeans;

import entities.IBaseEntity;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author ID:12118217 Name:Quyet Quang Quy
 * @author ID:12123838 Name:Samihana Gurung
 * This abstract class is handle insert update delete and some abstract methods are needed in general
 * Insert delete and update can be combine in one method call command by using generic method, 
 * but to reduce the complexity creating 3 method is recommended
 * @param <T>  it extends IBaseEntity marker to make sure data pass to method is 
 * an entity not thing else that help limit error when using generic method
 * @param <B>  can be any type
 */
public abstract class ABaseBean<T extends IBaseEntity,B> {

    /**
     * insert object to database
     * @param insertData extends IBaseEntity
     * @throws Exception
     */
    public void insert(T insertData) throws Exception{
        getEntityManager().persist(insertData);
    }

    /**
     * Update object to database
     * @param updateData extends IBaseEntity
     * @return
     * @throws Exception
     */
    public T update(T updateData) throws Exception{
        T out = getEntityManager().merge(getEntityManager().merge(updateData));
        return out;
    }

    /**
     * delete object from database
     * @param deleteData
     * @throws Exception
     */
    public void delete(T deleteData) throws  Exception{
        getEntityManager().remove(getEntityManager().merge(deleteData));
        getEntityManager().flush();
    }

    /**
     * get EntityManager from Inheritance class 
     * @return
     * @throws Exception
     */
    public abstract EntityManager getEntityManager() throws Exception;

    /**
     * select all object from database
     * @return list of IBaseEntity
     * @throws Exception
     */
    public abstract List<T> selectAll() throws Exception;

    /**
     * find object by object keyword 
     * @param keyWord
     * @return list of IBaseEntity
     * @throws Exception
     */
    public abstract List<T> findByID(B keyWord) throws Exception;

    /**
     * find object by string keyword
     * @param keyWord
     * @return list of IBaseEntity
     * @throws Exception
     */
    public abstract List<T> find(String keyWord) throws Exception;

}
