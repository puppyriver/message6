package com.alcatelsbell.nms.util;

import java.util.HashMap;

public class JVMRegistry
{
  protected static JVMRegistry registry = null;
  private HashMap registries = new HashMap();

  public static synchronized JVMRegistry getInstance()
  {
    if (registry == null)
      registry = new JVMRegistry();

    return registry;
  }

  public Object getRegistedObject(Object key)
  {
    if (key == null)
      return null;

    return this.registries.get(key);
  }

  public void registerObject(Object key, Object registedObejct) {
    if (key == null)
      return;

    this.registries.put(key, registedObejct);
  }

  public void clearRegistedObject(Object key) {
    if (key != null)
      this.registries.remove(key);
  }
}