package com.mvrt.stronghold.web;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * The WebSocket servlet.
 *
 * @author Siddharth Gollapudi
 */
public class StateStreamServlet extends WebSocketServlet {

  /**
   * Setting up the servlet. Pretty important for communicating with JS.
   *
   * @param factory necessary to work with Websocket servlets.
   */
  @Override
  public void configure(WebSocketServletFactory factory) {
    factory.register(StateStreamSocket.class);
  }

}
