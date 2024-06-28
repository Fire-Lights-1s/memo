package com.oreilly.servlet;

import java.io.IOException;
import java.net.ServerSocket;

class Daemon extends Thread {
   private ServerSocket serverSocket;
   private DaemonHttpServlet servlet;

   public Daemon(DaemonHttpServlet servlet) {
      this.servlet = servlet;
   }

   public void run() {
      try {
         this.serverSocket = new ServerSocket(this.servlet.getSocketPort());
      } catch (Exception var4) {
         this.servlet.log("Problem establishing server socket: " + var4.getClass().getName() + ": " + var4.getMessage());
         return;
      }

      try {
         while(true) {
            while(true) {
               try {
                  this.servlet.handleClient(this.serverSocket.accept());
               } catch (IOException var5) {
                  this.servlet.log("Problem accepting client's socket connection: " + var5.getClass().getName() + ": " + var5.getMessage());
               }
            }
         }
      } catch (ThreadDeath var6) {
         try {
            this.serverSocket.close();
         } catch (IOException var3) {
            this.servlet.log("Problem closing server socket: " + var3.getClass().getName() + ": " + var3.getMessage());
         }

      }
   }
}
