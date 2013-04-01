package com.bradchen.jsf.facelets.renderer;

import org.apache.myfaces.view.facelets.impl.DefaultResourceResolver;

import java.net.URL;

public class ClassPathResourceResolver extends DefaultResourceResolver {

	public URL resolveUrl(String path) {
		URL url = getClass().getClassLoader().getResource(path);
		return (url == null) ? super.resolveUrl(path) : url;
	}

}
