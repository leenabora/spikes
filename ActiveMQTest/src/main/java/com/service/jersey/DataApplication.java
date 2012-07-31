package com.service.jersey;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.tesco.spike.restservice.BasketResource;

/**
 * TODO - This class is not required if using Spring
 * @author pavan
 *
 */
@ApplicationPath("/")
public class DataApplication extends Application {

	 @Override
	    public Set<Class<?>> getClasses() {
	        final Set<Class<?>> classes = new HashSet<Class<?>>();
	        // register root resource
	        classes.add(BasketResource.class);
	        return classes;
	    }
}
