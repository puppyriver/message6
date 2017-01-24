package com.alcatelsbell.nms.common;

//import java.util.Arrays;


import com.alcatelsbell.nms.util.Assertion;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;


public abstract class Detect {// implements Serializable {

	// private static final transient Log log = LogFactory.getLog(Detect.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -3754637454616848491L;

	public static final short INVALID_NUMBER_VALUE = 0;

	public static final String EMPTY_STRING = "";

	public static final String DELIMITER = ",";

	/**  */
	public static boolean notEmpty(String string) {
		return null != string && !EMPTY_STRING.equals(string);
	}

	public static boolean notEmpty(List<?> list) {
		return null != list && !list.isEmpty();
	}

	public static boolean notEmpty(Map<?, ?> map) {
		return null != map && !map.isEmpty();
	}

	public static boolean notEmpty(Collection<?> collection) {
		return null != collection && !collection.isEmpty();
	}

	public static boolean notEmpty(String[] array) {
		return null != array && array.length > 0;
	}

	public static boolean notEmpty(short[] array) {
		return null != array && array.length > 0;
	}

	public static boolean notEmpty(long[] array) {
		return null != array && array.length > 0;
	}

	/**  */
	public static boolean isNegative(double value) {
		return value < 0;
	}

	public static boolean isPositive(double value) {
		return value > 0;
	}

	public static boolean isTrue(Boolean value) {
		return Boolean.TRUE.equals(value);
	}

	public static boolean isFalse(Boolean value) {
		return Boolean.FALSE.equals(value);
	}

	/**  */
	public static boolean contains(long value, long[] values) {
		if (notEmpty(values)) {
			int length = values.length;
			for (int i = 0; i < length; i++) {
				if (values[i] == value) {
					return true;
				}
			}
		}
		return false;
	}

	public static <E> boolean contains(E one, List<E> list) {
		if (notEmpty(list) && null != one) {
			for (E item : list) {
				if (one.equals(item)) {
					return true;
				}
			}
		}
		return false;
	}

