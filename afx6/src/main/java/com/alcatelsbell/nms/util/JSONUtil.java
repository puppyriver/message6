package com.alcatelsbell.nms.util;

import com.alcatelsbell.nms.valueobject.BObject;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: change Via
 * Date: 12-6-26
 * Time: 上午11:20
 * <p/>
 * <p/>
 * * For example:
 * <pre>
 *  JSONUtils.getMorpherRegistry().registerMorpher
 *  (new DateMorpher(new String[] {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
 *
 *
 *   private void setJAVA2JSONDate(JsonConfig jsonConfig) {
 * jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessor() {
 * public Object processObjectValue(String key, Object value,
 * JsonConfig arg2)
 * {
 * if (value ==null){
 * return "";
 * }
 * if (value instanceof Date) {
 * String str = new SimpleDateFormat(DEFAULT_DATE_PATTERN).format((Date) value);
 * return str;
 * }
 * return value.toString();
 * }
 *
 * public Object processArrayValue(Object value, JsonConfig arg1)
 * {
 * return null;
 * }
 * });
 * }
 *
 * </pre>
 * <p/>
 * json-lib 的实现 @see http://json-lib.sourceforge.net
 *
 * @version 2.1 主要修改了JAVA Date到JSON 的格式化。和反向JSON转化到JAVA Date
 * @since 2.2 主要修改了增加泛型。并且限制了方法的入参和方法的返回类型
 */
public class JSONUtil {
    private Log LOG = LogFactory.getLog(JSONUtil.class);

    private JsonConfig jsonConfig = new JsonConfig();

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public JSONUtil() {
    }

    public JSONUtil(JsonConfig jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    public JsonConfig getJsonConfig() {
        return jsonConfig;
    }

    public void setJsonConfig(JsonConfig jsonConfig) {
        this.jsonConfig = jsonConfig;
    }

    /**
     * List 转化成JSON的字符串
     *
     * @param object List<JavaBean>
     * @return json code
     */
    public synchronized <T extends Object> JSONArray createJSONArray(T object) {
        setDateFormat2JAVA();
        setJAVA2JSONDate(jsonConfig);
        return JSONArray.fromObject(object, jsonConfig);
    }


    /**
     * 单个对象转化成json
     *
     * @param bobject
     * @return
     */
    public synchronized <T extends Object> JSONObject createJSONObject(T bobject) {
        setDateFormat2JAVA();
        setJAVA2JSONDate(jsonConfig);
        return JSONObject.fromObject(bobject, jsonConfig);
    }

    /**
     * json字符串转化陈JSONOBJECT
     *
     * @param json
     * @return
     */


    public synchronized JSONObject createJSONObject(String json) {
        setDateFormat2JAVA();
        setJAVA2JSONDate(jsonConfig);

        if (!StringUtils.isBlank(json)) {
            return JSONObject.fromObject(json, jsonConfig);
        }
        throw new JSONParseException(new NullPointerException("Empty string :'" + json + "'"));
    }

    /**
     * json字符串转化陈JSONOBJECT
     *
     * @param json
     * @return
     */
    public synchronized JSONObject createJSONObjectBySerializer(String json) {
        setDateFormat2JAVA();
        setJAVA2JSONDate(jsonConfig);

        if (!StringUtils.isBlank(json)) {
            return (JSONObject) JSONSerializer.toJSON(json, jsonConfig);
        }
        throw new JSONParseException(new NullPointerException("Empty string :'" + json + "'"));
    }

    /**
     * java Object 转化成json对象
     *
     * @param object
     * @return
     */
    public synchronized <T extends BObject> JSONObject createJSONObjectBySerializer(T object) {
        setDateFormat2JAVA();
        setJAVA2JSONDate(jsonConfig);
        if (object != null) {
            return (JSONObject) JSONSerializer.toJSON(object, jsonConfig);
        }
        throw new JSONParseException(new NullPointerException("Empty Object :'" + object + "'"));
    }

    /**
     * json 字符串转化成java 的OBJECT
     *
     * @param jsonString JSONOBJECT 转化成javaObject
     *                   存在的问题，自己引用自己会出现错误
     * @param clazz
     * @param classMap        集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" : Bean.class)
     * @return
     */
    public synchronized <T extends BObject, K, V extends Object> Object createJavaBeanFromJSON(String jsonString, Class<T> clazz, Map<K, V> classMap) {
        setDateFormat2JAVA();
        setJAVA2JSONDate(jsonConfig);
        if (!StringUtils.isBlank(jsonString)) {
            JSONObject jsonObject = JSONObject.fromObject(jsonString, jsonConfig);
            return JSONObject.toBean(jsonObject, clazz, classMap);
        }
        throw new JSONParseException("clazz not Supported ," + clazz + "\r\t jsonString:\n" +
                "\t" + jsonString);
    }

    /**
     * 从一个JSON数组得到一个java对象集合
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public <T extends BObject> List<T> getJavaBeanList(String jsonString, Class<T> clazz) {
//        setDateFormat2JAVA();  not set
        setJAVA2JSONDate(jsonConfig);
        JSONArray array = JSONArray.fromObject(jsonString);
        List list = new ArrayList();

        for (Iterator iter = array.iterator(); iter.hasNext(); ) {
            JSONObject jsonObject = (JSONObject) iter.next();
            list.add(JSONObject.toBean(jsonObject, clazz));
        }
        return list;
    }

    public <T extends BObject> Collection<T> getJavaBeanList2(String jsonString, Class<T> clazz) {
        setJAVA2JSONDate(jsonConfig);
        return JSONArray.toCollection(JSONArray.fromObject(jsonString, jsonConfig), clazz);
    }

    /**
     * 设置JAVA 2 JSON的时间格式
     */
    private static void setDateFormat2JAVA() {
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
    }

    /**
     * 反向到JAVA的Date
     *
     * @param jsonConfig
     */
    private void setJAVA2JSONDate(JsonConfig jsonConfig) {
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
            public Object processObjectValue(String key, Object value,
                                             JsonConfig arg2) {
                if (value == null) {
                    return "";
                }
                if (value instanceof Date) {
                    String str = new SimpleDateFormat(DEFAULT_DATE_PATTERN).format((Date) value);
                    return str;
                }
                return value.toString();
            }

            public Object processArrayValue(Object value, JsonConfig arg1) {
                return null;
            }
        });
    }

    /**
     * 将一个jsonObject 装化成一个BObject
     *
     * @param jsonString
     * @param clazz
     * @param properties
     * @return
     * @throws Exception
     * @see T 表示泛型的下限 为BObject的子类
     */
    public <T extends BObject> T setJavaBeanProperty(String jsonString, Class<T> clazz, Properties properties) throws Exception {
        T instance = null;
        setDateFormat2JAVA();
        setJAVA2JSONDate(jsonConfig);
        if (!StringUtils.isBlank(jsonString)) {
            JSONObject jsonObject = JSONObject.fromObject(jsonString, jsonConfig);
            instance = clazz.newInstance();
            Enumeration enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                Object key = enumeration.nextElement();
                String value = (String) properties.get(key);
                Object jsonValue = jsonObject.get(key);
                if (jsonValue != null)
                    PropertyUtil.setProperties(instance, value, jsonValue);
            }
            return instance;
        }
        throw new JSONParseException("clazz not Supported ," + clazz + "\r\t jsonString:\n" +
                "\t" + jsonString);
    }

    /**
     * json Array 转化成ArrayList
     * class 是ArrayList的Typeof  例如：ArrayList<Object> Type of is Object
     * properties 保存jsonArray 的key和javaBean Field 对于关系,如果json array中没有Java Bean 中的Field 则可以忽略。
     * 如果json Array 里面有key javaBean没有field 就会报错
     *
     * @param jsonArrayString
     * @param clazz
     * @param properties
     * @return T 表示泛型的下限 为BObject的子类
     *         省掉 <code>instanceof  Bbject.class 和 class==BObject.class </code> 的判断
     * @throws Exception
     */
    public <T extends BObject> List<T> setJavaBeansProperty(String jsonArrayString, Class<T> clazz, Properties properties) throws Exception {
        List<T> objects = new ArrayList<T>();
        setJAVA2JSONDate(jsonConfig);
        if (!StringUtils.isBlank(jsonArrayString)) {
            JSONArray array = JSONArray.fromObject(jsonArrayString);
            for (Iterator iter = array.iterator(); iter.hasNext(); ) {
                JSONObject jsonObject = (JSONObject) iter.next();
                T instance = clazz.newInstance();

                Enumeration enumeration = properties.propertyNames();
                while (enumeration.hasMoreElements()) {
                    Object key = enumeration.nextElement();
                    String value = (String) properties.get(key);
                    Object jsonValue = jsonObject.get(key);
                    if (jsonValue != null) {
                        PropertyUtil.setProperties(instance, value, jsonValue);
                    }
                }
                objects.add(instance);
            }
            return objects;
        }
        throw new JSONParseException("clazz not Supported ," + clazz + "\r\t jsonString:\n" +
                "\t" + jsonArrayString);
    }


}



