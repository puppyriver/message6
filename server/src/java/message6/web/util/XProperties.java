package message6.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Author: Ronnie.Chen
 * Date: 2016/5/16
 * Time: 10:23
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class XProperties extends Properties {
 //   private String splitter = "\\.";
    private static Logger logger = LoggerFactory.getLogger(XProperties.class);


    private XProperties() {
    }

    public void addXProperty(XProperty xProperty) {
        this.put(xProperty.key,xProperty);
    }

    public String getProperty(String key) {
        Object oval = super.get(key);
        String sval = (oval instanceof XProperty) ? ((XProperty)oval).value : oval instanceof String ? (String)oval : null;
        return ((sval == null) && (defaults != null)) ? defaults.getProperty(key) : sval;
    }

    public String getProperty(String key, String defaultValue) {
        String val = getProperty(key);
        return (val == null) ? defaultValue : val;
    }

    public String getValueByShortName(String shortName) {
        for (Object o : this.values()) {
            if (o instanceof XProperty) {
                XProperty xProperty = (XProperty) o;
                if (xProperty.shortKey.equals(shortName))
                    return xProperty.value;
            }
        }
        return null;
    }

    private volatile boolean init = false;


    private void initX() {
        synchronized (this) {
            if (!init) {
                Set<String> keySet = stringPropertyNames();
                for (String propertyName : keySet) {
                    String[] names = propertyName.split("\\.");

                    Object o = get(propertyName);
                    XProperty xProperty = null;

                    if (o == null || !(o instanceof XProperty)) {
                        xProperty = new XProperty(propertyName, this.getProperty(propertyName), names[names.length - 1]);
                        put(propertyName, xProperty);
                    } else {
                        xProperty = (XProperty)o;
                    }

                    if (xProperty.value == null)
                        xProperty.value = this.getProperty(propertyName);

                    XProperty tmp = xProperty;
                    if (names.length > 1) {
                        for (int i = names.length-1; i >0; i--) {
                            String parentPropertyName = null;
                            parentPropertyName = joint(Arrays.copyOfRange(names,0,i));
                            Object t = get(parentPropertyName);
                            XProperty parentX = null;

                            if (t == null || !(t instanceof XProperty)) {
                                parentX = new XProperty(parentPropertyName,(t instanceof String ? (String)t : null),names[i-1]);
                                put(parentPropertyName,parentX);
                            } else {
                                parentX = (XProperty)t;
                            }

                            parentX.addChildXProperty(tmp);
                            tmp.setParentXProperty(parentX);
                            tmp = parentX;
                        }
                    }
                }
            }
            init = true;
        }
    }

    private String joint(String[] names) {
        StringBuffer sb = new StringBuffer();
        int idx = 0;
        for (String name : names) {
            if (++idx == names.length)
                sb.append(name);
            else
                sb.append(name).append(".");
        }
        return sb.toString();
    }



    public XProperty getXProperty(String name) {
        if (!init)
            initX();
        return (XProperty)get(name);
    }


    public static class XProperty {
        public XProperty(String key, String value, String shortKey) {
            this.key = key;
            this.value = value;
            this.shortKey = shortKey;
            childs.initX();
        }

        public String getValue(String _default) {
            return value == null ? _default : value;
        }

        public String key;
        public String value;
        public String shortKey;
        private XProperty parentXProperty;
        public XProperties childs = new XProperties();

        public XProperty getParentXProperty() {
            return parentXProperty;
        }

        public void setParentXProperty(XProperty parentXProperty) {
            this.parentXProperty = parentXProperty;
        }


        public void addChildXProperty(XProperty child) {
            childs.addXProperty(child);
        }

        public XProperty child(String path) {
            String[] paths = path.split("\\.");
            XProperty childsXProperty = childs.getXProperty(key + "." + paths[0]);
            if (childsXProperty != null) {
                if (paths.length == 1)
                    return childsXProperty;
                else
                    return childs.getXProperty(key + "." + paths[0]).child(path.substring(path.indexOf(".") + 1));
            }
            return null;
        }

        public String childValue(String path,String _default) {
            XProperty child = child(path);
            if (child == null) {
                logger.warn("Faild to read property {},use default value : {}",this.key+"."+path,_default);
                return _default;
            }
            return child.value == null ? _default : child.value;
        }

        public String childValue(String path) {
            return childValue(path,null);
        }

        public int childValueInt(String path,int _default) {
            String v = childValue(path);
            if (v == null)
                logger.warn("Faild to read property {},use default value : {}",this.key+"."+path,_default);
            return v == null ? _default : Integer.parseInt(v);
        }



    }


    private static XProperties inst = null;
    public static XProperties get() {
        if (inst == null) {
            synchronized (XProperties.class) {
                if (inst == null) {
                    inst = new XProperties();
                    String fileName = System.getProperties().getProperty("systemConfigFileName", "system.properties");
                    if (fileName.contains(";")){
                        String[] fileNames = fileName.split(";");
                        for (String name : fileNames) {
                            inst.load(name,
                                    System.getProperties().getProperty("systemConfigFileCharset","utf-8"));
                        }
                    } else {
                        inst.load(fileName,
                                System.getProperties().getProperty("systemConfigFileCharset", "utf-8"));
                    }
                    inst.initX();
                }
            }
        }

        return inst;
    }




    public  void load(String fileName, String charset) {


        try {
            this.load(new InputStreamReader(new FileInputStream(fileName),charset));
            logger.info("Load Property File :"+new File(fileName).getAbsolutePath());
        } catch (Exception ex) {
            try {
                InputStream is = XProperties.class.getClassLoader().getResourceAsStream(fileName);

                this.load(new InputStreamReader(is,charset));
                logger.info("Load Property File :"+  XProperties.class.getClassLoader().getResource(fileName));
            } catch (Exception ex1) {
                logger.error("FAILED TO LOAD PROPERTY FILE :"+fileName);
                //   ex1.printStackTrace();
            }
        }
        Enumeration keys = this.keys();
        logger.info(" =============== LOADING FILE "+fileName+" =====================");
        while (keys.hasMoreElements()){
            String key = (String)keys.nextElement();
            logger.info(key+" == "+this.getProperty(key));
            System.setProperty(key,this.getProperty(key));
        }
        logger.info(" =============== FINISH LOADING FILE "+fileName+" =====================");


    }

    public int getInt(String key) {
        String str = getProperty(key);
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception ex) {
            return -1;
        }
    }
    public int getInt(String key, int temp) {
        String str = getProperty(key);
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return temp;
        }
    }

    public List<XProperty> search(String shortKey) {
        List<XProperty> properties = new ArrayList<>();
        for (Object o : this.values()) {
            if (o instanceof XProperty ) {
                if (shortKey.equals(((XProperty) o).shortKey))
                    properties.add((XProperty) o);
                if (((XProperty) o).childs != null){
                    List<XProperty> search = ((XProperty) o).childs.search(shortKey);
                    if (search != null && !search.isEmpty())
                        properties.addAll(search);
                }
            }
        }
        return properties;
    }


    public List<String> searchValues(String shortKey) {
        List<String> values = new ArrayList<>();
        List<XProperty> search = search(shortKey);
        for (XProperty xProperty : search) {
            values.add(xProperty.value);
        }
        return values;
    }



    public static void main(String[] args) throws IOException {
        XProperty xProperty1 = XProperties.get().getXProperty("nbi.pm.src");
        List<String> url = xProperty1.childs.searchValues("url");
        System.out.println("xProperty1 = " + xProperty1);
    }
}
