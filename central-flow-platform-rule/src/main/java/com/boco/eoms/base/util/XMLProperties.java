package com.boco.eoms.base.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

//import com.boco.eoms.base.tool.LogOutTool;

public class XMLProperties {
	  private File file;
	  private Document doc;
	  private SAXBuilder builder;
	  /**
	   * Parsing the XML file every time we need a property is slow. Therefore,
	   * we use a Map to cache property values that are accessed more than once.
	   */
	  private Map propertyCache = new HashMap();

	  /**
	   * Creates a new XMLProperties object.
	   *
	   * @parm file the full path the file that properties should be read from
	   *      and written to.
	   */
	  public XMLProperties() {
	  	builder = new SAXBuilder();
	  }
	  
	  
	  /**
		 * Creates a new XMLProperties object.
		 * 
		 * @parm file the full path the file that properties should be read from and
		 *       written to.
		 */
		public XMLProperties(String file) {
			this.file = new File(file);

			try {
				SAXBuilder builder = new SAXBuilder();
				// Strip formatting
				DataUnformatFilter format = new DataUnformatFilter();
				builder.setXMLFilter(format);
				doc = builder.build(new File(file));
				// builder.build(StaticMethod.getFileInputStream(file));
			} catch (Exception e) {
				System.err.println("Error creating XML parser in "
						+ "PropertyManager.java");
				e.printStackTrace();
			}
		}
	  
	  public void loadSchema(String xmlSchema) {
	    try {
	        doc = builder.build(xmlSchema);
	    }
	    catch (Exception e) {
//	        LogOutTool.getLogger(XMLProperties.class).info( "Error creating XML parser in "
//		            + "PropertyManager.java");
	    }
	  }
	  
	  public void loadFile(String filePath) {
	    try {
	    	doc = builder.build(new File(filePath));
	    }
	    catch (Exception e) {
//	    	LogOutTool.getLogger(XMLProperties.class).info( "Error creating XML parser in "
//		            + "PropertyManager.java");
	    }
	  }
	  
	  public void loadFile(InputStream fileInputStream) {
		    try {
		    	doc = builder.build(fileInputStream);
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
//		    	LogOutTool.getLogger(XMLProperties.class).info( "Error creating XML parser in "
//			            + "PropertyManager.java");
		    }
		  }
	  
	  /**
	   * Returns the value of the specified property.
	   *
	   * @param name the name of the property to get.
	   * @return the value of the specified property.
	   */
	  public String getProperty(String name) {
	      if (propertyCache.containsKey(name)) {
	          return (String)propertyCache.get(name);
	      }

	      String[] propName = parsePropertyName(name);
	      // Search for this property by traversing down the XML heirarchy.
	      Element element = doc.getRootElement();
	      for (int i = 0; i < propName.length; i++) {
	          element = element.getChild(propName[i]);
	          if (element == null) {
	              // This node doesn't match this part of the property name which
	              // indicates this property doesn't exist so return null.
	              return null;
	          }
	      }
	      // At this point, we found a matching property, so return its value.
	      // Empty strings are returned as null.
	      String value = element.getText();
	      if ("".equals(value)) {
	          return null;
	      }
	      else {
	          // Add to cache so that getting property next time is fast.
	          value = value.trim();
	          propertyCache.put(name, value);
	          return value;
	      }
	  }
	  
	  public String getProperty(String name, String value) {
		  return this.getProperty(name) != null ? this.getProperty(name) :
		      value;
		}

	  public String getProperty(String name,int Index) {

	    String[] propName = parsePropertyName(name);
	    // Search for this property by traversing down the XML heirarchy.
	    Element element = doc.getRootElement();
	    for (int i = 0; i < propName.length-1; i++) {
	      element = element.getChild(propName[i]);
	        if (element == null) {
	            // This node doesn't match this part of the property name which
	            // indicates this property doesn't exist so return null.
	            return null;
	        }
	    }
	    try {
	      List elementList = element.getChildren(propName[propName.length-1]);
	        element =(Element) elementList.get(Index);
	      if (element == null)
	        return null;
	      String value = element.getText();
	      if ("".equals(value)) {
	        return null;
	      }
	      else {
	        value = value.trim();
	        return value;
	      }
	    }catch (Exception e) {
	      return null;
	    }
	  }

