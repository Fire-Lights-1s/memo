package com.oreilly.servlet;

public class CookieNotFoundException extends Exception {
   public CookieNotFoundException() {
   }

   public CookieNotFoundException(String s) {
      super(s);
   }
}
