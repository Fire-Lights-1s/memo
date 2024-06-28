package com.oreilly.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletOutputStream;

class CacheServletOutputStream extends ServletOutputStream {
   ServletOutputStream delegate;
   ByteArrayOutputStream cache;

   CacheServletOutputStream(ServletOutputStream out) {
      this.delegate = out;
      this.cache = new ByteArrayOutputStream(4096);
   }

   public ByteArrayOutputStream getBuffer() {
      return this.cache;
   }

   public void write(int b) throws IOException {
      this.delegate.write(b);
      this.cache.write(b);
   }

   public void write(byte[] b) throws IOException {
      this.delegate.write(b);
      this.cache.write(b);
   }

   public void write(byte[] buf, int offset, int len) throws IOException {
      this.delegate.write(buf, offset, len);
      this.cache.write(buf, offset, len);
   }
}
