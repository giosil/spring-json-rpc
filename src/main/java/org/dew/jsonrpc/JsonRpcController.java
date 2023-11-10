package org.dew.jsonrpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonRpcController {

  Logger logger = LoggerFactory.getLogger(JsonRpcController.class);
  
  static {
    JsonRpc.addHandler("DEMO", new org.dew.demo.Demo());
  }

  @GetMapping("/")
  public String index() {
    logger.info("index...");
    
    return "JsonRpcController is runnnig.";
  }
  
  @PostMapping("/rpc")
  public JsonRpcResponse invoke(@RequestBody JsonRpcRequest request) {
    logger.info("invoke...");
    
    return JsonRpc.invoke(request);
  }

}
