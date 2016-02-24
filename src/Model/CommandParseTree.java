package Model;

import java.util.List;

public class CommandParseTree {
	ParseNode root; 
	
	public CommandParseTree(ParseNode pNode) {
		root = pNode;  
	}
	
	class ParseNode { 
		Command c; 
		List<ParseNode> params; 
		Object value; 
		boolean paramsGood; 
		
		ParseNode(Command c, List<ParseNode> params) { 
			this.c = c;
			this.params = params;  	
			value = null; 
			paramsGood = false;
		}
		
		void setValue(Object value) { 
			this.value = value;
			c = null; 
			params = null; 
		}
		
		Object getValue() { 
			return value;
		}
	}

}
