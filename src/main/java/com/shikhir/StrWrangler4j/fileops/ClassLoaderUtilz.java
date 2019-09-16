package com.shikhir.StrWrangler4j.fileops;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ClassLoaderUtilz {
    public static URL getResource(String resourceName, Class callingClass) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);

        if (url == null) {
            url = ClassLoaderUtilz.class.getClassLoader().getResource(resourceName);
        }

        if (url == null) {
            ClassLoader cl = callingClass.getClassLoader();

            if (cl != null) {
                url = cl.getResource(resourceName);
            }
        }

        if ((url == null) && (resourceName != null) && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))) { 
            return getResource('/' + resourceName, callingClass);
        }

        return url;
    }
	public static InputStream getResourceAsStream(String resourceName, Class callingClass) {
		URL url = getResource(resourceName, callingClass);

		try {
			return (url != null) ? url.openStream() : null;
		} catch (IOException e) {
			return null;
		}
	}
}
