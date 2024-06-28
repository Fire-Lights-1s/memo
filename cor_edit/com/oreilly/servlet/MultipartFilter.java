package com.oreilly.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MultipartFilter implements Filter {
   private FilterConfig config = null;
   private String dir = null;

   public void init(FilterConfig config) throws ServletException {
      this.config = config;
      this.dir = config.getInitParameter("uploadDir");
      if (this.dir == null) {
         File tempdir = (File)config.getServletContext().getAttribute("javax.servlet.context.tempdir");
         if (tempdir == null) {
            throw new ServletException("MultipartFilter: No upload directory found: set an uploadDir init parameter or ensure the javax.servlet.context.tempdir directory is valid");
         }

         this.dir = tempdir.toString();
      }

   }

   public void destroy() {
      this.config = null;
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest)request;
      String type = req.getHeader("Content-Type");
      if (type != null && type.startsWith("multipart/form-data")) {
         MultipartWrapper multi = new MultipartWrapper(req, this.dir);
         chain.doFilter(multi, response);
      } else {
         chain.doFilter(request, response);
      }

   }
}
