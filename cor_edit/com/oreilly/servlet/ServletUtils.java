package com.oreilly.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

public class ServletUtils {
   public static void returnFile(String filename, OutputStream out) throws FileNotFoundException, IOException {
      FileInputStream fis = null;

      try {
         fis = new FileInputStream(filename);
         byte[] buf = new byte[4096];

         int bytesRead;
         while((bytesRead = fis.read(buf)) != -1) {
            out.write(buf, 0, bytesRead);
         }
      } finally {
         if (fis != null) {
            fis.close();
         }

      }

   }

   public static void returnURL(URL url, OutputStream out) throws IOException {
      InputStream in = url.openStream();
      byte[] buf = new byte[4096];

      int bytesRead;
      while((bytesRead = in.read(buf)) != -1) {
         out.write(buf, 0, bytesRead);
      }

   }

   public static void returnURL(URL url, Writer out) throws IOException {
      URLConnection con = url.openConnection();
      con.connect();
      String encoding = con.getContentEncoding();
      BufferedReader in = null;
      if (encoding == null) {
         in = new BufferedReader(new InputStreamReader(url.openStream()));
      } else {
         in = new BufferedReader(new InputStreamReader(url.openStream(), encoding));
      }

      char[] buf = new char[4096];

      int charsRead;
      while((charsRead = in.read(buf)) != -1) {
         out.write(buf, 0, charsRead);
      }

   }

   public static String getStackTraceAsString(Throwable t) {
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      PrintWriter writer = new PrintWriter(bytes, true);
      t.printStackTrace(writer);
      return bytes.toString();
   }

   public static Servlet getServlet(String name, ServletRequest req, ServletContext context) {
      try {
         Servlet servlet = context.getServlet(name);
         if (servlet != null) {
            return servlet;
         } else {
            Socket socket = new Socket(req.getServerName(), req.getServerPort());
            socket.setSoTimeout(4000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET /servlet/" + name + " HTTP/1.0");
            out.println();

            try {
               socket.getInputStream().read();
            } catch (InterruptedIOException var7) {
            }

            out.close();
            return context.getServlet(name);
         }
      } catch (Exception var8) {
         return null;
      }
   }

   public static String[] split(String str, String delim) {
      Vector v = new Vector();
      StringTokenizer tokenizer = new StringTokenizer(str, delim);

      while(tokenizer.hasMoreTokens()) {
         v.addElement(tokenizer.nextToken());
      }

      String[] ret = new String[v.size()];

      for(int i = 0; i < ret.length; ++i) {
         ret[i] = (String)v.elementAt(i);
      }

      return ret;
   }

   public static URL getResource(ServletContext context, String resource) throws IOException {
      if (resource == null) {
         throw new FileNotFoundException("Requested resource was null (passed in null)");
      } else if (!resource.endsWith("/") && !resource.endsWith("\\") && !resource.endsWith(".")) {
         if (resource.indexOf("..") != -1) {
            throw new MalformedURLException("Path may not contain double dots");
         } else {
            String upperResource = resource.toUpperCase();
            if (!upperResource.startsWith("/WEB-INF") && !upperResource.startsWith("/META-INF")) {
               if (upperResource.endsWith(".JSP")) {
                  throw new MalformedURLException("Path may not end with .jsp");
               } else {
                  URL url = context.getResource(resource);
                  if (url == null) {
                     throw new FileNotFoundException("Requested resource was null (" + resource + ")");
                  } else {
                     return url;
                  }
               }
            } else {
               throw new MalformedURLException("Path may not begin with /WEB-INF or /META-INF");
            }
         }
      } else {
         throw new MalformedURLException("Path may not end with a slash or dot");
      }
   }
}
