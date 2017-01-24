package com.alcatelsbell.nms.common;

import com.alcatelsbell.nms.db.components.client.JpaClient;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 14-1-24
 * Time: 上午11:21
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class XMLLoadDictionary {
    private Log logger = LogFactory.getLog(getClass());
    private HashMap<String,List<DicEntry>> dicEntries = new HashMap<String, List<DicEntry>>();
    private HashMap<String,Dic> dics = new HashMap<String, Dic>();
    private HashMap<String,ConfigurationNode> dicNodes = new HashMap<String, ConfigurationNode>();

    private static XMLLoadDictionary inst = new XMLLoadDictionary();
    public static XMLLoadDictionary getInstance() {
        return inst;
    }
    private XMLLoadDictionary() {
        try {
            init();
        } catch (ConfigurationException e) {
            logger.error(e, e);
        }
    }

    public void init() throws ConfigurationException {
        XMLConfiguration xmlConfiguration = new XMLConfiguration(XMLLoadDictionary.class.getClassLoader()
                .getResource("dictionary.xml"));

        List<ConfigurationNode> children = xmlConfiguration.getRootNode().getChildren();
        for (int i = 0; i < children.size(); i++) {
            ConfigurationNode configurationNode = children.get(i);
            if (configurationNode.getName().equals("Dictionary"))
                loadDictionary(configurationNode);
        }
    }

    private void loadDictionary(ConfigurationNode dicNode) {
        String type = getAttributeValue(dicNode, "type");
        String name = getAttributeValue(dicNode,"name");
        String desc = getAttributeValue(dicNode,"desc");
        dics.put(name,new Dic(name, type,desc));
        dicNodes.put(name,dicNode);
        try {
            if ("BObject".equals(type)) {
                loadBObjectDictionary(name,dicNode);
            } else if ("Define".equals(type)) {
                loadDefinedDictionary(name,dicNode);
            }
            else {
                logger.error("type attribute not found :" + dicNode.getName());
            }
        } catch (Exception e) {
            logger.error(e+" Error loading dictionary :"+name , e);
        }
    }

    private void loadDefinedDictionary(String name,ConfigurationNode dicNode) {
        List<ConfigurationNode> children = dicNode.getChildren();
        for (int i = 0; i < children.size(); i++) {
            ConfigurationNode entryNode = children.get(i);
            String desc = getAttributeValue(entryNode, "desc");
            String value = getAttributeValue(entryNode, "value");
            String code = getAttributeValue(entryNode,"code");
            String color = getAttributeValue(entryNode,"color");
            DicEntry dicEntry = new DicEntry(desc,(value == null || value.isEmpty()) ? -1 : Integer.parseInt(value),code,color);
            addDicEntry(name,dicEntry);
        }
    }

    private void addDicEntry(String dicName,DicEntry entry) {
        List<DicEntry> dicEntries = this.dicEntries.get(dicName);
        if (dicEntries == null) {
            dicEntries = new ArrayList<DicEntry>();
            this.dicEntries.put(dicName, dicEntries);
        }
        dicEntries.add(entry);
        logger.info(dicName+": desc="+entry.desc+" value="+entry.value+" code="+entry.code+" color="+entry.color);
    }

    public List<DicEntry> getDicEntries(String dicName) {
        Dic dic = dics.get(dicName);
        if (dic != null) {
            if (dic.getType().equals("BObject")) {
                try {
                    clear(dicName);
                    loadBObjectDictionary(dicName,dicNodes.get(dicName));
                } catch (Exception e) {
                    logger.error(e, e);
                }
            }

            return dicEntries.get(dicName);
        }
        return null;
    }

    public Dic getDic(String dicName) {
        return dics.get(dicName);
    }

    public void clear(String dicName) {
        dicEntries.remove(dicName);
    }

    /**
     * @todo
     * @param dicNode
     */
    private void loadBObjectDictionary(String name,ConfigurationNode dicNode) throws Exception{

        String codeField = getAttributeValue(dicNode,"keyField");
        String labelField = getAttributeValue(dicNode,"labelField");
        String cls = getAttributeValue(dicNode,"class");
        List allObjects = JpaClient.getInstance().findAllObjects(Class.forName(cls));
        for (int i = 0; i < allObjects.size(); i++) {
            Object o = allObjects.get(i);

            Object code = CommonUtil.getInstance().getFiledValue(o, codeField);
            Object label = CommonUtil.getInstance().getFiledValue(o, labelField);
            if (code != null && label != null)      {
                if (code instanceof Number) {
                    addDicEntry(name,new DicEntry(label.toString(),((Number) code).intValue(),null,null));
                }
                if (code instanceof String) {
                    addDicEntry(name,new DicEntry(label.toString(),-1,(String)code,null));
                }
            }

        }
    }

    private String getAttributeValue(ConfigurationNode node,String attributeName){
        List<ConfigurationNode> attributes = node.getAttributes(attributeName);
        if (attributes != null && attributes.size() > 0) {
            Object value = attributes.get(0).getValue();
            return value.toString();
        }
        return null;
    }

    public static void main(String[] args) throws ConfigurationException {
        new XMLLoadDictionary();
    }
}
