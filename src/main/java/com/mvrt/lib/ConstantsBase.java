package com.mvrt.lib;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * API class for using constants through the Reflection API. Contains the ability to modify fields
 * through Reflection API and convert to JSON.
 *
 * @author Lee Mracek
 */
public abstract class ConstantsBase {
  private HashMap<String, Boolean> modifiedKeys = new HashMap<>();

  public abstract String getFileLocation();

  public static class Constant {
    public String name;
    public Class<?> type;
    public Object value;

    public Constant(String name, Class<?> type, Object value) {
      this.name = name;
      this.type = type;
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      String oname = ((Constant) o).name;
      Class<?> otype = ((Constant) o).type;
      Object ovalue = ((Constant) o).value;
      return this.name.equals(oname) && this.type.equals(otype) && this.value.equals(ovalue);
    }
  }

  public JSONObject getJsonObjectFromFile() throws IOException, ParseException {
    File file = getFile();
    if (file == null || !file.exists()) {
      return new JSONObject();
    }

    FileReader reader = new FileReader(file);
    JSONParser parser = new JSONParser();
    return (JSONObject) parser.parse(reader);
  }

  public boolean setConstant(String name, Double value) {
    return setConstantRaw(name, value);
  }

  public boolean setConstant(String name, Integer value) {
    return setConstantRaw(name, value);
  }

  public boolean setConstant(String name, String value) {
    return setConstantRaw(name, value);
  }

  public boolean setConstantRaw(String name, Object value) {
    boolean success = false;
    try {
      Field constant = this.getClass().getDeclaredField(name);
      if (java.lang.reflect.Modifier.isStatic(constant.getModifiers())) {
        try {
          Object current = constant.get(this);
          constant.set(this, value);
          success = true;
          if (!value.equals(current)) {
            modifiedKeys.put(name, true);
          }
        } catch (IllegalArgumentException | IllegalAccessException e) {
          System.err.println("Could not set field: " + name);
        }
      }
    } catch (NoSuchFieldException e) {
      System.err.println("Field " + name + "not found!");
    }
    return success;
  }

  public Object getValueForConstant(String name) {
    Field[] declaredFields = this.getClass().getDeclaredFields();
    for (Field field : declaredFields) {
      if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) && field.getName()
          .equals(name)) {
        try {
          return field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException e) {
          System.err.println("Field " + name + " not found!");
          return null;
        }
      }
    }
    System.err.println("Field " + name + " not found!");
    return null;
  }

  public Object getValueForConstant(String name, Object defaultValue) {
    return getValueForConstant(name) == null ? defaultValue : getValueForConstant(name);
  }

  public void loadFromFile() {
    try {
      JSONObject jsonObject = getJsonObjectFromFile();
      Set keys = jsonObject.keySet();
      for (Object o : keys) {
        String key = (String) o;
        Object value = jsonObject.get(o);
        if (value instanceof Long && getConstant(key).type.equals(int.class)) {
          value = new BigDecimal((Long) value).intValueExact();
        }
        setConstantRaw(key, value);
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  public void saveToFile() {
    File file = getFile();
    if (file == null) {
      return;
    }

    try {
      JSONObject json = getJsonObjectFromFile();
      FileWriter writer = new FileWriter(file);
      for (String key : modifiedKeys.keySet()) {
        Object value = getValueForConstant(key);
        json.put(key, value);
      }

      writer.write(json.toJSONString());
      writer.close();
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  public Constant getConstant(String name) {
    try {
      Field constant = getClass().getDeclaredField(name);
      if (!java.lang.reflect.Modifier.isStatic(constant.getModifiers())) {
        throw new NoSuchFieldException();
      }

      return new Constant(constant.getName(), constant.getType(), constant.get(this));
    } catch (NoSuchFieldException e) {
      System.err.println("Field " + name + " not found, returning blank constant!");
    } catch (IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
    }

    return new Constant("", Object.class, 0);
  }

  public Collection<Constant> getConstants() {
    List<Constant> constants = (List<Constant>) getAllConstants();
    int stop = constants.size() - 1;
    for (int i = 0; i < constants.size(); ++i) {
      Constant c = constants.get(i);
      if ("kEndEditableArea".equals(c.name)) {
        stop = i;
      }
    }
    return constants.subList(0, stop);
  }

  public Collection<Constant> getAllConstants() {
    Field[] declaredFields = this.getClass().getDeclaredFields();
    List<Constant> constants = new ArrayList<Constant>(declaredFields.length);
    for (Field field : declaredFields) {
      if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
        Constant c;
        try {
          c = new Constant(field.getName(), field.getType(), field.get(this));
          constants.add(c);
        } catch (IllegalArgumentException | IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }

    return constants;
  }

  public File getFile() {
    return new File(getFileLocation().replaceFirst("^~", System.getProperty("user.home")));
  }
}
