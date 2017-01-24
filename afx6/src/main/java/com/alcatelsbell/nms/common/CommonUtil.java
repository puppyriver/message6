package com.alcatelsbell.nms.common;

import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import org.w3c.dom.Document;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonUtil
{
  private static Logger logger = Logger.getLogger(CommonUtil.class.getName());
  private static CommonUtil instance = null;
  private static int COMPONENT_HEIGHT = 25;

  public static synchronized CommonUtil getInstance()
  {
    if (instance == null)
      instance = new CommonUtil();

    return instance;
  }

  public static String toString(Object object) {

          StringBuffer buff = new StringBuffer();
          Method[] m = object.getClass().getMethods();
          final int idx = 3;
          try {
              for(int i=0;i<m.length;i++) {
                  String methodName = m[i].getName();
                  if (methodName.startsWith("get") && !methodName.endsWith("Class")) {
                      //String className = m[i].getReturnType().getName();
                      Object o = m[i].invoke(object, new Object[]{});
                      if (o != null) {
                          buff.append(methodName.substring(idx)).append("=[").append(o).append("] ");
                      }
                  }
              }
          }catch(IllegalAccessException iae) {
              iae.printStackTrace();
          }catch(InvocationTargetException ite) {
              ite.printStackTrace();
          }
          return buff.toString();


  }

  public byte[] serializeObject(Object obj)
  {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      ObjectOutput out = new ObjectOutputStream(bos);
      out.writeObject(obj);
      out.close();
    } catch (Exception e) {
      return null;
    }
    return bos.toByteArray();
  }


  public Object deserializeObject(byte[] byteArray)
  {
    Object result = null;
    try {
      ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteArray));
      result = in.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  public Field getField(Class<?> type, String name) {
    Field result = null;
    Field[] arr$ = type.getDeclaredFields(); int len$ = arr$.length; for (int i$ = 0; i$ < len$; ++i$) { Field field = arr$[i$];
      if (field.getName().equals(name))
        return field;
    }

    if (type.getSuperclass() != null) {
      return getField(type.getSuperclass(), name);
    }

    return result;
  }

  public Object getFiledValue(Object obj, String fieldName) throws Exception {
    if (obj == null)
      return null;

    if (isEmptyString(fieldName))
      return null;

    Field field = getField(obj.getClass(), fieldName);
    return getFiledValue(obj, field);
  }

  public Object getFiledValue(Object obj, Field field) throws Exception {
    boolean oldAccessible = field.isAccessible();
    field.setAccessible(true);
    Object value = field.get(obj);
    field.setAccessible(oldAccessible);
    return value;
  }

  public void setFiledValue(Object obj, String fieldName, Object value) throws Exception {
    if (obj == null)
      return;

    Field field = getField(obj.getClass(), fieldName);
    setFiledValue(obj, field, value);
  }

  public void setFiledValue(Object obj, Field field, Object value) throws Exception {
    boolean oldAccessible = field.isAccessible();
    field.setAccessible(true);
    field.set(obj, value);
    field.setAccessible(oldAccessible);
  }

  public void setFieldValueByMethod(Object obj,Field field,Object value) throws Exception {
      Method method = obj.getClass().getMethod("set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1),value.getClass());
      method.invoke(obj,value);
  }

  public boolean isSubclass(Class<?> clazz1, Class<?> clazz2) {
    try {
      clazz1.asSubclass(clazz2);
    } catch (ClassCastException e) {
      return false;
    }
    return true;
  }

  public List<Field> getAllFields(Class<?> type) {
    return getAllFields(new LinkedList(), type);
  }

  protected List<Field> getAllFields(List<Field> fields, Class<?> type) {
    Field[] arr$ = type.getDeclaredFields(); int len$ = arr$.length; for (int i$ = 0; i$ < len$; ++i$) { Field field = arr$[i$];
      fields.add(field);
    }

    if (type.getSuperclass() != null) {
      fields = getAllFields(fields, type.getSuperclass());
    }

    return fields;
  }

  public void centerDialog(JDialog w) {
    if (w != null) {
      Component parent = SwingUtilities.getRoot(w);
      w.setLocationRelativeTo(parent);
    }
  }

  public void sort(List list, String methodName) {
    final String _methodName = ((methodName == null) || (methodName == "")) ? "size" : methodName;

    Collections.sort(list, new Comparator() {
      public int compare(Object o1, Object o2) {
        int compareResult;
        Method method;
        try {
          method = o1.getClass().getMethod(_methodName, null);
          Object obj1 = method.invoke(o1, null);
          method = o2.getClass().getMethod(_methodName, null);
          Object obj2 = method.invoke(o2, null);

          Class[] parameterTypes = { Object.class };

          method = obj1.getClass().getMethod("compareTo", parameterTypes);
          Object[] argsArray = new Object[1];
          argsArray[0] = obj2;
          compareResult = ((Integer)method.invoke(obj1, argsArray)).intValue();
        } catch (Exception exception) {
          exception.printStackTrace();
          RuntimeException re = new RuntimeException("noSuchMethod");
          throw re;
        }
        return compareResult;
      }
    });
  }

  public int findFreePort()
  {
    ServerSocket socket = null;
    try {
      socket = new ServerSocket(0);
      int i = socket.getLocalPort();

      return i;
    }
    catch (IOException e)
    {
      e.printStackTrace();
    } finally {
      if (socket != null)
        try {
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    return -1;
  }

  public String toChinese(String strvalue) {
    try {
      if (strvalue == null)
        return null;

      strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
      return strvalue;
    }
    catch (Exception e) {
    }
    return strvalue;
  }

  public String exceptionToStr(Throwable ex)
  {
    StringWriter sw = new StringWriter();
    ex.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }

  public String getDetailErrorMsg(Throwable error)
  {
    String result = "";
    ByteArrayOutputStream outstream = new ByteArrayOutputStream(1000);
    error.printStackTrace(new PrintStream(outstream));
    result = outstream.toString();
    try {
      outstream.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return result;
  }

  public Exception throwException(String errMsg) {
    StringBuilder strMsg = new StringBuilder();
    strMsg.append(new StringBuilder().append(errMsg).append(" caused by ").toString());
    StackTraceElement[] e = Thread.currentThread().getStackTrace();
    for (int i = 0; i < e.length; ++i)
      strMsg.append(new StringBuilder().append(e.toString()).append("   ").toString());

    Exception result = new Exception(strMsg.toString());

    return result;
  }

//  public String commonString(String s1, String s2) {
//    StringBuffer result = new StringBuffer();
//    int M = s1.length();
//    int N = s2.length();
//
//    int[][] opt = new int[M + 1][N + 1];
//    int j = 0;
//    for (int i = M - 1; i >= 0; --i) {
//      for (j = N - 1; j >= 0; --j)
//        if (s1.charAt(i) == s2.charAt(j))
//          opt[i][j] = (opt[(i + 1)][(j + 1)] + 1);
//        else
//          opt[i][j] = Math.max(opt[(i + 1)][j], opt[i][(j + 1)]);
//
//
//    }
//
//    int i = 0;
//    while (true) { while (true) { while (true) { if ((i >= M) || (j >= N)) break label229;
//          if (s1.charAt(i) != s2.charAt(j)) break;
//          result.append(s1.charAt(i));
//          ++i;
//          ++j; }
//        if (opt[(i + 1)][j] < opt[i][(j + 1)]) break;
//        ++i;
//      }
//      ++j;
//    }
//
//    label229: return result.toString();
//  }

  public char waitForKey()
  {
    char answer = '\0';
    try {
      answer = (char)new InputStreamReader(System.in).read();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return answer;
  }

  public Document createDocument(String strDoc, String charsetName) throws Exception {
    ByteArrayInputStream stream = new ByteArrayInputStream(strDoc.getBytes(charsetName));

    return createDocument(stream);
  }

  private Document createDocument(InputStream inputStream)
    throws Exception
  {
    Document doc;
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try
    {
      DocumentBuilder parser = factory.newDocumentBuilder();
      doc = parser.parse(inputStream);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Uncaught exception", e);
      throw e;
    }

    return doc;
  }

  public boolean saveObject(String fileName, Object object) throws Exception {
    FileOutputStream outputFile = null;
    ObjectOutputStream serializeStream = null;
    try {
      String fileDir = fileName.substring(0, fileName.lastIndexOf(File.separator) + 1);
      if (!(newFolder("fileDir")))
        return false;

      outputFile = new FileOutputStream(fileName);
      serializeStream = new ObjectOutputStream(outputFile);
      serializeStream.writeObject(object);
      serializeStream.flush();
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Uncaught exception", ex);
      throw ex;
    }
    return true;
  }

  public Object readObject(String fileName) throws Exception {
    FileInputStream inputFile = null;

    ObjectInputStream serializeStream = null;
    try {
      inputFile = new FileInputStream(fileName);
      serializeStream = new ObjectInputStream(inputFile);
      return serializeStream.readObject();
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Uncaught exception", ex);
      logger.info(new StringBuilder().append("resoure file not found !").append(fileName).toString());
      throw ex; }
  }

  public boolean newFolder(String folderPath) throws Exception {
    String filePath;
    try {
      filePath = folderPath;
      filePath = filePath.toString();
      File myFilePath = new File(filePath);
      if (!(myFilePath.exists()))
        myFilePath.mkdir();
    }
    catch (Exception e) {
      logger.info("新建目录操作出错");
      logger.log(Level.SEVERE, "Uncaught exception", e);
      throw e;
    }
    return true;
  }

  public int getListSize(List data) {
    if (data == null)
      return 0;

    return data.size();
  }

  public boolean isEmptyList(List data) {
    if (data == null)
      return true;

    return data.isEmpty();
  }

  public boolean isEmptyString(String str) {
    if (str == null)
      return true;

    return (str.length() == 0);
  }

  public boolean isEqualLong(Long l1, Long l2) {
    if ((l1 == null) && (l2 == null))
      return true;

    if ((l1 == null) || (l2 == null))
      return false;

    return (l1.longValue() == l2.longValue());
  }

  public String getDateStr(Date date) {
    if (date != null)
    {
      String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(date);
      return result;
    }
    return "";
  }

  public String getObjectString(Object obj)
  {
    if (obj == null)
      return "";

    if (obj instanceof Date)
      return getDateStr((Date)obj);

    return obj.toString();
  }

  public void beanCopy(Object srcObject, Object destObject, String attrs) throws Exception {
    if (isEmptyString(attrs)) {
      return;
    }

    String[] attrArray = attrs.split(",");
    String[] arr$ = attrArray; int len$ = arr$.length; for (int i$ = 0; i$ < len$; ++i$) { String attr = arr$[i$];
      String attrTemp = attr.trim();
      Object value = getFiledValue(srcObject, attrTemp);
      setFiledValue(destObject, attrTemp, value);
    }
  }

  public void beanCopy(Object srcObject, Map destMap, String attrs) throws Exception {
    if (isEmptyString(attrs)) {
      return;
    }

    String[] attrArray = attrs.split(",");
    String[] arr$ = attrArray; int len$ = arr$.length; for (int i$ = 0; i$ < len$; ++i$) { String attr = arr$[i$];
      String attrTemp = attr.trim();
      Object value = getFiledValue(srcObject, attrTemp);
      destMap.put(attrTemp, value);
    }
  }

  public void clearEnumNull(Map destMap, String attrs) throws Exception {
    if (isEmptyString(attrs)) {
      return;
    }

    String[] attrArray = attrs.split(",");
    String[] arr$ = attrArray; int len$ = arr$.length; for (int i$ = 0; i$ < len$; ++i$) { String attr = arr$[i$];
      String attrTemp = attr.trim();
      Object value = destMap.get(attrTemp);
      if ((value != null) && (value instanceof Integer)) {
        int intvalue = ((Integer)value).intValue();
        if (intvalue < 0)
          destMap.put(attrTemp, null);
      }
    }
  }

  public int compareTime(Date d1, Date d2)
  {
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();

    c1.setTime(d1);
    c2.setTime(d2);

    c1.set(1980, 1, 1);
    c2.set(1980, 1, 1);
    return new Long(c1.getTimeInMillis()).compareTo(new Long(c2.getTimeInMillis()));
  }

  public boolean lessThanTime(Date d1, Date d2) {
    int result = compareTime(d1, d2);

    return (result < 0);
  }
   public static String checkNull(String str) {
       return str == null ? "":str;
   }

    public static void printObjectFieldValues(Object obj) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        if (methods != null) {
            for (Method method : methods){
                if (method.getName().startsWith("get")){
                    Object value = null;
                    try {
                        value = method.invoke(obj);
                    } catch ( Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(method.getName()+"   ==    "+(value == null ? null:value.toString()));
                }
            }
        }
    }
    public static void createSetMethods(Class cls,String instname,boolean copymethod) {
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            Class[] pts = method.getParameterTypes();

            if (name.startsWith("set") && pts.length == 1) {
                Class pt = pts[0];
                if (pt.equals(String.class)) {
                    if (!copymethod)
                        System.out.println(instname+"."+name+"(\""+name.substring(3)+"\");");
                    else  {
                        String getmd = "g"+name.substring(1);
                        System.out.println(instname+"."+name+"("+instname+"2."+getmd+"());");
                    }
                }
                if (pt.equals(Integer.class) || pt.equals(Long.class) || pt.getName().equals("int") ||  pt.getName().equals("long") ){
                    if (!copymethod)
                        System.out.println(instname+"."+name+"(1);");
                    else  {
                        String getmd = "g"+name.substring(1);
                        System.out.println(instname+"."+name+"("+instname+"2."+getmd+"());");
                    }
                }

            }
        }
    }

    public static Object getObjectFieldValue(Field field,Object object) {
        String fieldName = field.getName();
        String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
        try {
            return object.getClass().getMethod(methodName).invoke(object);
        } catch (Exception e) {
            return null;
        }
    }



    public  static String  findCharset(String txt) {
        return null;
    }


    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                if (readMethod != null) {
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
                }
            }
        }
        return returnMap;
    }

    /**
	 * 根据类获取对应的数据库表名
	 * added by Aaron
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getTableNameByClass(Class cls){
		Annotation tableAnnot=cls.getAnnotation(javax.persistence.Table.class);
		String tableName=((javax.persistence.Table)tableAnnot).name();
		return tableName;
	}

    public static void printSetMethods(String srcName,Class srcCls,String desName,Class desClass) {
        System.out.println(desClass.getSimpleName()+" "+desName+" = new "+desClass.getSimpleName()+"();");
        Field[] declaredFields = srcCls.getDeclaredFields();
        String bigName1 = "Dn";
        System.out.println(desName+".set" +bigName1+"("+srcName+".get"+bigName1+"());");
        bigName1 = "CollectTimepoint";
        System.out.println(desName+".set" +bigName1+"("+srcName+".get"+bigName1+"());");
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            String fieldName = declaredField.getName();
            String bigName =  fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
            System.out.println(desName+".set" +bigName+"("+srcName+".get"+bigName+"());");
        }
    }
    
    public static void main(String[] args) {
        System.out.println(toString(new Curralarm()));
      //  createSetMethods(Alarminformation.class,"rp",false);
    }
}