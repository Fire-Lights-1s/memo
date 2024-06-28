package com.oreilly.servlet;

import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;

public class MultipartRequest {
   private static final int DEFAULT_MAX_POST_SIZE = 1048576;
   protected Hashtable parameters;
   protected Hashtable files;

   public MultipartRequest(HttpServletRequest request, String saveDirectory) throws IOException {
      this(request, saveDirectory, 1048576);
   }

   public MultipartRequest(HttpServletRequest request, String saveDirectory, int maxPostSize) throws IOException {
      this(request, saveDirectory, maxPostSize, (String)null, (FileRenamePolicy)null);
   }

   public MultipartRequest(HttpServletRequest request, String saveDirectory, String encoding) throws IOException {
      this(request, saveDirectory, 1048576, encoding, (FileRenamePolicy)null);
   }

   public MultipartRequest(HttpServletRequest request, String saveDirectory, int maxPostSize, FileRenamePolicy policy) throws IOException {
      this(request, saveDirectory, maxPostSize, (String)null, policy);
   }

   public MultipartRequest(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding) throws IOException {
      this(request, saveDirectory, maxPostSize, encoding, (FileRenamePolicy)null);
   }

   public MultipartRequest(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy) throws IOException {
      this.parameters = new Hashtable();
      this.files = new Hashtable();
      if (request == null) {
         throw new IllegalArgumentException("request cannot be null");
      } else if (saveDirectory == null) {
         throw new IllegalArgumentException("saveDirectory cannot be null");
      } else if (maxPostSize <= 0) {
         throw new IllegalArgumentException("maxPostSize must be positive");
      } else {
         File dir = new File(saveDirectory);
         if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Not a directory: " + saveDirectory);
         } else if (!dir.canWrite()) {
            throw new IllegalArgumentException("Not writable: " + saveDirectory);
         } else {
            MultipartParser parser = new MultipartParser(request, maxPostSize, true, true, encoding);
            Vector existingValues;
            if (request.getQueryString() != null) {
               Hashtable queryParameters = HttpUtils.parseQueryString(request.getQueryString());
               Enumeration queryParameterNames = queryParameters.keys();

               while(queryParameterNames.hasMoreElements()) {
                  Object paramName = queryParameterNames.nextElement();
                  String[] values = (String[])((String[])queryParameters.get(paramName));
                  existingValues = new Vector();

                  for(int i = 0; i < values.length; ++i) {
                     existingValues.add(values[i]);
                  }

                  this.parameters.put(paramName, existingValues);
               }
            }

            Part part;
            while((part = parser.readNextPart()) != null) {
               String name = part.getName();
               if (name == null) {
                  throw new IOException("Malformed input: parameter name missing (known Opera 7 bug)");
               }

               String fileName;
               if (part.isParam()) {
                  ParamPart paramPart = (ParamPart)part;
                  fileName = paramPart.getStringValue();
                  existingValues = (Vector)this.parameters.get(name);
                  if (existingValues == null) {
                     existingValues = new Vector();
                     this.parameters.put(name, existingValues);
                  }

                  existingValues.addElement(fileName);
               } else if (part.isFile()) {
                  FilePart filePart = (FilePart)part;
                  fileName = filePart.getFileName();
                  if (fileName != null) {
                     filePart.setRenamePolicy(policy);
                     filePart.writeTo(dir);
                     this.files.put(name, new UploadedFile(dir.toString(), filePart.getFileName(), fileName, filePart.getContentType()));
                  } else {
                     this.files.put(name, new UploadedFile((String)null, (String)null, (String)null, (String)null));
                  }
               }
            }

         }
      }
   }

   public MultipartRequest(ServletRequest request, String saveDirectory) throws IOException {
      this((HttpServletRequest)request, saveDirectory);
   }

   public MultipartRequest(ServletRequest request, String saveDirectory, int maxPostSize) throws IOException {
      this((HttpServletRequest)request, saveDirectory, maxPostSize);
   }

   public Enumeration getParameterNames() {
      return this.parameters.keys();
   }

   public Enumeration getFileNames() {
      return this.files.keys();
   }

   public String getParameter(String name) {
      try {
         Vector values = (Vector)this.parameters.get(name);
         if (values != null && values.size() != 0) {
            String value = (String)values.elementAt(values.size() - 1);
            return value;
         } else {
            return null;
         }
      } catch (Exception var4) {
         return null;
      }
   }

   public String[] getParameterValues(String name) {
      try {
         Vector values = (Vector)this.parameters.get(name);
         if (values != null && values.size() != 0) {
            String[] valuesArray = new String[values.size()];
            values.copyInto(valuesArray);
            return valuesArray;
         } else {
            return null;
         }
      } catch (Exception var4) {
         return null;
      }
   }

   public String getFilesystemName(String name) {
      try {
         UploadedFile file = (UploadedFile)this.files.get(name);
         return file.getFilesystemName();
      } catch (Exception var3) {
         return null;
      }
   }

   public String getOriginalFileName(String name) {
      try {
         UploadedFile file = (UploadedFile)this.files.get(name);
         return file.getOriginalFileName();
      } catch (Exception var3) {
         return null;
      }
   }

   public String getContentType(String name) {
      try {
         UploadedFile file = (UploadedFile)this.files.get(name);
         return file.getContentType();
      } catch (Exception var3) {
         return null;
      }
   }

   public File getFile(String name) {
      try {
         UploadedFile file = (UploadedFile)this.files.get(name);
         return file.getFile();
      } catch (Exception var3) {
         return null;
      }
   }
}