	/** *array */
	public static double[] doubleArray(String value, String delimiter) {
		String[] values = StringUtils.split(value, delimiter);

		double[] doubleValues = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			doubleValues[i] = Double.parseDouble(values[i]);
		}
		return doubleValues;
	}

	public static short[] shortArray(String value) {
		return shortArray(value, DELIMITER);
	}

	public static short[] shortArray(String value, char delimiter) {
		if (!notEmpty(value)) {
			return null;
		}
		String[] values = StringUtils.split(value, delimiter);

		short[] shortValues = new short[values.length];
		for (int i = 0; i < values.length; i++) {
			shortValues[i] = Short.parseShort(values[i]);
		}
		return shortValues;
	}

	public static short[] shortArray(String value, String delimiter) {
		if (!notEmpty(value)) {
			return null;
		}
		String[] values = StringUtils.split(value, delimiter);

		short[] shortValues = new short[values.length];
		for (int i = 0; i < values.length; i++) {
			shortValues[i] = Short.parseShort(values[i]);
		}
		return shortValues;
	}

	public static long[] longArray(String value) {
		return longArray(value, DELIMITER);
	}

	public static long[] longArray(String value, char delimiter) {
		if (!notEmpty(value)) {
			return null;
		}
		String[] values = StringUtils.split(value, delimiter);

		long[] longValues = new long[values.length];
		for (int i = 0; i < values.length; i++) {
			longValues[i] = Long.parseLong(values[i]);
		}
		return longValues;
	}

	public static long[] longArray(String value, String delimiter) {
		if (!notEmpty(value)) {
			return null;
		}
		String[] values = StringUtils.split(value, delimiter);

		long[] longValues = new long[values.length];
		for (int i = 0; i < values.length; i++) {
			longValues[i] = Long.parseLong(values[i]);
		}
		return longValues;
	}

	public static List<long[]> grouping(long[] values, int groupSize) {
		if (notEmpty(values)) {

			Assertion.isPositive(groupSize, "divideSize must be bigger than 0");

			int groupLength = values.length / groupSize + ((values.length % groupSize) > 0 ? 1 : 0);

			List<long[]> longArryGroup = new LinkedList<long[]>();
			long[] valueArray = null;
			for (int i = 0; i < groupLength; i++) {
				int arrayLength = (i < groupLength - 1 || values.length % groupSize == 0) ? groupSize : (values.length % groupSize);

				valueArray = new long[arrayLength];
				for (int j = 0; j < arrayLength; j++) {
					valueArray[j] = values[i * groupSize + j];
				}
				longArryGroup.add(valueArray);
			}

			return longArryGroup;
		}
		return null;
	}
	
	public static <T> List<List<T>> grouping(List<T> values, int groupSize) {
		if (values != null && values.size() > 0) {

			Assertion.isPositive(groupSize, "divideSize must be bigger than 0");

			int groupLength = values.size() / groupSize + ((values.size() % groupSize) > 0 ? 1 : 0);

			List<List<T>> longArryGroup = new LinkedList<List<T>>();
			for (int i = 0; i < groupLength; i++) {
				int arrayLength = (i < groupLength - 1 || values.size() % groupSize == 0) ? groupSize : (values.size() % groupSize);

				List<T> valueArray = new ArrayList<T>();
				for (int j = 0; j < arrayLength; j++) {
					valueArray.add(values.get(i * groupSize + j));
				}
				longArryGroup.add(valueArray);
			}

			return longArryGroup;
		}
		return null;
	}

	/** join */
	public static String join(long[] values) {
		return join(values, DELIMITER);
	}

	public static String join(String[] values) {
		return StringUtils.join(values, DELIMITER);
	}

	public static String join(long[] values, String delimiter) {
		return org.apache.commons.lang.StringUtils.join(org.apache.commons.lang.ArrayUtils.toObject(values), delimiter);
	}

	/** as */
	public static short asPrimitiveShort(Object value) {
		String stringValue = asString(value);
		if (notEmpty(asString(stringValue))) {
			if (org.apache.commons.lang.math.NumberUtils.isNumber(stringValue)) {
				return Short.parseShort(stringValue);
			}
		}
		return INVALID_NUMBER_VALUE;
	}

	public static long asPrimitiveLong(Object value) {
		String stringValue = asString(value);
		if (notEmpty(stringValue)) {
			if (org.apache.commons.lang.math.NumberUtils.isNumber(stringValue)) {
				return Long.parseLong(stringValue);
			}
		}
		return INVALID_NUMBER_VALUE;
	}

	public static double asPrimitiveDouble(Object value) {
		String stringValue = asString(value);
		if (notEmpty(asString(stringValue))) {
			if (org.apache.commons.lang.math.NumberUtils.isNumber(stringValue)) {
				return Double.parseDouble(stringValue);
			}
		}
		return INVALID_NUMBER_VALUE;
	}

	public static String asString(Object object) {
		if (null != object) {
			return StringUtils.trim(String.valueOf(object));
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <E> E[] asArray(List<E> list) {
		if (notEmpty(list)) {
			return (E[]) list.toArray();
		}
		return null;
	}

	public static boolean equal(String left, String right) {
		if (null == left && null == right) {
			return true;
		}
		if (null != left && null != right && left.equals(right)) {
			return true;
		}
		return false;
	}

	public static String trim(String string) {
		if (null == string) {
			return null;
		}

		return StringUtils.trim(string);
	}

	public static <E> List<E> unionList(List<E> one, List<E> another) {
		List<E> resultList = new ArrayList<E>();
		if (notEmpty(one)) {
			resultList.addAll(one);
		}
		if (notEmpty(another)) {
			resultList.addAll(another);
		}
		return escapeEmpty(resultList);
	}

	/** escape */
	public static long[] escapeDuplication(long[] values) {
		if (Detect.notEmpty(values)) {
			Set<Long> set = new LinkedHashSet<Long>();
			set.addAll(Arrays.asList(ArrayUtils.toObject(values)));
			return ArrayUtils.toPrimitive((Long[]) set.toArray(new Long[0]));
		}
		return null;
	}

	/**
	 * Remove the duplicate element in List according to the specified keys in List bean and return a new list.</br>
	 * 
	 * If the parameters are empty or exception occurred, original list will be returned.
	 * 
	 * @param list
	 *            To be processed list
	 * @param
	 *              fields in List bean as keys
	 * @return
	 */
	public static <E> List<E> escapeDuplication(List<E> list, String... fields) {
		if (!notEmpty(list) || !notEmpty(fields)) {
			return null;
		}

		for (String field : fields) {
			Assertion.notEmpty(field, "field not found, fields=" + join(fields));
			if (!notEmpty(field)) {
				throw new IllegalArgumentException("Key is empty.");
			}
		}

		List<E> returnValue = new ArrayList<E>();
		Set<String> keySet = new HashSet<String>();

		for (E t : list) {
			StringBuffer hashCodeKey = new StringBuffer();
			for (String field : fields) {
				try {
					hashCodeKey.append(BeanUtils.getProperty(t, field));
					hashCodeKey.append(DELIMITER);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			if (!keySet.contains(hashCodeKey.toString())) {
				keySet.add(hashCodeKey.toString());
				returnValue.add(t);
			}
		}
		return returnValue;
	}

	public static <E> List<E> escapeEmpty(List<E> list) {
		if (notEmpty(list)) {
			return list;
		}
		return null;
	}

	public static String escapeVarchar(String value) {
		if (notEmpty(value) && value.length() > 4000) {
			return value.substring(0, 3990);
		}
		return value;
	}

	public static <E> E firstOne(List<E> list) {
		if (notEmpty(list)) {
			return list.get(0);
			// return list.iterator().next();
		}
		return null;
	}

	public static boolean onlyOne(List<?> list) {
		if (notEmpty(list) && list.size() == 1) {
			return true;
		}
		return false;
	}

	public static <E> List<E> unmodifiableList(List<E> list) {
		if (notEmpty(list)) {
			return Collections.unmodifiableList(list);
		}
		return null;
	}

	public static boolean between(long value, long floor, long ceil) {
		if (value >= floor && value <= ceil) {
			return true;
		} else {
			return false;
		}
	}

	public static String changeSpecialChar(String s){
	    s = s.replace("\\", "\\\\");
        s = s.replace("\r", "\\r");
        s = s.replace("\n", "\\n");
        s = s.replace("\"", "\\\"");
        s = s.replace("\'", "\\\'");
        return s;
	}
	
	public static void main(String[] args) throws Exception {
		// System.out.println(longArray("1", "."));

		List<long[]> longValueGroup = grouping(new long[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 5);
		if (null != longValueGroup) {
			for (int i = 0; i < longValueGroup.size(); i++) {
				long[] longValue = longValueGroup.get(i);

				System.out.println(longValue.length + ": " + join(longValue));
			}
		}

		//System.out.println(JsonUtil.marshal(escapeDuplication(new long[] { 1, 2, 2, 5, 2, 2, 1, 2, 3, 4, 1, 2, 3 })));
	}
}
