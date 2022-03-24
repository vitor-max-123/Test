package com.example.demo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

/**
 * @author Administrator
 * @create 2021/6/8 0008 21:07
 */
public class HttpParameterWrapper extends HttpServletRequestWrapper {

    private Map<String,String[]> parameterMap;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public HttpParameterWrapper(HttpServletRequest request,Map<String,String[]> parameterMap) {
        super(request);
        if(Objects.isNull(parameterMap)){
            // Map不能进行写操作（request的ParameterMap赋值给当前对象的Map，lock后不能进行写操作）
            this.parameterMap = request.getParameterMap();

        }else{
            // Map可以进行写操作
            this.parameterMap = parameterMap;
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.parameterMap;
    }

    @Override
    public String getParameter(String name) {
        String[] paramMap = this.parameterMap.get(name);
        if(Objects.isNull(paramMap)){
            return null;
        }
        return paramMap[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return this.parameterMap.get(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<>(this.parameterMap.keySet());
        return vector.elements();
    }

    public void setParameterMap(Map<String,String[]> parameterMap){
        this.parameterMap = parameterMap;
    }
}
