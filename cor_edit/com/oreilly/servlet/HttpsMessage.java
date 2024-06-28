package com.oreilly.servlet;

import java.net.URL;
import java.net.URLStreamHandlerFactory;
import java.security.Provider;
import java.security.Security;

public class HttpsMessage extends HttpMessage {
   static boolean m_bStreamHandlerSet = false;

   public HttpsMessage(String szURL) throws Exception {
      super((URL)null);
      if (!m_bStreamHandlerSet) {
         String szVendor = System.getProperty("java.vendor");
         String szVersion = System.getProperty("java.version");
         Double dVersion = new Double(szVersion.substring(0, 3));
         Class clsFactory;
         if (-1 < szVendor.indexOf("Microsoft")) {
            try {
               clsFactory = Class.forName("com.ms.net.wininet.WininetStreamHandlerFactory");
               if (null != clsFactory) {
                  URL.setURLStreamHandlerFactory((URLStreamHandlerFactory)clsFactory.newInstance());
               }
            } catch (ClassNotFoundException var7) {
               throw new Exception("Unable to load the Microsoft SSL stream handler.  Check classpath." + var7.toString());
            } catch (Error var8) {
               m_bStreamHandlerSet = true;
            }
         } else if (1.2D <= dVersion) {
            System.getProperties().put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");

            try {
               clsFactory = Class.forName("com.sun.net.ssl.internal.ssl.Provider");
               if (null != clsFactory && null == Security.getProvider("SunJSSE")) {
                  Security.addProvider((Provider)clsFactory.newInstance());
               }
            } catch (ClassNotFoundException var6) {
               throw new Exception("Unable to load the JSSE SSL stream handler.  Check classpath." + var6.toString());
            }
         }

         m_bStreamHandlerSet = true;
      }

      super.servlet = new URL(szURL);
   }
}
