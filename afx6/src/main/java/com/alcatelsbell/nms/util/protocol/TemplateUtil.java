/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alcatelsbell.nms.util.protocol;

/**
 *
 * @author Ronnie.Chen
 */

 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateUtil {

    private static Log logger = LogFactory.getLog(TemplateUtil.class.getName());
    private Pattern typePattern = Pattern.compile("\\#\\{((.|\\s)+?)\\}");
    private Map<String, String> variableMap = new HashMap();

    public TemplateUtil() {
    }

    public TemplateUtil(Pattern _typePattern) {
        this.typePattern = _typePattern;
    }

    public void process(String currentString, String pString, String rpattern) {
        if (currentString != null) {
            int i;
            List<String> nameList = new Vector();

            Matcher typeMatch = this.typePattern.matcher(pString);
            StringBuffer sb = new StringBuffer();
            while (typeMatch.find()) {
                nameList.add(typeMatch.group(1));
                logger.info(typeMatch.group(1));

                typeMatch.appendReplacement(sb, Matcher.quoteReplacement(rpattern));
            }
            typeMatch.appendTail(sb);

            logger.info("sb : " + sb.toString());

            Pattern p = Pattern.compile(sb.toString());

            typeMatch = p.matcher(currentString);

            if (typeMatch.find()) {
                for (i = 0; i < nameList.size(); ++i) {
                    String svalue = typeMatch.group(i + 1);
                    this.variableMap.put(nameList.get(i), svalue);
                    logger.info("name : " + ((String) nameList.get(i)) + " ,   value : " + svalue);
                }
            } else {
                for (i = 0; i < nameList.size(); ++i) {
                    this.variableMap.remove(nameList.get(i));
                    logger.info("name : " + ((String) nameList.get(i)));
                }
            }
        }
    }

    public String replace(String oString) {
        String result = "";
        Matcher typeMatch = this.typePattern.matcher(oString);
        StringBuffer sb = new StringBuffer();
        while (typeMatch.find()) {
            String name = typeMatch.group(1);
            if (this.variableMap.get(name) != null) {
                typeMatch.appendReplacement(sb, Matcher.quoteReplacement((String) this.variableMap.get(name)));
            }
        }

        typeMatch.appendTail(sb);
        result = sb.toString();
        logger.info(result);
        return result;
    }

    public Map<String, String> getVariableMap() {
        return this.variableMap;
    }

    public static void main(String[] args) {
        TemplateUtil tu = new TemplateUtil();

        tu.process("Last login: Fri Jun\r\n  4 13:07:18 from [172.16.6.253]?Solaris", "from \\[#{a}\\]\\?", "(.*)");
        System.out.println("a : " + ((String) tu.getVariableMap().get("a")));
        tu.process("Last login: Fri Jun\r\n  4 13:07:18 from [172.16.6.253?Solaris", "from \\[#{a}\\]\\?", "(.*)");
        System.out.println("a : " + ((String) tu.getVariableMap().get("a")));

        tu.process("Last login: Fri Jun\r\n  4 13:07:18 from 172.16.6.253\nSolaris", "172.16.6.253\n#{a}s", "(.*)");
        System.out.println("a : " + ((String) tu.getVariableMap().get("a")));
    }
}