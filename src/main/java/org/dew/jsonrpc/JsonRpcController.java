package org.dew.jsonrpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rpc")
public class JsonRpcController {

  protected Logger logger = LoggerFactory.getLogger(JsonRpcController.class);

  protected JsonRpc jsonrpc;

  @Autowired
  public JsonRpcController(JsonRpc jsonrpc) {
    this.jsonrpc = jsonrpc;
    // Handlers
    this.jsonrpc.addHandler("DEMO", new org.dew.demo.Demo());
  }

  @GetMapping()
  public String hello() {
    logger.info("JsonRpcController.hello");
    return "Hello from JsonRpc.";
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.OK)
  public JsonRpcResponse invoke(@RequestBody JsonRpcRequest request) {
    return jsonrpc.invoke(request);
  }

}
