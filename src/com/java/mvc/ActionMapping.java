/**
 *时间：2014-7-14下午8:39:19
 *
 *作者：张国宝
 *功能：TODO
 */
package com.java.mvc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.java.mvc.ActionConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ActionMapping {

	static Map<String, ActionConfig> actionMapping = new HashMap<String, ActionConfig>();

	public static void addMap(String path, ActionConfig actionConfig) {
		actionMapping.put(path, actionConfig);
	}

	public static ActionConfig getAction(String path) {
		return actionMapping.get(path);

	}

	public static Object getActionBean(String path) {
		ActionConfig ac = getAction(path);
		if (ac == null) {
			return null;
		}

		return ac.getActionBean();
	}

	/**
	 * 读取配置文件完成映射
	 *
	 * @param configPath
	 * */
	public static void initConfig(String configPath) {
		// 要解析xml文档，最好先定义若干个JavaBean，与xml节点匹配

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File(configPath));

			// 得到根节点
			Element root = document.getDocumentElement();
			NodeList nl = root.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				if (node.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				// 解析元素节点action-mappings
				Element actionMappingsNode = (Element) node;
				parseActionMappingsNode(actionMappingsNode);

			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void parseActionMappingsNode(Element actionMappingsNode) {
		NodeList nl = actionMappingsNode.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			// 判断是不是子节点 不是填出循环
			if (node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			Element actionNode = (Element) node;
			String actionPath = actionNode.getAttribute("path");// 找到节点属性
			String actionClazz = actionNode.getAttribute("class");// 找到节点属性
			String actionMethodName = actionNode.getAttribute("method");// 找到属性节点
			if (actionMethodName==null ||actionMethodName.length()==0)
			{
				actionMethodName="execute";
			}
			try {
				Object action = Class.forName(actionClazz).newInstance();
				ActionConfig ac = new ActionConfig(action, actionMethodName);
				ac.setPath(actionPath);
				addMap(actionPath, ac);// 添加映射关系
				System.out.println(actionClazz + "已经映射......");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
	}
}