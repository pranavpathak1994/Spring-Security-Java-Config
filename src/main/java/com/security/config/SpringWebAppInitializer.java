package com.security.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Similar to Deployment Descriptor
 * @author pranav
 *
 */

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
	
		return new Class[] { SpringMvcConfig.class , SpringSecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
	
		return null;
	}

	@Override
	protected String[] getServletMappings() {
	
		return new String[] { "/" };
	}

}
