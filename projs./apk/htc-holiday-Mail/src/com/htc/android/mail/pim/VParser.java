package com.htc.android.mail.pim;

import com.htc.android.mail.pim.VBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public abstract class VParser {

   protected final int PARSE_ERROR = -1;
   protected String mBuffer = null;
   protected VBuilder mBuilder = null;
   protected final String mDefaultEncoding = "8BIT";
   protected String mEncoding = null;


   public VParser() {}

   protected String getWord(int var1) {
      StringBuffer var2 = new StringBuffer();

      while(true) {
         try {
            char var3 = this.mBuffer.charAt(var1);
            if(!this.isLetterOrDigit(var3) && var3 != 45) {
               break;
            }

            var2.append(var3);
         } catch (IndexOutOfBoundsException var6) {
            break;
         }

         ++var1;
      }

      return var2.toString();
   }

   protected boolean isLetter(char var1) {
      boolean var2;
      if((var1 < 97 || var1 > 122) && (var1 < 65 || var1 > 90)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   protected boolean isLetterOrDigit(char var1) {
      boolean var2;
      if(var1 >= 48 && var1 <= 57) {
         var2 = true;
      } else if(var1 >= 97 && var1 <= 122) {
         var2 = true;
      } else if(var1 >= 65 && var1 <= 90) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   protected boolean isPrintable(char var1) {
      boolean var2;
      if(var1 >= 32 && var1 <= 126) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public boolean parse(InputStream var1, String var2, VBuilder var3) throws IOException {
      this.setInputStream(var1, var2);
      this.mBuilder = var3;
      int var4 = 0;
      int var5 = 0;
      if(this.mBuilder != null) {
         this.mBuilder.start();
      }

      while(true) {
         int var6 = this.parseVFile(var4);
         if(-1 == var6) {
            if(this.mBuilder != null) {
               this.mBuilder.end();
            }

            boolean var7;
            if(this.mBuffer.length() == var5) {
               var7 = true;
            } else {
               var7 = false;
            }

            return var7;
         }

         var4 += var6;
         var5 += var6;
      }
   }

   public boolean parse(String var1, VBuilder var2) throws IOException {
      this.setInputStream(var1);
      this.mBuilder = var2;
      int var3 = 0;
      int var4 = 0;
      if(this.mBuilder != null) {
         this.mBuilder.start();
      }

      while(true) {
         int var5 = this.parseVFile(var3);
         if(-1 == var5) {
            if(this.mBuilder != null) {
               this.mBuilder.end();
            }

            boolean var6;
            if(this.mBuffer.length() == var4) {
               var6 = true;
            } else {
               var6 = false;
            }

            return var6;
         }

         var3 += var5;
         var4 += var5;
      }
   }

   protected int parse8bit(int var1) {
      int var2 = this.mBuffer.substring(var1).indexOf("\r\n");
      int var3;
      if(var2 == -1) {
         var3 = -1;
      } else {
         var3 = var2;
      }

      return var3;
   }

   protected int parseBase64(int var1) {
      int var2 = 0;
      int var3;
      if(this.parseString(var1, "\r\n", (boolean)0) != -1) {
         var3 = 0;
         return var3;
      } else {
         while(true) {
            char var4;
            while(true) {
               int var5;
               try {
                  var4 = this.mBuffer.charAt(var1);
                  if(var4 != 13) {
                     break;
                  }

                  var5 = this.parseString(var1, "\r\n\r\n", (boolean)0);
               } catch (IndexOutOfBoundsException var8) {
                  var3 = -1;
                  return var3;
               }

               if(var5 != -1) {
                  var3 = var2 + var5 + -2;
                  return var3;
               }

               ++var1;
               ++var2;
            }

            if(var4 == 10) {
               ++var1;
               ++var2;
            } else {
               ++var2;
               ++var1;
            }
         }
      }
   }

   protected int parseCharsetVal(int var1) {
      int var2 = this.parseString(var1, "us-ascii", (boolean)1);
      int var3;
      if(var2 != -1) {
         var3 = 0 + var2;
      } else {
         var2 = this.parseString(var1, "iso-8859-1", (boolean)1);
         if(var2 != -1) {
            var3 = 0 + var2;
         } else {
            var2 = this.parseString(var1, "iso-8859-2", (boolean)1);
            if(var2 != -1) {
               var3 = 0 + var2;
            } else {
               var2 = this.parseString(var1, "iso-8859-3", (boolean)1);
               if(var2 != -1) {
                  var3 = 0 + var2;
               } else {
                  var2 = this.parseString(var1, "iso-8859-4", (boolean)1);
                  if(var2 != -1) {
                     var3 = 0 + var2;
                  } else {
                     var2 = this.parseString(var1, "iso-8859-5", (boolean)1);
                     if(var2 != -1) {
                        var3 = 0 + var2;
                     } else {
                        var2 = this.parseString(var1, "iso-8859-6", (boolean)1);
                        if(var2 != -1) {
                           var3 = 0 + var2;
                        } else {
                           var2 = this.parseString(var1, "iso-8859-7", (boolean)1);
                           if(var2 != -1) {
                              var3 = 0 + var2;
                           } else {
                              var2 = this.parseString(var1, "iso-8859-8", (boolean)1);
                              if(var2 != -1) {
                                 var3 = 0 + var2;
                              } else {
                                 var2 = this.parseString(var1, "iso-8859-9", (boolean)1);
                                 if(var2 != -1) {
                                    var3 = 0 + var2;
                                 } else {
                                    var2 = this.parseString(var1, "UTF-8", (boolean)1);
                                    if(var2 != -1) {
                                       var3 = 0 + var2;
                                    } else {
                                       var2 = this.parseString(var1, "BIG5", (boolean)1);
                                       if(var2 != -1) {
                                          var3 = 0 + var2;
                                       } else {
                                          var2 = this.parseString(var1, "SHIFT_JIS", (boolean)1);
                                          if(var2 != -1) {
                                             var3 = 0 + var2;
                                          } else {
                                             var2 = this.parseString(var1, "GBK", (boolean)1);
                                             if(var2 != -1) {
                                                var3 = 0 + var2;
                                             } else {
                                                var2 = this.parseXWord(var1);
                                                if(var2 != -1) {
                                                   var3 = 0 + var2;
                                                } else {
                                                   var3 = -1;
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return var3;
   }

   protected int parseCrlf(int var1) {
      int var2 = this.mBuffer.length();
      byte var3;
      if(var1 >= var2) {
         var3 = -1;
      } else {
         if(this.mBuffer.charAt(var1) == 13) {
            int var4 = var1 + 1;
            if(this.mBuffer.charAt(var4) == 10) {
               var3 = 2;
               return var3;
            }
         }

         var3 = -1;
      }

      return var3;
   }

   protected int parseLangVal(int var1) {
      boolean var2 = false;
      int var3 = this.parseTag(var1);
      int var4;
      if(-1 == var3) {
         var4 = -1;
      } else {
         var1 += var3;
         int var5 = 0 + var3;

         while(true) {
            var3 = this.parseString(var1, "-", (boolean)0);
            if(-1 == var3) {
               break;
            }

            var1 += var3;
            var5 += var3;
            var3 = this.parseTag(var1);
            if(-1 == var3) {
               break;
            }

            var1 += var3;
            var5 += var3;
         }

         var4 = var5;
      }

      return var4;
   }

   protected int parseOctet(int var1) {
      boolean var2 = false;
      int var3 = this.parseString(var1, "=\n", (boolean)0);
      int var10000;
      if(-1 != var3) {
         int var4 = var1 + var3;
         int var5 = 0 + var3;
         int var6 = this.removeWs(var4);
         var1 = var4 + var6;
         var10000 = var5 + var6;
      }

      int var8 = this.parseString(var1, "=\r\n", (boolean)0);
      int var13;
      if(-1 != var8) {
         int var9 = var1 + var8;
         int var10 = 0 + var8;
         int var11 = this.removeWs(var9);
         var10000 = var9 + var11;
         var13 = var10 + var11;
      } else {
         int var14 = this.parseString(var1, "==\r\n", (boolean)0);
         if(-1 != var14) {
            int var15 = var1 + var14;
            int var16 = 0 + var14;
            int var17 = this.removeWs(var15);
            var10000 = var15 + var17;
            var13 = var16 + var17;
         } else {
            int var19 = this.parseString(var1, "=", (boolean)0);
            if(-1 == var19) {
               var13 = -1;
            } else {
               var1 += var19;
               int var26 = 0 + var19;

               label62: {
                  int var21;
                  int var22;
                  try {
                     char var20 = this.mBuffer.charAt(var1);
                     if(var20 == 32 || var20 == 9) {
                        var13 = var26 + 1;
                        return var13;
                     }

                     if((var20 < 48 || var20 > 57) && (var20 < 65 || var20 > 70)) {
                        break label62;
                     }

                     ++var1;
                     var21 = var26 + 1;
                     var20 = this.mBuffer.charAt(var1);
                     if(var20 >= 48 && var20 <= 57 || var20 >= 65 && var20 <= 70) {
                        var13 = var21 + 1;
                        return var13;
                     }

                     var22 = this.parseString(var1, "=\r\n", (boolean)0);
                  } catch (IndexOutOfBoundsException var25) {
                     break label62;
                  }

                  if(var22 != -1) {
                     var10000 = var1 + var22;
                     var13 = var21 + var22;
                     return var13;
                  }
               }

               var13 = -1;
            }
         }
      }

      return var13;
   }

   protected int parsePEncodingVal(int var1) {
      int var2 = this.parseString(var1, "7BIT", (boolean)1);
      int var3;
      if(var2 != -1) {
         this.mEncoding = "7BIT";
         var3 = 0 + var2;
      } else {
         var2 = this.parseString(var1, "8BIT", (boolean)1);
         if(var2 != -1) {
            this.mEncoding = "8BIT";
            var3 = 0 + var2;
         } else {
            var2 = this.parseString(var1, "QUOTED-PRINTABLE", (boolean)1);
            if(var2 != -1) {
               this.mEncoding = "QUOTED-PRINTABLE";
               var3 = 0 + var2;
            } else {
               var2 = this.parseString(var1, "BASE64", (boolean)1);
               if(var2 != -1) {
                  this.mEncoding = "BASE64";
                  var3 = 0 + var2;
               } else {
                  var2 = this.parseXWord(var1);
                  if(var2 != -1) {
                     String var4 = this.mBuffer.substring(var1).substring(0, var2);
                     this.mEncoding = var4;
                     var3 = 0 + var2;
                  } else {
                     var3 = -1;
                  }
               }
            }
         }
      }

      return var3;
   }

   protected int parsePValueVal(int var1) {
      int var2 = this.parseString(var1, "INLINE", (boolean)1);
      int var3;
      if(var2 != -1) {
         var3 = 0 + var2;
      } else {
         var2 = this.parseString(var1, "URL", (boolean)1);
         if(var2 != -1) {
            var3 = 0 + var2;
         } else {
            var2 = this.parseString(var1, "CONTENT-ID", (boolean)1);
            if(var2 != -1) {
               var3 = 0 + var2;
            } else {
               var2 = this.parseString(var1, "CID", (boolean)1);
               if(var2 != -1) {
                  var3 = 0 + var2;
               } else {
                  var2 = this.parseString(var1, "INLINE", (boolean)1);
                  if(var2 != -1) {
                     var3 = 0 + var2;
                  } else {
                     var2 = this.parseXWord(var1);
                     if(var2 != -1) {
                        var3 = 0 + var2;
                     } else {
                        var3 = -1;
                     }
                  }
               }
            }
         }
      }

      return var3;
   }

   protected int parsePtext(int var1) {
      char var2;
      boolean var3;
      int var4;
      try {
         var2 = this.mBuffer.charAt(var1);
         var3 = this.isPrintable(var2);
      } catch (IndexOutOfBoundsException var7) {
         var4 = -1;
         return var4;
      }

      if(var3 && var2 != 61 && var2 != 32 && var2 != 9) {
         var4 = 1;
      } else {
         int var6 = this.parseOctet(var1);
         if(var6 != -1) {
            var4 = var6;
         } else {
            var4 = -1;
         }
      }

      return var4;
   }

   protected int parseQuotedPrintable(int var1) {
      int var2 = this.removeWs(var1);
      int var3 = var1 + var2;
      int var4 = 0 + var2;

      while(true) {
         int var5 = this.parsePtext(var3);
         if(-1 == var5) {
            int var6 = this.parseString(var3, "=", (boolean)0);
            if(var6 != -1) {
               var4 += var6;
            }

            return var4;
         }

         int var7 = var3 + var5;
         int var8 = var4 + var5;
         int var9 = this.removeWs(var7);
         var3 = var7 + var9;
         var4 = var8 + var9;
      }
   }

   protected int parseString(int var1, String var2, boolean var3) {
      int var4;
      if(var2 == null) {
         var4 = -1;
      } else {
         int var9;
         if(var3) {
            int var5 = var2.length();

            boolean var8;
            try {
               String var6 = this.mBuffer;
               int var7 = var1 + var5;
               var8 = var6.substring(var1, var7).equalsIgnoreCase(var2);
            } catch (IndexOutOfBoundsException var11) {
               var4 = -1;
               return var4;
            }

            if(!var8) {
               var4 = -1;
               return var4;
            }

            var9 = var5;
         } else {
            if(!this.mBuffer.startsWith(var2, var1)) {
               var4 = -1;
               return var4;
            }

            var9 = var2.length();
         }

         var4 = var9;
      }

      return var4;
   }

   protected int parseTag(int var1) {
      int var2 = 0;

      int var3;
      for(var3 = 0; var3 < 8; ++var3) {
         boolean var5;
         try {
            char var4 = this.mBuffer.charAt(var1);
            var5 = this.isLetter(var4);
         } catch (IndexOutOfBoundsException var8) {
            break;
         }

         if(!var5) {
            break;
         }

         ++var2;
         ++var1;
      }

      int var6;
      if(var3 == 0) {
         var6 = -1;
      } else {
         var6 = var2;
      }

      return var6;
   }

   protected abstract int parseVFile(int var1);

   protected int parseValue(int var1) {
      int var2;
      int var3;
      if(this.mEncoding != null && !this.mEncoding.equalsIgnoreCase("7BIT") && !this.mEncoding.equalsIgnoreCase("8BIT") && !this.mEncoding.toUpperCase().startsWith("X-")) {
         if(this.mEncoding.equalsIgnoreCase("QUOTED-PRINTABLE")) {
            var2 = this.parseQuotedPrintable(var1);
            if(var2 != -1) {
               var3 = var2;
            } else {
               var3 = -1;
            }
         } else if(this.mEncoding.equalsIgnoreCase("BASE64")) {
            var2 = this.parseBase64(var1);
            if(var2 != -1) {
               var3 = var2;
            } else {
               var3 = -1;
            }
         } else {
            var3 = -1;
         }
      } else {
         var2 = this.parse8bit(var1);
         if(var2 != -1) {
            var3 = var2;
         } else {
            var3 = -1;
         }
      }

      return var3;
   }

   protected int parseWord(int param1) {
      // $FF: Couldn't be decompiled
   }

   protected int parseWsls(int param1) {
      // $FF: Couldn't be decompiled
   }

   protected int parseXWord(int var1) {
      boolean var2 = false;
      int var3 = this.parseString(var1, "X-", (boolean)1);
      int var4;
      if(-1 == var3) {
         var4 = -1;
      } else {
         int var5 = var1 + var3;
         int var6 = 0 + var3;
         var3 = this.parseWord(var5);
         if(-1 == var3) {
            var4 = -1;
         } else {
            var4 = var6 + var3;
         }
      }

      return var4;
   }

   protected int removeWs(int var1) {
      int var2 = this.mBuffer.length();
      int var3;
      if(var1 >= var2) {
         var3 = -1;
      } else {
         int var4 = 0;

         while(true) {
            char var5 = this.mBuffer.charAt(var1);
            if(var5 != 32 && var5 != 9) {
               var3 = var4;
               break;
            }

            ++var1;
            ++var4;
         }
      }

      return var3;
   }

   protected void setInputStream(InputStream var1, String var2) throws UnsupportedEncodingException {
      InputStreamReader var3 = new InputStreamReader(var1, var2);
      StringBuffer var4 = new StringBuffer();

      label37:
      while(true) {
         int var5;
         label35: {
            while(true) {
               try {
                  var5 = var3.read();
                  if(var5 == -1) {
                     break label37;
                  }

                  if(var5 != 13) {
                     break label35;
                  }

                  var5 = var3.read();
                  if(var5 == 10) {
                     var5 = var3.read();
                     if(var5 != 32 && var5 != 9) {
                        break;
                     }

                     char var6 = (char)var5;
                     var4.append(var6);
                     continue;
                  }
               } catch (Exception var14) {
                  return;
               }

               StringBuffer var11 = var4.append("\r");
               break label35;
            }

            StringBuffer var9 = var4.append("\r\n");
            if(var5 == -1) {
               break;
            }
         }

         char var12 = (char)var5;
         var4.append(var12);
      }

      String var10 = var4.toString();
      this.mBuffer = var10;
   }

   protected void setInputStream(String var1) throws UnsupportedEncodingException {
      int var2 = var1.length();
      StringBuffer var3 = new StringBuffer(var2);
      var3.append(var1);
      int var5 = 0;
      int var6 = 0;

      while(var5 >= 0) {
         var5 = var3.indexOf("\r\n ", var5);
         if(var5 < 0) {
            var3.indexOf("\r\n\t", var5);
         }

         if(var5 >= 0) {
            while(true) {
               int var8 = var5 + 2 + var6;
               if(var3.charAt(var8) != 32) {
                  int var9 = var5 + 2 + var6;
                  if(var3.charAt(var9) != 9) {
                     int var10 = var5 + 2;
                     int var11 = var5 + 2 + var6;
                     var3.delete(var10, var11);
                     var6 = 0;
                     break;
                  }
               }

               ++var6;
            }
         }
      }

      String var13 = var3.toString();
      this.mBuffer = var13;
   }
}
