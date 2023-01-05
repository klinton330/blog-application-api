package com.blogapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//@ResponseStatus cause spring boot to respond with the specific HTTP status code whenever this exception thrown form the controller
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fileName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fileName, long fieldValue) {
        super(String.format("%s not found with %s:'%s'",resourceName,fileName,fieldValue));//Post not found with id:1
        this.resourceName = resourceName;
        this.fileName = fileName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getFieldValue() {
        return fieldValue;
    }
}
