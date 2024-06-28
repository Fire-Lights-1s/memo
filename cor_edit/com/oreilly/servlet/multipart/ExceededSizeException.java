package com.oreilly.servlet.multipart;

import java.io.IOException;

public class ExceededSizeException extends IOException {
   public ExceededSizeException() {
   }

   public ExceededSizeException(String s) {
      super(s);
   }
}
