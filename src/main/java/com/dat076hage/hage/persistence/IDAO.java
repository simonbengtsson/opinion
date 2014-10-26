
package com.dat076hage.hage.persistence;

import java.util.List;

/**
 * Basic CRUD interface implemented by all DAO (Facade)
 * classes 
 *
 * @author hajo
 * @param <T> Type
 * @param <K> Primary key (id)
 */

public interface IDAO<T, K> {

    public void create(T t);

    public void delete(K id);

    public void update(T t);

    public T find(K id);

    public int count();
    
    //public void refresh(T t);
}
