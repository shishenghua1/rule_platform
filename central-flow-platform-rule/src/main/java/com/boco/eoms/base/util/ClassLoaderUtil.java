package com.boco.eoms.base.util;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

//import com.boco.eoms.base.dao.jdbc.QueryJdbcDaoImpl;
//import com.boco.eoms.base.tool.LogOutTool;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-11-19 11:07:44
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class ClassLoaderUtil {
    /**
     * Thread.currentThread().getContextClassLoader().getResource("")
     */

    /**
     * 加载Java类。 使用全限定类名
     * 
     * @paramclassName
     * @return
     */
    public static Class loadClass(String className) {
        try {
            return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not found '" + className + "'", e);
        }
    }

    /**
     * 得到类加载器
     * 
     * @return
     */
    public static ClassLoader getClassLoader() {

        return ClassLoaderUtil.class.getClassLoader();
    }

    /**
     * 提供相对于classpath的资源路径，返回文件的输入流
     * 
     * @param
     * relativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
     * @return 文件输入流
     * @throws IOException
     * @throws MalformedURLException
     */
    public static InputStream getStream(String relativePath)
            throws MalformedURLException, IOException {
        if (!(relativePath.indexOf("../") > -1)) {
            return getClassLoader().getResourceAsStream(relativePath);

        } else {
            return ClassLoaderUtil.getStreamByExtendResource(relativePath);
        }

    }

    /**
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public static InputStream getStream(URL url) throws IOException {
        if (url != null) {

            return url.openStream();

        } else {
            return null;
        }
    }

    /**
     * 
     * @paramr elativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static InputStream getStreamByExtendResource(String relativePath)
            throws MalformedURLException, IOException {
        return ClassLoaderUtil.getStream(ClassLoaderUtil
                .getExtendResource(relativePath));
    }

    /**
     * 提供相对于classpath的资源路径，返回属性对象，它是一个散列表
     * 
     * @param resource
     * @return
     */
    public static Properties getProperties(String resource) {
        Properties properties = new Properties();
        try {
            properties.load(getStream(resource));
        } catch (IOException e) {
            throw new RuntimeException("couldn't load properties file '"
                    + resource + "'", e);
        }
        return properties;
    }

    /**
     * 得到本Class所在的ClassLoader的Classpat的绝对路径。 URL形式的
     * 
     * @return
     */
    public static String getAbsolutePathOfClassLoaderClassPath() {

//        LogOutTool.getLogger(ClassLoaderUtil.class).info(ClassLoaderUtil.getClassLoader().getResource(
//                "").toString());
        return ClassLoaderUtil.getClassLoader().getResource("").toString();

    }

    /**
     * 
     * @param relativePath
     *            必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用../来查找
     * @return 资源的绝对URL
     * @throws MalformedURLException
     */
    public static URL getExtendResource(String relativePath)
            throws MalformedURLException {

        if (!(relativePath.indexOf("../")>-1)) {
            return ClassLoaderUtil.getResource(relativePath);

        }
        String classPathAbsolutePath = ClassLoaderUtil
                .getAbsolutePathOfClassLoaderClassPath();
        if (relativePath.substring(0, 1).equals("/")) {
            relativePath = relativePath.substring(1);
        }
//        LogOutTool.getLogger(ClassLoaderUtil.class).info("relativePath=="+new Integer(relativePath.lastIndexOf("../")));
        String wildcardString = relativePath.substring(0, relativePath
                .lastIndexOf("../") + 3);
        relativePath = relativePath
                .substring(relativePath.lastIndexOf("../") + 3);
        int containSum = ClassLoaderUtil.containSum(wildcardString, "../");
        classPathAbsolutePath = ClassLoaderUtil.cutLastString(
                classPathAbsolutePath, "/", containSum);
        String resourceAbsolutePath = classPathAbsolutePath + relativePath;
//        LogOutTool.getLogger(ClassLoaderUtil.class).info("绝对路径：" + resourceAbsolutePath);

        URL resourceAbsoluteURL = new URL(resourceAbsolutePath);
        return resourceAbsoluteURL;
    }

    /**
     * 
     * @param source
     * @param dest
     * @return
     */
    private static int containSum(String source, String dest) {
        int containSum = 0;
        int destLength = dest.length();
        //         while(source.contains(dest)){
        while (source.indexOf(dest) > -1) {
            containSum = containSum + 1;
            source = source.substring(destLength);
        }
        return containSum;
    }

    /**
     * 
     * @param source
     * @param dest
     * @param num
     * @return
     */
    private static String cutLastString(String source, String dest, int num) {
        // String cutSource=null;
        for (int i = 0; i < num; i++) {
            source = source.substring(0, source.lastIndexOf(dest, source
                    .length() - 2) + 1);
        }

        return source;
    }

    /**
     * 
     * @param resource
     * @return
     */
    public static URL getResource(String resource) {
//    	LogOutTool.getLogger(ClassLoaderUtil.class).info("传入的相对于classpath的路径：" + resource);

        return ClassLoaderUtil.getClassLoader().getResource(resource);
    }
}
