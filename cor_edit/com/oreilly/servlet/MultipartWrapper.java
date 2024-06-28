package com.oreilly.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MultipartWrapper extends HttpServletRequestWrapper {
   MultipartRequest mreq = null;

   public MultipartWrapper(HttpServletRequest req, String dir) throws IOException {
      super(req);
      this.mreq = new MultipartRequest(req, dir);
   }

   public Enumeration getParameterNames() {
      return this.mreq.getParameterNames();
   }

   public String getParameter(String name) {
      return this.mreq.getParameter(name);
   }

   public String[] getParameterValues(String name) {
      return this.mreq.getParameterValues(name);
   }

   public Map getParameterMap() {
      Map map = new HashMap();
      Enumeration enumm = this.getParameterNames();

      while(enumm.hasMoreElements()) {
         String name = (String)enumm.nextElement();
         map.put(name, this.mreq.getParameterValues(name));
      }

      return map;
   }

   public Enumeration getFileNames() {
      return this.mreq.getFileNames();
   }

   public String getFilesystemName(String name) {
      return this.mreq.getFilesystemName(name);
   }

   public String getOriginalFileName(String name) {
      return this.mreq.getOriginalFileName(name);
   }

   public String getContentType(String name) {
      return this.mreq.getContentType(name);
   }

   public File getFile(String name) {
      return this.mreq.getFile(name);
   }
}