	  public String getAttribute(String name,int Index,String Attribute) {
	      String[] propName = parsePropertyName(name);
	      // Search for this property by traversing down the XML heirarchy.
	      Element element = doc.getRootElement();
	      for (int i = 0; i < propName.length; i++) {
	        element = element.getChild(propName[i]);
	          if (element == null) {
	              // This node doesn't match this part of the property name which
	              // indicates this property doesn't exist so return null.
	              return null;
	          }
	      }
	      try {
	        List elementList = element.getChildren();
	          element =(Element) elementList.get(Index);
	        if (element == null)
	          return null;
	        String value = element.getAttribute(Attribute).getValue();
	        if ("".equals(value)) {
	          return null;
	        }
	        else {
	          value = value.trim();
	          return value;
	        }
	      }catch (Exception e) {
	        return null;
	      }
	  }
	  
	  public String getAttribute(String name,String Attribute) {
	    String[] propName = parsePropertyName(name);
	    // Search for this property by traversing down the XML heirarchy.
	    Element element = doc.getRootElement();
	    for (int i = 0; i < propName.length; i++) {
	      element = element.getChild(propName[i]);
	        if (element == null) {
	            // This node doesn't match this part of the property name which
	            // indicates this property doesn't exist so return null.
	            return null;
	        }
	    }
	    try {
	      String value = element.getAttribute(Attribute).getValue();
	      if ("".equals(value)) {
	        return null;
	      }
	      else {
	        value = value.trim();
	        return value;
	      }
	    }catch (Exception e) {
	      return null;
	    }
	}


	  /**
	   * Return all children property names of a parent property as a String array,
	   * or an empty array if the if there are no children. For example, given
	   * the properties <tt>X.Y.A</tt>, <tt>X.Y.B</tt>, and <tt>X.Y.C</tt>, then
	   * the child properties of <tt>X.Y</tt> are <tt>A</tt>, <tt>B</tt>, and
	   * <tt>C</tt>.
	   *
	   * @param parent the name of the parent property.
	   * @return all child property values for the given parent.
	   */
	  public String [] getChildrenProperties(String parent) {
	      String[] propName = parsePropertyName(parent);
	      // Search for this property by traversing down the XML heirarchy.
	      Element element = doc.getRootElement();
	      for (int i = 0; i < propName.length; i++) {
	          element = element.getChild(propName[i]);
	          if (element == null) {
	              // This node doesn't match this part of the property name which
	              // indicates this property doesn't exist so return empty array.
	              return new String [] { };
	          }
	      }
	      // We found matching property, return names of children.
	      List children = element.getChildren();
	      int childCount = children.size();
	      String [] childrenNames = new String[childCount];
	      for (int i=0; i<childCount; i++) {
	          childrenNames[i] = ((Element)children.get(i)).getName();
	      }
	      return childrenNames;
	  }

	  /**
	   * Sets the value of the specified property. If the property doesn't
	   * currently exist, it will be automatically created.
	   *
	   * @param name the name of the property to set.
	   * @param value the new value for the property.
	   */
	  public void setProperty(String name, String value) {
	      // Set cache correctly with prop name and value.
	      propertyCache.put(name, value);

	      String[] propName = parsePropertyName(name);
	      // Search for this property by traversing down the XML heirarchy.
	      Element element = doc.getRootElement();
	      for (int i=0; i<propName.length; i++) {
	          // If we don't find this part of the property in the XML heirarchy
	          // we add it as a new node
	          if (element.getChild(propName[i]) == null) {
	              element.addContent(new Element(propName[i]));
	          }
	          element = element.getChild(propName[i]);
	      }
	      // Set the value of the property in this node.
	      element.setText(value);
	      // write the XML properties to disk
	      saveProperties();
	  }

