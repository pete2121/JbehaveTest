package com.site.tools;

import java.lang.reflect.Field;

import ObjectRepository.BOX1Objects;

public class ReflectionParser  {
	

	public String getValuebyFieldName(String targetName) throws Exception
	
	{	String targetValue = null;
		final Class<?> myInterfaceClass = BOX1Objects.class;
	    if( myInterfaceClass != null )
	    {
	        Field[] flist = myInterfaceClass.getDeclaredFields();
	        for (Field f : flist ) {
	        	targetName=targetName.replace("'", "");
	        	targetName=targetName.replaceAll("\\s+","");
	        	String ActualName=f.getName().replaceAll("\\s+","");
	         	System.out.println(ActualName);
	        	System.out.println(targetName);
	        	if( ActualName.trim().equals(targetName.trim())) {
	        		targetValue = (String) f.get(myInterfaceClass);
	            	System.out.println("The matching field is " + targetValue);
	            	break;
	            	
	                    }
	        					}
	    }
		return targetValue;
	                } 
	                		}
	            

