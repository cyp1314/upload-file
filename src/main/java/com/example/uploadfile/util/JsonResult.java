package com.example.uploadfile.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult implements Serializable {
    private String code;
    private boolean success;
    private String message;

}
