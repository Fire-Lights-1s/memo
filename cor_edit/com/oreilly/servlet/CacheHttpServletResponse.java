package com.oreilly.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

class CacheHttpServletResponse implements HttpServletResponse {
   private int status;
   private Hashtable headers;
   private int contentLength;
   private String contentType;
   private String encoding;
   private Locale locale;
   private Vector cookies;
   private boolean didError;
   private boolean didRedirect;
   private boolean gotStream;
   private boolean gotWriter;
   private HttpServletResponse delegate;
   private CacheServletOutputStream out;
   private PrintWriter writer;

   CacheHttpServletResponse(HttpServletResponse res) {
      this.delegate = res;

      try {
         this.out = new CacheServletOutputStream(res.getOutputStream());
      } catch (IOException var3) {
         System.out.println("Got IOException constructing cached response: " + var3.getMessage());
      }

      this.internalReset();
   }

   private void internalReset() {
      this.status = 200;
      this.headers = new Hashtable();
      this.contentLength = -1;
      this.contentType = null;
      this.encoding = null;
      this.locale = null;
      this.cookies = new Vector();
      this.didError = false;
      this.didRedirect = false;
      this.gotStream = false;
      this.gotWriter = false;
      this.out.getBuffer().reset();
   }

   public boolean isValid() {
      return !this.didError && !this.didRedirect;
   }

   private void internalSetHeader(String name, Object value) {
      Vector v = new Vector();
      v.addElement(value);
      this.headers.put(name, v);
   }

   private void internalAddHeader(String name, Object value) {
      Vector v = (Vector)this.headers.get(name);
      if (v == null) {
         v = new Vector();
      }

      v.addElement(value);
      this.headers.put(name, v);
   }

   public void writeTo(HttpServletResponse res) {
      res.setStatus(this.status);
      if (this.contentType != null) {
         res.setContentType(this.contentType);
      }

      if (this.encoding != null) {
         res.setCharacterEncoding(this.encoding);
      }

      if (this.locale != null) {
         res.setLocale(this.locale);
      }

      Enumeration enu = this.cookies.elements();

      while(enu.hasMoreElements()) {
         Cookie c = (Cookie)enu.nextElement();
         res.addCookie(c);
      }

      enu = this.headers.keys();

      while(enu.hasMoreElements()) {
         String name = (String)enu.nextElement();
         Vector values = (Vector)this.headers.get(name);
         Enumeration enu2 = values.elements();

         while(enu2.hasMoreElements()) {
            Object value = enu2.nextElement();
            if (value instanceof String) {
               res.setHeader(name, (String)value);
            }

            if (value instanceof Integer) {
               res.setIntHeader(name, (Integer)value);
            }

            if (value instanceof Long) {
               res.setDateHeader(name, (Long)value);
            }
         }
      }

      res.setContentLength(this.out.getBuffer().size());

      try {
         this.out.getBuffer().writeTo(res.getOutputStream());
      } catch (IOException var7) {
         System.out.println("Got IOException writing cached response: " + var7.getMessage());
      }

   }

   public ServletOutputStream getOutputStream() throws IOException {
      if (this.gotWriter) {
         throw new IllegalStateException("Cannot get output stream after getting writer");
      } else {
         this.gotStream = true;
         return this.out;
      }
   }

   public PrintWriter getWriter() throws UnsupportedEncodingException {
      if (this.gotStream) {
         throw new IllegalStateException("Cannot get writer after getting output stream");
      } else {
         this.gotWriter = true;
         if (this.writer == null) {
            OutputStreamWriter w = new OutputStreamWriter(this.out, this.getCharacterEncoding());
            this.writer = new PrintWriter(w, true);
         }

         return this.writer;
      }
   }

   public void setContentLength(int len) {
      this.delegate.setContentLength(len);
   }

   public void setContentType(String type) {
      this.delegate.setContentType(type);
      this.contentType = type;
   }

   public void setCharacterEncoding(String encoding) {
      this.delegate.setCharacterEncoding(encoding);
   }

   public String getCharacterEncoding() {
      return this.delegate.getCharacterEncoding();
   }

   public void setBufferSize(int size) throws IllegalStateException {
      this.delegate.setBufferSize(size);
   }

   public int getBufferSize() {
      return this.delegate.getBufferSize();
   }

   public void reset() throws IllegalStateException {
      this.delegate.reset();
      this.internalReset();
   }

   public void resetBuffer() throws IllegalStateException {
      this.delegate.resetBuffer();
      this.contentLength = -1;
      this.out.getBuffer().reset();
   }

   public boolean isCommitted() {
      return this.delegate.isCommitted();
   }

   public void flushBuffer() throws IOException {
      this.delegate.flushBuffer();
   }

   public void setLocale(Locale loc) {
      this.delegate.setLocale(loc);
      this.locale = loc;
   }

   public Locale getLocale() {
      return this.delegate.getLocale();
   }

   public void addCookie(Cookie cookie) {
      this.delegate.addCookie(cookie);
      this.cookies.addElement(cookie);
   }

   public boolean containsHeader(String name) {
      return this.delegate.containsHeader(name);
   }

   public String getContentType() {
      return this.delegate.getContentType();
   }

   /** @deprecated */
   public void setStatus(int sc, String sm) {
      this.delegate.setStatus(sc, sm);
      this.status = sc;
   }

   public void setStatus(int sc) {
      this.delegate.setStatus(sc);
      this.status = sc;
   }

   public void setHeader(String name, String value) {
      this.delegate.setHeader(name, value);
      this.internalSetHeader(name, value);
   }

   public void setIntHeader(String name, int value) {
      this.delegate.setIntHeader(name, value);
      this.internalSetHeader(name, new Integer(value));
   }

   public void setDateHeader(String name, long date) {
      this.delegate.setDateHeader(name, date);
      this.internalSetHeader(name, new Long(date));
   }

   public void sendError(int sc, String msg) throws IOException {
      this.delegate.sendError(sc, msg);
      this.didError = true;
   }

   public void sendError(int sc) throws IOException {
      this.delegate.sendError(sc);
      this.didError = true;
   }

   public void sendRedirect(String location) throws IOException {
      this.delegate.sendRedirect(location);
      this.didRedirect = true;
   }

   public String encodeURL(String url) {
      return this.delegate.encodeURL(url);
   }

   public String encodeRedirectURL(String url) {
      return this.delegate.encodeRedirectURL(url);
   }

   public void addHeader(String name, String value) {
      this.internalAddHeader(name, value);
   }

   public void addIntHeader(String name, int value) {
      this.internalAddHeader(name, new Integer(value));
   }

   public void addDateHeader(String name, long value) {
      this.internalAddHeader(name, new Long(value));
   }

   /** @deprecated */
   public String encodeUrl(String url) {
      return this.encodeURL(url);
   }

   /** @deprecated */
   public String encodeRedirectUrl(String url) {
      return this.encodeRedirectURL(url);
   }
}
