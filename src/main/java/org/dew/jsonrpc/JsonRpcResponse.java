package org.dew.jsonrpc;

import java.io.Serializable;

/**
 * Bean JsonRpcResponse.
 */
public class JsonRpcResponse implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = -3466179240914856510L;
  protected int id;
  protected String jsonrpc;
  protected Object result;
  protected JsonRpcError error;

  public JsonRpcResponse()
  {
    this.jsonrpc = "2.0";
  }

  public JsonRpcResponse(int id, Object result)
  {
    this.jsonrpc = "2.0";
    this.id = id;
    this.result = result;
  }

  public JsonRpcResponse(int id, JsonRpcError error)
  {
    this.jsonrpc = "2.0";
    this.id = id;
    this.error = error;
  }
  
  public JsonRpcResponse(JsonRpcError error)
  {
    this.jsonrpc = "2.0";
    this.id = 1;
    this.error = error;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getJsonrpc() {
    return jsonrpc;
  }

  public void setJsonrpc(String jsonrpc) {
    this.jsonrpc = jsonrpc;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public JsonRpcError getError() {
    return error;
  }

  public void setError(JsonRpcError error) {
    this.error = error;
  }
  
  @Override
  public boolean equals(Object object) {
    if(object instanceof JsonRpcResponse) {
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
    if(error != null) {
      return id + "#err#" + error.hashCode();
    }
    if(result != null) {
      return id + "#res#" + result.hashCode();
    }
    return String.valueOf(id);
  }
}
