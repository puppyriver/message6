package com.alcatelsbell.nms.common;

import com.alcatelsbell.nms.common.annotation.PropertiesBean;
import com.alcatelsbell.nms.common.annotation.Property;
import com.alcatelsbell.nms.util.LogUtil;
//import com.alcatelsbell.nms.util.log.LogUtil;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * Author: Ronnie.Chen
 * Date: 13-1-18
 * Time: 下午1:50
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class PropertiesBeanLoader {
    public static Object load(Class propertyBeanClass) {
        PropertiesBean propertiesBean = (PropertiesBean) propertyBeanClass.getAnnotation(PropertiesBean.class);
        if (propertiesBean != null)                                                                               {
            try {
                Object bean = propertyBeanClass.newInstance();
                String filePath = propertiesBean.fileName();
                URL url = PropertiesBeanLoader.class.getClassLoader().getResource(filePath);
                if (url == null) return bean;
                Properties properties = new Properties();
                properties.load(url.openStream());

                Field[] declaredFields = propertyBeanClass.getDeclaredFields();
                for (int i = 0; i < declaredFields.length; i++) {
                    Field declaredField = declaredFields[i];
                    Property property = declaredField.getAnnotation(Property.class);
                    if (property != null) {
                        String key = declaredField.getName();
                        if (property.key() != null && !property.key().isEmpty())
                            key = property.key();

                        String txtValue = properties.getProperty(key);
                        if (declaredField.getType().equals(String.class)) {
                            CommonUtil.getInstance().setFiledValue(bean,declaredField,txtValue);
                        }
                        if (declaredField.getType().equals(Integer.TYPE) || declaredField.getType().equals(Integer.class)) {
                            CommonUtil.getInstance().setFiledValue(bean,declaredField,Integer.parseInt(txtValue));
                        }
                        if (declaredField.getType().equals(new int[0].getClass()) || declaredField.getType().equals(List.class)) {
                            CommonUtil.getInstance().setFiledValue(bean,declaredField,txtToMultiDic(txtValue));
                        }

                    }

                }

                return bean;
            } catch (Exception e) {
                LogUtil.error(PropertiesBeanLoader.class, e, e);
            }
        }
        return null;
    }


    public static void write(Object bean) throws Exception{
        Class propertyBeanClass = bean.getClass();
        PropertiesBean propertiesBean = (PropertiesBean) propertyBeanClass.getAnnotation(PropertiesBean.class);
        if (propertiesBean != null) {
            String filePath = propertiesBean.fileName();
            URL url = PropertiesBeanLoader.class.getClassLoader().getResource(filePath);
            Properties properties = new Properties();
            Field[] declaredFields = propertyBeanClass.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                Field declaredField = declaredFields[i];
                Property property = declaredField.getAnnotation(Property.class);
                if (property != null) {
                    String key = declaredField.getName();
                    if (property.key() != null && !property.key().isEmpty())
                        key = property.key();

                    String txtValue = null;
                    if (declaredField.getType().equals(String.class)) {
                        txtValue = (String)CommonUtil.getInstance().getFiledValue(bean,declaredField);
                    }
                    if (declaredField.getType().equals(Integer.TYPE) || declaredField.getType().equals(Integer.class)) {
                        txtValue = ""+CommonUtil.getInstance().getFiledValue(bean,declaredField);
                    }
                    if (declaredField.getType().equals(new int[0].getClass()) || declaredField.getType().equals(List.class)) {
                        txtValue = multiDicToTxt(CommonUtil.getInstance().getFiledValue(bean, declaredField));
                    }

                    if (txtValue != null)
                         properties.put(key,txtValue);
                    if (url == null) {
                        properties.list(new PrintStream(new File(filePath)));
                    }
                    else
                        properties.list(new PrintStream(new File(url.getFile())));

                }

            }

        }
    }

    private static int[] txtToMultiDic(String txt) {
        String[] split = txt.trim().split(",");
        int[] vs = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            vs[i] = Integer.parseInt(s);
        }
        return vs;
    }

    private static String multiDicToTxt(Object value) {
        int[] dics = null;
        if (value instanceof int[])
            dics = (int[])value;

        if (value instanceof List) {
            dics = new int[((List) value).size()];
            for (int i = 0; i < ((List) value).size(); i++) {
                Object o = ((List) value).get(i);
                dics[i] = (Integer)o;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < dics.length; i++) {
            int dic = dics[i];
            sb.append(dic);
            if ( i < dics.length - 1)
                sb.append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] as = new int[1] ;
        Class<? extends int[]> aClass = as.getClass();
        System.out.println(aClass.equals(new int[0].getClass()));
    }
}
