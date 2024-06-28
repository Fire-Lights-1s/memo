package com.oreilly.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CacheHttpServlet extends HttpServlet {
   CacheHttpServletResponse cacheResponse;
   long cacheLastMod = -1L;
   String cacheQueryString = null;
   String cachePathInfo = null;
   String cacheServletPath = null;
   Object lock = new Object();

   protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      String method = req.getMethod();
      if (!method.equals("GET")) {
         super.service(req, res);
      } else {
         long servletLastMod = this.getLastModified(req);
         if (servletLastMod == -1L) {
            super.service(req, res);
         } else if (servletLastMod / 1000L * 1000L <= req.getDateHeader("If-Modified-Since")) {
            res.setStatus(304);
         } else {
            CacheHttpServletResponse localResponseCopy = null;
            synchronized(this.lock) {
               if (servletLastMod <= this.cacheLastMod && this.cacheResponse.isValid() && this.equal(this.cacheQueryString, req.getQueryString()) && this.equal(this.cachePathInfo, req.getPathInfo()) && this.equal(this.cacheServletPath, req.getServletPath())) {
                  localResponseCopy = this.cacheResponse;
               }
            }

            if (localResponseCopy != null) {
               localResponseCopy.writeTo(res);
            } else {
               localResponseCopy = new CacheHttpServletResponse(res);
               super.service(req, localResponseCopy);
               synchronized(this.lock) {
                  this.cacheResponse = localResponseCopy;
                  this.cacheLastMod = servletLastMod;
                  this.cacheQueryString = req.getQueryString();
                  this.cachePathInfo = req.getPathInfo();
                  this.cacheServletPath = req.getServletPath();
               }
            }
         }
      }
   }

   private boolean equal(String s1, String s2) {
      if (s1 == null && s2 == null) {
         return true;
      } else {
         return s1 != null && s2 != null ? s1.equals(s2) : false;
      }
   }
}
