package com.oreilly.servlet;

import java.util.Hashtable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieParser {
   private HttpServletRequest req;
   private Hashtable cookieJar = new Hashtable();

   public CookieParser(HttpServletRequest req) {
      this.req = req;
      this.parseCookies();
   }

   void parseCookies() {
      Cookie[] cookies = this.req.getCookies();
      if (cookies != null) {
         for(int i = 0; i < cookies.length; ++i) {
            String name = cookies[i].getName();
            String value = cookies[i].getValue();
            this.cookieJar.put(name, value);
         }
      }

   }

   public String getStringCookie(String name) throws CookieNotFoundException {
      String value = (String)this.cookieJar.get(name);
      if (value == null) {
         throw new CookieNotFoundException(name + " not found");
      } else {
         return value;
      }
   }

   public String getStringCookie(String name, String def) {
      try {
         return this.getStringCookie(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public boolean getBooleanCookie(String name) throws CookieNotFoundException {
      return new Boolean(this.getStringCookie(name));
   }

   public boolean getBooleanCookie(String name, boolean def) {
      try {
         return this.getBooleanCookie(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public byte getByteCookie(String name) throws CookieNotFoundException, NumberFormatException {
      return Byte.parseByte(this.getStringCookie(name));
   }

   public byte getByteCookie(String name, byte def) {
      try {
         return this.getByteCookie(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public char getCharCookie(String name) throws CookieNotFoundException {
      String param = this.getStringCookie(name);
      if (param.length() == 0) {
         throw new CookieNotFoundException(name + " is empty string");
      } else {
         return param.charAt(0);
      }
   }

   public char getCharCookie(String name, char def) {
      try {
         return this.getCharCookie(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public double getDoubleCookie(String name) throws CookieNotFoundException, NumberFormatException {
      return new Double(this.getStringCookie(name));
   }

   public double getDoubleCookie(String name, double def) {
      try {
         return this.getDoubleCookie(name);
      } catch (Exception var5) {
         return def;
      }
   }

   public float getFloatCookie(String name) throws CookieNotFoundException, NumberFormatException {
      return new Float(this.getStringCookie(name));
   }

   public float getFloatCookie(String name, float def) {
      try {
         return this.getFloatCookie(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public int getIntCookie(String name) throws CookieNotFoundException, NumberFormatException {
      return Integer.parseInt(this.getStringCookie(name));
   }

   public int getIntCookie(String name, int def) {
      try {
         return this.getIntCookie(name);
      } catch (Exception var4) {
         return def;
      }
   }

   public long getLongCookie(String name) throws CookieNotFoundException, NumberFormatException {
      return Long.parseLong(this.getStringCookie(name));
   }

   public long getLongCookie(String name, long def) {
      try {
         return this.getLongCookie(name);
      } catch (Exception var5) {
         return def;
      }
   }

   public short getShortCookie(String name) throws CookieNotFoundException, NumberFormatException {
      return Short.parseShort(this.getStringCookie(name));
   }

   public short getShortCookie(String name, short def) {
      try {
         return this.getShortCookie(name);
      } catch (Exception var4) {
         return def;
      }
   }
}
