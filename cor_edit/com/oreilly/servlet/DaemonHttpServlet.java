package com.oreilly.servlet;

import java.net.Socket;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class DaemonHttpServlet extends HttpServlet {
   protected int DEFAULT_PORT = 1313;
   private Thread daemonThread;

   public void init(ServletConfig config) throws ServletException {
      super.init(config);

      try {
         this.daemonThread = new Daemon(this);
         this.daemonThread.start();
      } catch (Exception var3) {
         this.log("Problem starting socket server daemon thread" + var3.getClass().getName() + ": " + var3.getMessage());
      }

   }

   protected int getSocketPort() {
      try {
         return Integer.parseInt(this.getInitParameter("socketPort"));
      } catch (NumberFormatException var2) {
         return this.DEFAULT_PORT;
      }
   }

   public abstract void handleClient(Socket var1);

   public void destroy() {
      try {
         this.daemonThread.stop();
         this.daemonThread = null;
      } catch (Exception var2) {
         this.log("Problem stopping server socket daemon thread: " + var2.getClass().getName() + ": " + var2.getMessage());
      }

   }
}
