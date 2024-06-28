package com.oreilly.servlet;

import java.io.UnsupportedEncodingException;
import java.util.Vector;
import javax.servlet.ServletRequest;

public class ParameterParser {
   private ServletRequest req;
   private String encoding;

   public ParameterParser(ServletRequest req) {
      this.req = req;
   }

   public void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {
      new String("".getBytes("8859_1"), encoding);
      this.encoding = encoding;
   }

   public String getStringParameter(String name) throws ParameterNotFoundException {
      String[] values = this.req.getParameterValues(name);
      if (values == null) {
         throw new ParameterNotFoundException(name + " not found");
      } else if (values[0].length() == 0) {
         throw new ParameterNotFoundException(name + " was empty");
      } else if (this.encoding == null) {
         return values[0];
      } else {
         try {
            return new String(values[0].getBytes("8859_1"), this.encoding);
         } catch (UnsupportedEncodingException var4) {
            return values[0];
         }
      }
   }

   public String getStringParameter(String name, String def) {
      try {
         return this.getStringParameter(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public boolean getBooleanParameter(String name) throws ParameterNotFoundException, NumberFormatException {
      String value = this.getStringParameter(name).toLowerCase();
      if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("on") && !value.equalsIgnoreCase("yes")) {
         if (!value.equalsIgnoreCase("false") && !value.equalsIgnoreCase("off") && !value.equalsIgnoreCase("no")) {
            throw new NumberFormatException("Parameter " + name + " value " + value + " is not a boolean");
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public boolean getBooleanParameter(String name, boolean def) {
      try {
         return this.getBooleanParameter(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public byte getByteParameter(String name) throws ParameterNotFoundException, NumberFormatException {
      return Byte.parseByte(this.getStringParameter(name));
   }

   public byte getByteParameter(String name, byte def) {
      try {
         return this.getByteParameter(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public char getCharParameter(String name) throws ParameterNotFoundException {
      String param = this.getStringParameter(name);
      if (param.length() == 0) {
         throw new ParameterNotFoundException(name + " is empty string");
      } else {
         return param.charAt(0);
      }
   }

   public char getCharParameter(String name, char def) {
      try {
         return this.getCharParameter(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public double getDoubleParameter(String name) throws ParameterNotFoundException, NumberFormatException {
      return new Double(this.getStringParameter(name));
   }

   public double getDoubleParameter(String name, double def) {
      try {
         return this.getDoubleParameter(name);
      } catch (Exception var5) {
         return def;
      }
   }

   public float getFloatParameter(String name) throws ParameterNotFoundException, NumberFormatException {
      return new Float(this.getStringParameter(name));
   }

   public float getFloatParameter(String name, float def) {
      try {
         return this.getFloatParameter(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public int getIntParameter(String name) throws ParameterNotFoundException, NumberFormatException {
      return Integer.parseInt(this.getStringParameter(name));
   }

   public int getIntParameter(String name, int def) {
      try {
         return this.getIntParameter(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public long getLongParameter(String name) throws ParameterNotFoundException, NumberFormatException {
      return Long.parseLong(this.getStringParameter(name));
   }

   public long getLongParameter(String name, long def) {
      try {
         return this.getLongParameter(name);
      } catch (Exception var5) {
         return def;
      }
   }

   public short getShortParameter(String name) throws ParameterNotFoundException, NumberFormatException {
      return Short.parseShort(this.getStringParameter(name));
   }

   public short getShortParameter(String name, short def) {
      try {
         return this.getShortParameter(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public String[] getMissingParameters(String[] required) {
      Vector missing = new Vector();

      for(int i = 0; i < required.length; ++i) {
         String val = this.getStringParameter(required[i], (String)null);
         if (val == null) {
            missing.addElement(required[i]);
         }
      }

      if (missing.size() == 0) {
         return null;
      } else {
         String[] ret = new String[missing.size()];
         missing.copyInto(ret);
         return ret;
      }
   }
}
