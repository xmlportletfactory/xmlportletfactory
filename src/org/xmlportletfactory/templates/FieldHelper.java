package org.xmlportletfactory.templates;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlportletfactory.utils.XMLUtils;
import org.xmlportletfactory.xml.xmlportletfactory.DefinitionDocument.Definition.Applications.Application.FileDef.Fields.Field;
import org.xmlportletfactory.xml.xmlportletfactory.DefinitionDocument.Definition.Applications.Application.FileDef.Fields.Field.Type;

public class FieldHelper {
	
	public boolean order(Field field) {
		
		if(field == null) {
			throw new IllegalArgumentException("field argument must not be null");
		}
		
		boolean order = false;
		
		Type type = field.getType();
		
		if(type != null) {
			
			
			Node specificTypeNode = XMLUtils.findFirstElementChild(type.getDomNode());
			
			if(specificTypeNode != null) {
				
				Node orderNode = XMLUtils.findFirstElementChildWithName(specificTypeNode, "order");
			
				if(orderNode != null) {
					NodeList orderChildNodes = orderNode.getChildNodes();
					for (int j = 0; j < orderChildNodes.getLength(); j++) {
						Node c = orderChildNodes.item(j);
						order = c.getNodeValue().contains(
							Boolean.TRUE.toString());
						if(order) {
							System.out
								.println(c);
						}
					}
				}
			}
		}
		
		return order;
	}

}
