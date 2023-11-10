package org.dew.springjsonrpc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonRpcController {

  static {
    JsonRpc.addHandler("DEMO", new org.dew.demo.Demo());
  }

  @GetMapping("/")
  public String index() {
    return "JsonRpcController is runnnig.";
  }
  
  @PostMapping("/rpc")
  public JsonRpcResponse invoke(@RequestBody JsonRpcRequest request) {
    return JsonRpc.invoke(request);
  }

}
