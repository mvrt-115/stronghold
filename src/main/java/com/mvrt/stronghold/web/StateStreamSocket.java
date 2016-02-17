package com.mvrt.stronghold.web;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

/**
 * Class necessary to work WebSocket.
 *
 * @author Siddharth Gollapudi
 */
public class StateStreamSocket extends WebSocketAdapter {
  HashMap<String, Boolean> subscribedKeys = new HashMap<String, Boolean>();
  private boolean running = true;

  /**
   * Show that the class can be updated.
   * @return that it is running and the hashmap isn't empty
   */
  public boolean canBeUpdated() {
    return running && !subscribedKeys.isEmpty();
  }

  /**
   * Connection will be closed.
   * @param statusCode the close status code
   * @param reason optional reason for close.
   */
  public void onWebSocketClose(int statusCode, String reason) {
    super.onWebSocketClose(statusCode, reason);
    WebServer.unregisterStateStreamSocket(this);
  }

  /**
   * Session is successfully created and ready to use.
   * @param session the websocket session
   */
  public void onWebSocketConnect(Session session) {
    super.onWebSocketConnect(session);
    WebServer.registerStateStreamSocket(this);
  }

  /**
   * A WebSocket exception has occurred.
   * @param cause the cause for the error.
   */
  public void onWebSocketError(Throwable cause) {
    System.err.println("WebSocket Error" + cause);
    WebServer.unregisterStateStreamSocket(this);
  }

  /**
   * A WebSocket Text frame was received.
   * @param message the message
   */
  public void onWebSocketText(String message) {
    JSONParser parser = new JSONParser();
    Object obj;
    try {
      obj = parser.parse(message);
    } catch (ParseException e) {
      e.printStackTrace();
      return;
    }

    JSONObject cmd = (JSONObject) obj;
    if (cmd != null) {
      String action = (String) cmd.get("action");
      if ("pause".equals(action)) {
        running = false;
      } else if ("start".equals(action)) {
        running = true;
      }
      if ("subscribe".equals(action)) {
        JSONArray keys = (JSONArray) cmd.get("keys");
        for (Object key : keys) {
          subscribe((String) key);
        }
      } else if ("unsubscribe".equals(action)) {
        JSONArray keys = (JSONArray) cmd.get("keys");
        for (Object key : keys) {
          unsubscribe((String) key);
        }
      }
    }
    if (isConnected()) {
      update();
    }
  }

  /**
   * A WebSocket binary frame has been received.
   * @param arg0 dummy argument
   * @param arg1 dummy argument
   * @param arg2 dummy argument
   */
  @Override
  public void onWebSocketBinary(byte[] arg0, int arg1, int arg2) {
  }


  public boolean update() {
    if (!isConnected()) {
      return false;
    }
    if (!running || subscribedKeys.keySet().size() == 0) {
      return true;
    }
    String[] keys = subscribedKeys.keySet().toArray(new String[subscribedKeys.size()]);

    return true;
  }

  /**
   * Put the given key in the hashmap to show subscription.
   * @param key in question
   */
  private void subscribe(String key) {
    subscribedKeys.put(key, true);
  }

  /**
   * Remove key from hashmap to show unsubscribed-ness.
   * @param key key to signal removal
   */
  private void unsubscribe(String key) {
    subscribedKeys.remove(key);
  }
}
