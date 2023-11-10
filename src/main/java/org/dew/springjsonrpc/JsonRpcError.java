package org.dew.springjsonrpc;

import java.io.Serializable;

/**
 * Bean JsonRpcError.
 */
public class JsonRpcError implements Serializable {
  
  private static final long serialVersionUID = 7122148325861903419L;
  
  protected int code;
  protected String message;
  protected String data;
  
  public JsonRpcError()
  {
  }

  public JsonRpcError(int code)
  {
    this.code = code;
  }

  public JsonRpcError(int code, String message)
  {
    this.code    = code;
    this.message = message;
  }

  public JsonRpcError(int code, Throwable th)
  {
    this.code    = code;
    if(th != null) {
      message = th.getMessage();
      if(message == null || message.length() == 0) {
        message = th.toString();
      }
    }
    else {
      message = "Error";
    }
  }

  public JsonRpcError(int code, String message, String data)
  {
    this.code    = code;
    this.message = message;
    this.data    = data;
  }
  
  public JsonRpcError(int code, Throwable th, String data)
  {
    this.code    = code;
    this.data    = data;
    if(th != null) {
      message = th.getMessage();
      if(message == null || message.length() == 0) {
        message = th.toString();
      }
    }
    else {
      message = "Error";
    }
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object instanceof JsonRpcError) {
      return this.toString().equals(object.toString());
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return toString().hashCode();
  }
  
  @Override
  public String toString() {
    return code + "#" + message;
  }
}
