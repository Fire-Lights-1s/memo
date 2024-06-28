package com.oreilly.servlet;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class LocaleNegotiator {
   private ResourceBundle chosenBundle;
   private Locale chosenLocale;
   private String chosenCharset;

   public LocaleNegotiator(String bundleName, String languages, String charsets) {
      Locale defaultLocale = new Locale("en", "US");
      String defaultCharset = "ISO-8859-1";
      ResourceBundle defaultBundle = null;

      try {
         defaultBundle = ResourceBundle.getBundle(bundleName, defaultLocale);
      } catch (MissingResourceException var12) {
      }

      if (languages == null) {
         this.chosenLocale = defaultLocale;
         this.chosenCharset = defaultCharset;
         this.chosenBundle = defaultBundle;
      } else {
         StringTokenizer tokenizer = new StringTokenizer(languages, ",");

         while(tokenizer.hasMoreTokens()) {
            String lang = tokenizer.nextToken();
            Locale loc = this.getLocaleForLanguage(lang);
            ResourceBundle bundle = this.getBundleNoFallback(bundleName, loc);
            if (bundle != null) {
               String charset = this.getCharsetForLocale(loc, charsets);
               if (charset != null) {
                  this.chosenLocale = loc;
                  this.chosenBundle = bundle;
                  this.chosenCharset = charset;
                  return;
               }
            }
         }

         this.chosenLocale = defaultLocale;
         this.chosenCharset = defaultCharset;
         this.chosenBundle = defaultBundle;
      }
   }

   public ResourceBundle getBundle() {
      return this.chosenBundle;
   }

   public Locale getLocale() {
      return this.chosenLocale;
   }

   public String getCharset() {
      return this.chosenCharset;
   }

   private Locale getLocaleForLanguage(String lang) {
      int semi;
      if ((semi = lang.indexOf(59)) != -1) {
         lang = lang.substring(0, semi);
      }

      lang = lang.trim();
      Locale loc;
      int dash;
      if ((dash = lang.indexOf(45)) == -1) {
         loc = new Locale(lang, "");
      } else {
         loc = new Locale(lang.substring(0, dash), lang.substring(dash + 1));
      }

      return loc;
   }

   private ResourceBundle getBundleNoFallback(String bundleName, Locale loc) {
      ResourceBundle fallback = null;

      try {
         fallback = ResourceBundle.getBundle(bundleName, new Locale("bogus", ""));
      } catch (MissingResourceException var6) {
      }

      try {
         ResourceBundle bundle = ResourceBundle.getBundle(bundleName, loc);
         if (bundle != fallback) {
            return bundle;
         }

         if (bundle == fallback && loc.getLanguage().equals(Locale.getDefault().getLanguage())) {
            return bundle;
         }
      } catch (MissingResourceException var5) {
      }

      return null;
   }

   protected String getCharsetForLocale(Locale loc, String charsets) {
      return LocaleToCharsetMap.getCharset(loc);
   }
}
