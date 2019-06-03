package coop.tecso.examen.exceptions;

import com.google.common.collect.Maps;

import java.util.Map;

public class RestException extends Exception{
    Integer statusCode;
    String message;

    public RestException(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String,Object> getErrorData(){
        Map map = Maps.newHashMap();
        map.put("statusCode",statusCode);
        map.put("message",message);

        return map;
    }
}
