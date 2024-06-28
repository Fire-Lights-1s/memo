package com.oreilly.servlet;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class RemoteDaemonHttpServlet extends DaemonHttpServlet implements Remote {
   protected Registry registry;

   public void init(ServletConfig config) throws ServletException {
      super.init(config);

      try {
         UnicastRemoteObject.exportObject(this);
         this.bind();
      } catch (RemoteException var3) {
         this.log("Problem binding to RMI registry: " + var3.getMessage());
      }

   }

   public void destroy() {
      super.destroy();
      this.unbind();
   }

   protected String getRegistryName() {
      String name = this.getInitParameter("registryName");
      return name != null ? name : this.getClass().getName();
   }

   protected int getRegistryPort() {
      try {
         return Integer.parseInt(this.getInitParameter("registryPort"));
      } catch (NumberFormatException var2) {
         return 1099;
      }
   }

   protected void bind() {
      try {
         this.registry = LocateRegistry.getRegistry(this.getRegistryPort());
         this.registry.list();
      } catch (Exception var4) {
         this.registry = null;
      }

      if (this.registry == null) {
         try {
            this.registry = LocateRegistry.createRegistry(this.getRegistryPort());
         } catch (Exception var3) {
            this.log("Could not get or create RMI registry on port " + this.getRegistryPort() + ": " + var3.getMessage());
            return;
         }
      }

      try {
         this.registry.rebind(this.getRegistryName(), this);
      } catch (Exception var2) {
         this.log("humbug Could not bind to RMI registry: " + var2.getMessage());
      }
   }

   protected void unbind() {
      try {
         if (this.registry != null) {
            this.registry.unbind(this.getRegistryName());
         }
      } catch (Exception var2) {
         this.log("Problem unbinding from RMI registry: " + var2.getMessage());
      }

   }
}
