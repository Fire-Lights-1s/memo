package com.oreilly.servlet;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

class MailPrintStream extends PrintStream {
   int lastChar;

   public MailPrintStream(OutputStream out) throws UnsupportedEncodingException {
      super(out, true, "ISO-8859-1");
   }

   public void write(int b) {
      if (b == 10 && this.lastChar != 13) {
         this.rawWrite(13);
         this.rawWrite(b);
      } else if (b == 46 && this.lastChar == 10) {
         this.rawWrite(46);
         this.rawWrite(b);
      } else if (b != 10 && this.lastChar == 13) {
         this.rawWrite(10);
         this.rawWrite(b);
         if (b == 46) {
            this.rawWrite(46);
         }
      } else {
         this.rawWrite(b);
      }

      this.lastChar = b;
   }

   public void write(byte[] buf, int off, int len) {
      for(int i = 0; i < len; ++i) {
         this.write(buf[off + i]);
      }

   }

   void rawWrite(int b) {
      super.write(b);
   }

   void rawPrint(String s) {
      int len = s.length();

      for(int i = 0; i < len; ++i) {
         this.rawWrite(s.charAt(i));
      }

   }
}
