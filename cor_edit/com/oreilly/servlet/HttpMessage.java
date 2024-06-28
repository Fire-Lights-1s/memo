package com.oreilly.servlet;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public class HttpMessage {
   URL servlet = null;
   Hashtable headers = null;

   public HttpMessage(URL servlet) {
      this.servlet = servlet;
   }

   public InputStream sendGetMessage() throws IOException {
      return this.sendGetMessage((Properties)null);
   }

   public InputStream sendGetMessage(Properties args) throws IOException {
      String argString = "";
      if (args != null) {
         argString = "?" + this.toEncodedString(args);
      }

      URL url = new URL(this.servlet.toExternalForm() + argString);
      URLConnection con = url.openConnection();
      con.setUseCaches(false);
      this.sendHeaders(con);
      return con.getInputStream();
   }

   public InputStream sendPostMessage() throws IOException {
      return this.sendPostMessage((Properties)null);
   }

   public InputStream sendPostMessage(Properties args) throws IOException {
      String argString = "";
      if (args != null) {
         argString = this.toEncodedString(args);
      }

      URLConnection con = this.servlet.openConnection();
      con.setDoInput(true);
      con.setDoOutput(true);
      con.setUseCaches(false);
      con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      this.sendHeaders(con);
      DataOutputStream out = new DataOutputStream(con.getOutputStream());
      out.writeBytes(argString);
      out.flush();
      out.close();
      return con.getInputStream();
   }

   public InputStream sendPostMessage(Serializable obj) throws IOException {
      URLConnection con = this.servlet.openConnection();
      con.setDoInput(true);
      con.setDoOutput(true);
      con.setUseCaches(false);
      con.setRequestProperty("Content-Type", "application/x-java-serialized-object");
      this.sendHeaders(con);
      ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
      out.writeObject(obj);
      out.flush();
      out.close();
      return con.getInputStream();
   }

   public void setHeader(String name, String value) {
      if (this.headers == null) {
         this.headers = new Hashtable();
      }

      this.headers.put(name, value);
   }

   private void sendHeaders(URLConnection con) {
      if (this.headers != null) {
         Enumeration enumm = this.headers.keys();

         while(enumm.hasMoreElements()) {
            String name = (String)enumm.nextElement();
            String value = (String)this.headers.get(name);
            con.setRequestProperty(name, value);
         }
      }

   }

   public void setCookie(String name, String value) {
      if (this.headers == null) {
         this.headers = new Hashtable();
      }

      String existingCookies = (String)this.headers.get("Cookie");
      if (existingCookies == null) {
         this.setHeader("Cookie", name + "=" + value);
      } else {
         this.setHeader("Cookie", existingCookies + "; " + name + "=" + value);
      }

   }

   public void setAuthorization(String name, String password) {
      String authorization = Base64Encoder.encode(name + ":" + password);
      this.setHeader("Authorization", "Basic " + authorization);
   }

   private String toEncodedString(Properties args) {
      StringBuffer buf = new StringBuffer();
      Enumeration names = args.propertyNames();

      while(names.hasMoreElements()) {
         String name = (String)names.nextElement();
         String value = args.getProperty(name);
         buf.append(URLEncoder.encode(name) + "=" + URLEncoder.encode(value));
         if (names.hasMoreElements()) {
            buf.append("&");
         }
      }

      return buf.toString();
   }
}
