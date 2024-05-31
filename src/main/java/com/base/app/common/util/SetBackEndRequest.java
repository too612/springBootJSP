package com.base.app.common.util;

import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Enumeration;
import java.util.Collections;

public class SetBackEndRequest extends HttpServletRequestWrapper {
    HashMap<String, Object> params;

    @SuppressWarnings("unchecked")
    public SetBackEndRequest(HttpServletRequest request) {
        super(request);
        this.params = new HashMap<String, Object>(request.getParameterMap());
    }

    public String getParameter(String name){
        String returnValue = null;
        String[] paramArray = getParameterValues(name);
        if (paramArray != null && paramArray.length > 0){
            returnValue = paramArray[0];
        }
        return returnValue;
    }

    @SuppressWarnings("unchecked")
    public Map getParameterMap() {
        return Collections.unmodifiableMap(params);
    }

    @SuppressWarnings("unchecked")
    public Enumeration getParameterNames() {
        return Collections.enumeration(params.keySet());
    }

    public String[] getParameterValues(String name) {
        String[] result = null;
        String[] temp = (String[])params.get(name);
        if (temp != null){
            result = new String[temp.length];
            System.arraycopy(temp, 0, result, 0, temp.length);
        }
        return result;
    }


    public void setParameter(String name, String value){
        String[] oneParam = {value};
        setParameter(name, oneParam);
    }

    public void setParameter(String name, String[] value){
        params.put(name, value);
    }
}