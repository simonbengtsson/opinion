
package com.dat076hage.hage;

import com.dat076hage.hage.controllers.*;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author hajo
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.dat076hage.hage.auth.AuthFilter.class);
        resources.add(com.dat076hage.hage.controllers.PostController.class);
        resources.add(com.dat076hage.hage.controllers.UserController.class);
    }
}