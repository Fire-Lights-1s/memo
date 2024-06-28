package com.oreilly.servlet;

import java.io.File;

class UploadedFile {
   private String dir;
   private String filename;
   private String original;
   private String type;

   UploadedFile(String dir, String filename, String original, String type) {
      this.dir = dir;
      this.filename = filename;
      this.original = original;
      this.type = type;
   }

   public String getContentType() {
      return this.type;
   }

   public String getFilesystemName() {
      return this.filename;
   }

   public String getOriginalFileName() {
      return this.original;
   }

   public File getFile() {
      return this.dir != null && this.filename != null ? new File(this.dir + File.separator + this.filename) : null;
   }
}
