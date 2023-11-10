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

  @GetMapping("/rpc")
  public String hello() {
    logger.info("JsonRpcController.hello");
    return "Hello from JsonRpc.";
  }

  @PostMapping("/rpc")
  public JsonRpcResponse invoke(@RequestBody JsonRpcRequest request) {
    return JsonRpc.invoke(request);
  }

}
