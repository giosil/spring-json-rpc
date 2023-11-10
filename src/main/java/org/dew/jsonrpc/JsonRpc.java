package org.dew.jsonrpc;

import java.util.HashMap;
import java.util.Map;

/* external */
import org.dew.util.RefUtil;

public class JsonRpc {

  public static final int PARSE_ERROR_CODE            = -32700;
  public static final int INVALID_REQUEST_ERROR_CODE  = -32600;
  public static final int METHOD_NOT_FOUND_ERROR_CODE = -32601;
  public static final int INVALID_PARAMS_ERROR_CODE   = -32602;
  public static final int INTERNAL_ERROR_CODE         = -32603;
  public static final int SERVER_ERROR_START          = -32000;

  protected static Map<String, Object> handlers = new HashMap<>();
  
  public static void addHandler(String key, Object handler) {
    if(key == null || handler == null) return;
    handlers.put(key, handler);
  }

  public static void removeHandler(String key, Object handler) {
    if(key == null) return;
    handlers.remove(key);
  }

  public static void clear() {
    handlers.clear();
  }

  public static JsonRpcResponse invoke(JsonRpcRequest request) {
    if(request == null) {
      return new JsonRpcResponse(new JsonRpcError(INVALID_REQUEST_ERROR_CODE, "Invalid request"));
    }
    String methodName = request.getMethod();
    if(methodName == null || methodName.length() == 0) {
      return new JsonRpcResponse(new JsonRpcError(INVALID_REQUEST_ERROR_CODE, "Missing method"));
    }
    int sep = methodName.indexOf('.');
    if(sep <= 0) {
      return new JsonRpcResponse(new JsonRpcError(INVALID_REQUEST_ERROR_CODE, "Invalid method"));
    }
    
    String handlerName = methodName.substring(0,sep);
    methodName = methodName.substring(sep+1);
    
    Object handler = handlers.get(handlerName);
    if(handler == null) {
      return new JsonRpcResponse(new JsonRpcError(METHOD_NOT_FOUND_ERROR_CODE, "handler " + RefUtil.msgText(handlerName) + " not found"));
    }
    
    try {
      Object result = RefUtil.invoke(handler, methodName, request.getParams());
      
      return new JsonRpcResponse(request.getId(), result);
    }
    catch(Throwable ex) {
      return new JsonRpcResponse(request.getId(), new JsonRpcError(SERVER_ERROR_START, ex, RefUtil.getStackTrace(ex)));
    }
  }
}
