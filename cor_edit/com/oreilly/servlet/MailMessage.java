package com.oreilly.servlet;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class MailMessage {
   String host;
   String from;
   Vector to;
   Vector cc;
   Hashtable headers;
   MailPrintStream out;
   BufferedReader in;
   Socket socket;

   public MailMessage() throws IOException {
      this("localhost");
   }

   public MailMessage(String host) throws IOException {
      this.host = host;
      this.to = new Vector();
      this.cc = new Vector();
      this.headers = new Hashtable();
      this.setHeader("X-Mailer", "com.oreilly.servlet.MailMessage (www.servlets.com)");
      this.connect();
      this.sendHelo();
   }

   public void from(String from) throws IOException {
      this.sendFrom(from);
      this.from = from;
   }

   public void to(String to) throws IOException {
      this.sendRcpt(to);
      this.to.addElement(to);
   }

   public void cc(String cc) throws IOException {
      this.sendRcpt(cc);
      this.cc.addElement(cc);
   }

   public void bcc(String bcc) throws IOException {
      this.sendRcpt(bcc);
   }

   public void setSubject(String subj) {
      this.headers.put("Subject", subj);
   }

   public void setHeader(String name, String value) {
      this.headers.put(name, value);
   }

   public PrintStream getPrintStream() throws IOException {
      this.setFromHeader();
      this.setToHeader();
      this.setCcHeader();
      this.sendData();
      this.flushHeaders();
      return this.out;
   }

   void setFromHeader() {
      this.setHeader("From", this.from);
   }

   void setToHeader() {
      this.setHeader("To", this.vectorToList(this.to));
   }

   void setCcHeader() {
      if (!this.cc.isEmpty()) {
         this.setHeader("Cc", this.vectorToList(this.cc));
      }

   }

   String vectorToList(Vector v) {
      StringBuffer buf = new StringBuffer();
      Enumeration e = v.elements();

      while(e.hasMoreElements()) {
         buf.append(e.nextElement());
         if (e.hasMoreElements()) {
            buf.append(", ");
         }
      }

      return buf.toString();
   }

   void flushHeaders() throws IOException {
      Enumeration e = this.headers.keys();

      while(e.hasMoreElements()) {
         String name = (String)e.nextElement();
         String value = (String)this.headers.get(name);
         this.out.println(name + ": " + value);
      }

      this.out.println();
      this.out.flush();
   }

   public void sendAndClose() throws IOException {
      this.sendDot();
      this.disconnect();
   }

   static String sanitizeAddress(String s) {
      int paramDepth = 0;
      int start = 0;
      int end = 0;
      int len = s.length();

      for(int i = 0; i < len; ++i) {
         char c = s.charAt(i);
         if (c == '(') {
            ++paramDepth;
            if (start == 0) {
               end = i;
            }
         } else if (c == ')') {
            --paramDepth;
            if (end == 0) {
               start = i + 1;
            }
         } else if (paramDepth == 0 && c == '<') {
            start = i + 1;
         } else if (paramDepth == 0 && c == '>') {
            end = i;
         }
      }

      if (end == 0) {
         end = len;
      }

      return s.substring(start, end);
   }

   void connect() throws IOException {
      this.socket = new Socket(this.host, 25);
      this.out = new MailPrintStream(new BufferedOutputStream(this.socket.getOutputStream()));
      this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "ISO-8859-1"));
      this.getReady();
   }

   void getReady() throws IOException {
      String response = this.in.readLine();
      int[] ok = new int[]{220};
      if (!this.isResponseOK(response, ok)) {
         throw new IOException("Didn't get introduction from server: " + response);
      }
   }

   void sendHelo() throws IOException {
      String local = InetAddress.getLocalHost().getHostName();
      int[] ok = new int[]{250};
      this.send("HELO " + local, ok);
   }

   void sendFrom(String from) throws IOException {
      int[] ok = new int[]{250};
      this.send("MAIL FROM: <" + sanitizeAddress(from) + ">", ok);
   }

   void sendRcpt(String rcpt) throws IOException {
      int[] ok = new int[]{250, 251};
      this.send("RCPT TO: <" + sanitizeAddress(rcpt) + ">", ok);
   }

   void sendData() throws IOException {
      int[] ok = new int[]{354};
      this.send("DATA", ok);
   }

   void sendDot() throws IOException {
      int[] ok = new int[]{250};
      this.send("\r\n.", ok);
   }

   void sendQuit() throws IOException {
      int[] ok = new int[]{221};
      this.send("QUIT", ok);
   }

   void send(String msg, int[] ok) throws IOException {
      this.out.rawPrint(msg + "\r\n");
      String response = this.in.readLine();
      if (!this.isResponseOK(response, ok)) {
         throw new IOException("Unexpected reply to command: " + msg + ": " + response);
      }
   }

   boolean isResponseOK(String response, int[] ok) {
      if (response == null) {
         return false;
      } else {
         for(int i = 0; i < ok.length; ++i) {
            if (response.startsWith("" + ok[i])) {
               return true;
            }
         }

         return false;
      }
   }

   void disconnect() throws IOException {
      if (this.out != null) {
         this.out.close();
      }

      if (this.in != null) {
         this.in.close();
      }

      if (this.socket != null) {
         this.socket.close();
      }

   }
}