	  /**
	   * Deletes the specified property.
	   *
	   * @param name the property to delete.
	   */
	  public void deleteProperty(String name) {
	      String[] propName = parsePropertyName(name);
	      // Search for this property by traversing down the XML heirarchy.
	      Element element = doc.getRootElement();
	      for (int i=0; i<propName.length-1; i++) {
	          element = element.getChild(propName[i]);
	          // Can't find the property so return.
	          if (element == null) {
	              return;
	          }
	      }
	      // Found the correct element to remove, so remove it...
	      element.removeChild(propName[propName.length-1]);
	      // .. then write to disk.
	      saveProperties();
	  }

	  /**
	   * Saves the properties to disk as an XML document. A temporary file is
	   * used during the writing process for maximum safety.
	   */
	  public synchronized void saveProperties() {
	      OutputStream out = null;
	      boolean error = false;
	      // Write data out to a temporary file first.
	      File tempFile = null;
	      try {
	          tempFile = new File(file.getParentFile(), file.getName() + ".tmp");
	          // Use JDOM's XMLOutputter to do the writing and formatting. The
	          // file should always come out pretty-printed.
	          XMLOutputter outputter = new XMLOutputter();// XMLOutputter("    ", true);
	          out = new BufferedOutputStream(new FileOutputStream(tempFile));
	          outputter.output(doc, out);
	      }
	      catch (Exception e) {
	          e.printStackTrace();
	          // There were errors so abort replacing the old property file.
	          error = true;
	      }
	      finally {
	          try {  out.close();  }
	          catch (Exception e) {
	              e.printStackTrace();
	              error = true;
	          }
	      }
	      // No errors occured, so we should be safe in replacing the old
	      if (!error) {
	          // Delete the old file so we can replace it.
	          file.delete();
	          // Rename the temp file. The delete and rename won't be an
	          // automic operation, but we should be pretty safe in general.
	          // At the very least, the temp file should remain in some form.
	          tempFile.renameTo(file);
	      }
	  }

	  /**
	   * Returns an array representation of the given Jive property. Jive
	   * properties are always in the format "prop.name.is.this" which would be
	   * represented as an array of four Strings.
	   *
	   * @param name the name of the Jive property.
	   * @return an array representation of the given Jive property.
	   */
	  private String[] parsePropertyName(String name) {
	      // Figure out the number of parts of the name (this becomes the size
	      // of the resulting array).
	      int size = 1;
	      for (int i=0; i<name.length(); i++) {
	          if (name.charAt(i) == '.') {
	              size++;
	          }
	      }
	      String[] propName = new String[size];
	      // Use a StringTokenizer to tokenize the property name.
	      StringTokenizer tokenizer = new StringTokenizer(name, ".");
	      int i = 0;
	      while (tokenizer.hasMoreTokens()) {
	          propName[i] = tokenizer.nextToken();
	          i++;
	      }
	      return propName;
	  }

	  /**
	     *获取某个属性的子属性的值
	     * @param parent 父属性
	     * @param post 位置
	     * @return 属性Hashtable
	     */
	    public Hashtable getChildrenPropertiesValue(String parent, int post) {
	      Hashtable hashtable = new Hashtable();
	      Element element = doc.getRootElement();

	      String[] propName = parsePropertyName(parent);

	      if (!parent.trim().equals("")) {
	        for (int i = 0; i < propName.length-1; i++) {
	          element = element.getChild(propName[i]);

	          if (element == null) {
	            hashtable = null;
	          }
	        }
	      }

	      List children = element.getChildren();

	      children = ((Element)children.get(post)).getChildren();

	      int childCount = children.size();

	      for (int i = 0; i < childCount; i++) {
	        element = (Element) children.get(i);
	        hashtable.put(element.getName(), element.getText());
	      }

	      return hashtable;
	    }


	  public Element getElement() {
	      return doc.getRootElement();
	  }

	}

