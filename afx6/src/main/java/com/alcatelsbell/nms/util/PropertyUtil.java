package com.alcatelsbell.nms.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * User: Change Via
 * Date: 12-8-11
 * Time: 下午8:09
 * java 自省
 */
public class PropertyUtil {
    public static  <T extends Object> Object getProperties(T instance, String beanProperty) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        PropertyDescriptor descriptor = new PropertyDescriptor(beanProperty, instance.getClass());
        Method writeMethod = descriptor.getReadMethod();
        return writeMethod.invoke(instance);
    }

    public static <T extends Object,V extends Object> Object setProperties(T instance, String beanProperty, Object propertyVaulue) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        PropertyDescriptor descriptor = new PropertyDescriptor(beanProperty, instance.getClass());
        Method writeMethod = descriptor.getWriteMethod();
        Class parameter = writeMethod.getParameterTypes()[0];
        propertyVaulue = fieldCast(parameter,propertyVaulue);
        return writeMethod.invoke(instance, propertyVaulue);
    }

    private static <T extends Object> T fieldCast(Class<T> clazz,Object param) {
            Object rt = null;
            try {
                if (clazz.isPrimitive()){
                    if (clazz ==int.class){
                        return (T)TypeUtils.castToInt(param);
                    }else if(clazz ==boolean.class){
                        return (T)TypeUtils.castToBoolean(param);
                    }else if(clazz==byte.class){
                        return (T)TypeUtils.castToByte(param);
                    }else  if(clazz==char.class){
                        return (T)TypeUtils.castToChar(param);
                    } else if(clazz ==short.class){
                        return (T)TypeUtils.castToShort(param);
                    } else if(clazz==long.class){
                        return (T)TypeUtils.castToLong(param);
                    } else if(clazz==float.class){
                        return (T)TypeUtils.castToFloat(param);
                    }else if(clazz==double.class){
                        return (T)TypeUtils.castToDouble(param);
                    }
                }

                if(clazz.getSuperclass()==Number.class){
                    rt = clazz.getClass();
                    if (clazz ==Integer.class){
                        return  (T)TypeUtils.castToInt(param);
                    }else if(clazz ==Boolean.class){
                        return  (T)TypeUtils.castToBoolean(param);
                    }else if(clazz==Byte.class){
                        return (T)TypeUtils.castToByte(param);
                    } else if(clazz ==Short.class){
                        return (T)TypeUtils.castToShort(param);
                    } else if(clazz==Long.class){
                        return (T)TypeUtils.castToLong(param);
                    } else if(clazz==Float.class){
                        return (T)TypeUtils.castToFloat(param);
                    }else if(clazz==Double.class){
                        return (T)TypeUtils.castToDouble(param);
                    } else {
                        return (T)TypeUtils.castToString(param);
                    }
                }else if(clazz==Date.class) {
                    return (T)TypeUtils.castToDate(param);
                } else if(clazz ==String.class){
                    return (T)TypeUtils.castToString(param);
                } else  if(clazz==Character.class){
                    return (T)TypeUtils.castToChar(param);
                } else  if(clazz==Boolean.class){
                    return (T)TypeUtils.castToBoolean(param);
                }else {
                    return (T)TypeUtils.castToString(param);
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            return (T)rt;
        }
}
