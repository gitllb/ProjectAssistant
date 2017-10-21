package org.apache.pdfbox.pdfparser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSBoolean;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNull;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSObjectKey;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.util.Charsets;




public abstract class BaseParser
{
  private static final long OBJECT_NUMBER_THRESHOLD = 10000000000L;
  private static final long GENERATION_NUMBER_THRESHOLD = 65535L;
   static final int MAX_LENGTH_LONG = Long.toString(Long.MAX_VALUE).length();
  



   private static final Log LOG = LogFactory.getLog(BaseParser.class);
  

  protected static final int E = 101;
  

  protected static final int N = 110;
  

  protected static final int D = 100;
  

  protected static final int S = 115;
  

  protected static final int T = 116;
  

  protected static final int R = 114;
  

  protected static final int A = 97;
  

  protected static final int M = 109;
  

  protected static final int O = 111;
  

  protected static final int B = 98;
  

  protected static final int J = 106;
  

  public static final String DEF = "def";
  

  protected static final String ENDOBJ_STRING = "endobj";
  

  protected static final String ENDSTREAM_STRING = "endstream";
  

  protected static final String STREAM_STRING = "stream";
  

  private static final String TRUE = "true";
  

  private static final String FALSE = "false";
  
  private static final String NULL = "null";
  
  protected static final byte ASCII_LF = 10;
  
  protected static final byte ASCII_CR = 13;
  
  private static final byte ASCII_ZERO = 48;
  
  private static final byte ASCII_NINE = 57;
  
  private static final byte ASCII_SPACE = 32;
  
  protected  SequentialSource seqSource;
  
  protected COSDocument document;
  

  public BaseParser(SequentialSource pdfSource)
  {
     this.seqSource = pdfSource;
  }
  
  private static boolean isHexDigit(char ch)
  {
     return (isDigit(ch)) || ((ch >= 'a') && (ch <= 'f')) || ((ch >= 'A') && (ch <= 'F'));
  }
  








  private COSBase parseCOSDictionaryValue()
    throws IOException
  {
     long numOffset = this.seqSource.getPosition();
     COSBase value = parseDirObject();
     skipSpaces();
    
     if ((!(value instanceof COSNumber)) || (!isDigit()))
    {
       return value;
    }
    
     long genOffset = this.seqSource.getPosition();
     COSBase generationNumber = parseDirObject();
     skipSpaces();
     readExpectedChar('R');
     if (!(value instanceof COSInteger))
    {
       throw new IOException("expected number, actual=" + value + " at offset " + numOffset);
    }
     if (!(generationNumber instanceof COSInteger))
    {
       throw new IOException("expected number, actual=" + value + " at offset " + genOffset);
    }
     COSObjectKey key = new COSObjectKey(((COSInteger)value).longValue(), ((COSInteger)generationNumber).intValue());
    

     return getObjectFromPool(key);
  }
  
  private COSBase getObjectFromPool(COSObjectKey key) throws IOException
  {
     if (this.document == null)
    {
       throw new IOException("object reference " + key + " at offset " + this.seqSource.getPosition() + " in content stream");
    }
    
     return this.document.getObjectFromPool(key);
  }
  






  protected COSDictionary parseCOSDictionary()
    throws IOException
  {

     readExpectedChar('<');

     readExpectedChar('<');

     skipSpaces();

     COSDictionary obj = new COSDictionary();

     boolean done = false;

     while (!done)
    {

       skipSpaces();

       char c = (char)this.seqSource.peek();

       if (c == '>')
      {

         done = true;
      }

       else if (c == '/')
      {

         parseCOSDictionaryNameValuePair(obj);

      }
      else
      {

         LOG.warn("Invalid dictionary, found: '" + c + "' but expected: '/' at offset " + this.seqSource.getPosition());

         if (readUntilEndOfCOSDictionary())
        {


           return obj;
        }
      }
    }

     readExpectedChar('>');

     readExpectedChar('>');

     return obj;
  }
  








  private boolean readUntilEndOfCOSDictionary()
    throws IOException
  {

     int c = this.seqSource.read();

     while ((c != -1) && (c != 47) && (c != 62))
    {



      if (c == 101)
      {

         c = this.seqSource.read();
          if (c == 110)
        {
            c = this.seqSource.read();
            if (c == 100)
          {
              c = this.seqSource.read();
              boolean isStream = (c == 115) && (this.seqSource.read() == 116) && (this.seqSource.read() == 114) && (this.seqSource.read() == 101) && (this.seqSource.read() == 97) && (this.seqSource.read() == 109);
            
              boolean isObj = (!isStream) && (c == 111) && (this.seqSource.read() == 98) && (this.seqSource.read() == 106);
              if ((isStream) || (isObj))
            {

                return true;
            }
          }
        }
      }
        c = this.seqSource.read();
    }
      if (c == -1)
    {
        return true;
    }
      this.seqSource.unread(c);
      return false;
  }
  
  private void parseCOSDictionaryNameValuePair(COSDictionary obj) throws IOException
  {
      COSName key = parseCOSName();
      COSBase value = parseCOSDictionaryValue();
      skipSpaces();
      if ((char)this.seqSource.peek() == 'd')
    {


        String potentialDEF = readString();
        if (!potentialDEF.equals("def"))
      {
          this.seqSource.unread(potentialDEF.getBytes(Charsets.ISO_8859_1));
      }
      else
      {
          skipSpaces();
      }
    }
    
      if (value == null)
    {
        LOG.warn("Bad Dictionary Declaration " + this.seqSource);

    }
    else
    {
        value.setDirect(true);
        obj.setItem(key, value);
    }
  }
  


  protected void skipWhiteSpaces()
    throws IOException
  {
      int whitespace = this.seqSource.read();
    



      while (32 == whitespace)
    {
        whitespace = this.seqSource.read();
    }
    
      if (13 == whitespace)
    {
        whitespace = this.seqSource.read();
        if (10 != whitespace)
      {
          this.seqSource.unread(whitespace);
      }
      

    }
      else if (10 != whitespace)
    {



        this.seqSource.unread(whitespace);
    }
  }
  














  private int checkForEndOfString(int bracesParameter)
    throws IOException
  {
      int braces = bracesParameter;
      byte[] nextThreeBytes = new byte[3];
      int amountRead = this.seqSource.read(nextThreeBytes);
    






      if ((amountRead == 3) && (nextThreeBytes[0] == 13))
    {
        if (((nextThreeBytes[1] == 10) && (nextThreeBytes[2] == 47)) || (nextThreeBytes[2] == 62) || (nextThreeBytes[1] == 47) || (nextThreeBytes[1] == 62))
      {

          braces = 0;
      }
    }
      if (amountRead > 0)
    {
        this.seqSource.unread(nextThreeBytes, 0, amountRead);
    }
      return braces;
  }
  






  protected COSString parseCOSString()
    throws IOException
  {
      char nextChar = (char)this.seqSource.read();
      if (nextChar == '<')
    {
        return parseCOSHexString();
    }
      if (nextChar != '(')
    {
        throw new IOException("parseCOSString string should start with '(' or '<' and not '" + nextChar + "' " + this.seqSource);
    }
    

      ByteArrayOutputStream out = new ByteArrayOutputStream();
    

      int braces = 1;
      int c = this.seqSource.read();
      while ((braces > 0) && (c != -1))
    {
        char ch = (char)c;
        int nextc = -2;
      
        if (ch == ')')
      {

          braces--;
          braces = checkForEndOfString(braces);
          if (braces != 0)
        {
            out.write(ch);
        }
      }
        else if (ch == '(')
      {
          braces++;
          out.write(ch);
      }
        else if (ch == '\\')
      {

          char next = (char)this.seqSource.read();
          switch (next)
        {
        case 'n': 
            out.write(10);
            break;
        case 'r': 
            out.write(13);
            break;
        case 't': 
            out.write(9);
            break;
        case 'b': 
            out.write(8);
            break;
        case 'f': 
            out.write(12);
            break;
        
        case ')': 
            braces = checkForEndOfString(braces);
            if (braces != 0)
          {
              out.write(next);
          }
          else
          {
              out.write(92);
          }
            break;
        case '(': 
        case '\\': 
            out.write(next);
            break;
        
        case '\n': 
        case '\r': 
            c = this.seqSource.read();
            while ((isEOL(c)) && (c != -1))
          {
              c = this.seqSource.read();
          }
            nextc = c;
            break;
        
        case '0': 
        case '1': 
        case '2': 
        case '3': 
        case '4': 
        case '5': 
        case '6': 
        case '7': 
            StringBuilder octal = new StringBuilder();
            octal.append(next);
            c = this.seqSource.read();
            char digit = (char)c;
            if ((digit >= '0') && (digit <= '7'))
          {
              octal.append(digit);
              c = this.seqSource.read();
              digit = (char)c;
              if ((digit >= '0') && (digit <= '7'))
            {
                octal.append(digit);
            }
            else
            {
                nextc = c;
            }
          }
          else
          {
              nextc = c;
          }
          
            int character = 0;
          try
          {
              character = Integer.parseInt(octal.toString(), 8);
          }
          catch (NumberFormatException e)
          {
              throw new IOException("Error: Expected octal character, actual='" + octal + "'", e);
          }
            out.write(character);
            break;
        



        default: 
            out.write(next);
        }
        
      }
      else
      {
          out.write(ch);
      }
        if (nextc != -2)
      {
          c = nextc;
      }
      else
      {
          c = this.seqSource.read();
      }
    }
      if (c != -1)
    {
        this.seqSource.unread(c);
    }
      return new COSString(out.toByteArray());
  }
  











  private COSString parseCOSHexString()
    throws IOException
  {
       StringBuilder sBuf = new StringBuilder();
	    List<Byte> byteList=new ArrayList(); 
    for (;;)
    {
        int c = this.seqSource.read();
        if(c==62){
        	break;
        }
        
        if(c==-1){
        	throw new IOException("Missing closing bracket: > for hex string. Reached EOS.");
        }
        
        sBuf.append((char)c);
        byteList.add((byte)c);
		
//        if (isHexDigit((char)c))
//      {
//         // sBuf.append((char)c);
//			byteList.add((byte)c);
//      } else {
//    	  
//    	  if (c == 62) {
//              break;
//            }
//            
//              if (c < 0)
//            {
//                throw new IOException("Missing closing bracket for hex string. Reached EOS.");
//            }
//         
//          if ((c != 32) && (c != 10) && (c != 9) && (c != 13) && (c != 8) && (c != 12))
//        {
//
//
//
//          do
//          {
//              c = this.seqSource.read();
//          }
//            while ((c != 62) && (c >= 0));
//          
//
//
//
//            if (c >= 0)
//            break;
//            throw new IOException("Missing closing bracket for hex string. Reached EOS.");
//        }
//      }
    }
    

	   byte[] bytes=new byte[byteList.size()];
				for(int i=0;i<bytes.length;i++){
					bytes[i]=byteList.get(i);
				}
				
	 
		String str=	"hello,中国";	
	  byte[] hBytes=str.getBytes("utf-16LE");
	  byte[] gbk=str.getBytes("gb2312");
	  byte[] unicode=str.getBytes("unicode");
	  byte[] uf16=str.getBytes("utf-16BE");
	  byte[] asic=str.getBytes("ISO-8859-1");
	  String stru="4848526648485256484852704848527048485350484848704852545148685151";
	  String unicodeString =bytesToHexString(unicode);
	  String cosString=new String(bytes,"unicode");
      return COSString.parseHex(sBuf.toString());//new COSString(unicode);//
  }
  





  /*
  * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
  * @param src byte[] data
  * @return hex string
  */   
 public static String bytesToHexString(byte[] src){
     StringBuilder stringBuilder = new StringBuilder("");
     if (src == null || src.length <= 0) {
         return null;
     }
     for (int i = 0; i < src.length; i++) {
         int v = src[i] & 0xFF;
         String hv = Integer.toHexString(v);
         if (hv.length() < 2) {
             stringBuilder.append(0);
         }
         stringBuilder.append(hv);
     }
     return stringBuilder.toString();
 }
 /**
  * Convert hex string to byte[]
  * @param hexString the hex string
  * @return byte[]
  */
 public static byte[] hexStringToBytes(String hexString) {
     if (hexString == null || hexString.equals("")) {
         return null;
     }
     hexString = hexString.toUpperCase();
     int length = hexString.length() / 2;
     char[] hexChars = hexString.toCharArray();
     byte[] d = new byte[length];
     for (int i = 0; i < length; i++) {
         int pos = i * 2;
         d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
     }
     return d;
 }
 /**
  * Convert char to byte
  * @param c char
  * @return byte
  */
  private static byte charToByte(char c) {
     return (byte) "0123456789ABCDEF".indexOf(c);
 }

protected COSArray parseCOSArray()
    throws IOException
  {
      readExpectedChar('[');
      COSArray po = new COSArray();
    
      skipSpaces();
    int i;
      while (((i = this.seqSource.peek()) > 0) && ((char)i != ']'))
    {
        COSBase pbo = parseDirObject();
        if ((pbo instanceof COSObject))
      {

          if ((po.get(po.size() - 1) instanceof COSInteger))
        {
            COSInteger genNumber = (COSInteger)po.remove(po.size() - 1);
            if ((po.get(po.size() - 1) instanceof COSInteger))
          {
              COSInteger number = (COSInteger)po.remove(po.size() - 1);
              COSObjectKey key = new COSObjectKey(number.longValue(), genNumber.intValue());
              pbo = getObjectFromPool(key);

          }
          else
          {
              pbo = null;
          }
        }
        else
        {
            pbo = null;
        }
      }
        if (pbo != null)
      {
          po.add(pbo);

      }
      else
      {
          LOG.warn("Corrupt object reference at offset " + this.seqSource.getPosition());
        


          String isThisTheEnd = readString();
          this.seqSource.unread(isThisTheEnd.getBytes(Charsets.ISO_8859_1));
          if (("endobj".equals(isThisTheEnd)) || ("endstream".equals(isThisTheEnd)))
        {
            return po;
        }
      }
        skipSpaces();
    }
    
      this.seqSource.read();
      skipSpaces();
      return po;
  }
  






  protected boolean isEndOfName(int ch)
  {
      return (ch == 32) || (ch == 13) || (ch == 10) || (ch == 9) || (ch == 62) || (ch == 60) || (ch == 91) || (ch == 47) || (ch == 93) || (ch == 41) || (ch == 40) || (ch == 0) || (ch == 12);
  }
  







  protected COSName parseCOSName()
    throws IOException
  {
      readExpectedChar('/');
      ByteArrayOutputStream buffer = new ByteArrayOutputStream();
      int c = this.seqSource.read();
      while (c != -1)
    {
        int ch = c;
        if (ch == 35)
      {
          int ch1 = this.seqSource.read();
          int ch2 = this.seqSource.read();
        





          if ((isHexDigit((char)ch1)) && (isHexDigit((char)ch2)))
        {
            String hex = "" + (char)ch1 + (char)ch2;
          try
          {
              buffer.write(Integer.parseInt(hex, 16));
          }
          catch (NumberFormatException e)
          {
              throw new IOException("Error: expected hex digit, actual='" + hex + "'", e);
          }
            c = this.seqSource.read();

        }
        else
        {
            if ((ch2 == -1) || (ch1 == -1))
          {
              LOG.error("Premature EOF in BaseParser#parseCOSName");
              c = -1;
              break;
          }
            this.seqSource.unread(ch2);
            c = ch1;
            buffer.write(ch);
        }
      } else {
          if (isEndOfName(ch)) {
          break;
        }
        


          buffer.write(ch);
          c = this.seqSource.read();
      }
    }
      if (c != -1)
    {
        this.seqSource.unread(c);
    }
    
      byte[] bytes = buffer.toByteArray();
    String string;
       if (isValidUTF8(bytes))
    {
        string = new String(buffer.toByteArray(), Charsets.UTF_8);

    }
    else
    {
        string = new String(buffer.toByteArray(), Charsets.WINDOWS_1252);
    }
      return COSName.getPDFName(string);
  }
  



  private boolean isValidUTF8(byte[] input)
  {
      CharsetDecoder cs = Charsets.UTF_8.newDecoder();
    try
    {
        cs.decode(ByteBuffer.wrap(input));
        return true;
    }
    catch (CharacterCodingException e) {}
    
      return false;
  }
  







  protected COSBoolean parseBoolean()
    throws IOException
  {
      COSBoolean retval = null;
      char c = (char)this.seqSource.peek();
      if (c == 't')
    {
        String trueString = new String(this.seqSource.readFully(4), Charsets.ISO_8859_1);
        if (!trueString.equals("true"))
      {
          throw new IOException("Error parsing boolean: expected='true' actual='" + trueString + "' at offset " + this.seqSource.getPosition());
      }
      


        retval = COSBoolean.TRUE;

    }
      else if (c == 'f')
    {
        String falseString = new String(this.seqSource.readFully(5), Charsets.ISO_8859_1);
        if (!falseString.equals("false"))
      {
          throw new IOException("Error parsing boolean: expected='true' actual='" + falseString + "' at offset " + this.seqSource.getPosition());
      }
      


        retval = COSBoolean.FALSE;

    }
    else
    {
        throw new IOException("Error parsing boolean expected='t or f' actual='" + c + "' at offset " + this.seqSource.getPosition());
    }
    
      return retval;
  }
  






  protected COSBase parseDirObject()
    throws IOException
  {
      COSBase retval = null;
    
      skipSpaces();
      int nextByte = this.seqSource.peek();
      char c = (char)nextByte;
      switch (c)
    {


    case '<': 
        int leftBracket = this.seqSource.read();
      
        c = (char)this.seqSource.peek();
        this.seqSource.unread(leftBracket);
        if (c == '<')
      {

          retval = parseCOSDictionary();
          skipSpaces();
      }
      else
      {
          retval = parseCOSString();
      }
        break;
    


    case '[': 
        retval = parseCOSArray();
        break;
    
    case '(': 
        retval = parseCOSString();
        break;
    
    case '/': 
        retval = parseCOSName();
        break;
    

    case 'n': 
        readExpectedString("null");
        retval = COSNull.NULL;
        break;
    

    case 't': 
        String trueString = new String(this.seqSource.readFully(4), Charsets.ISO_8859_1);
        if (trueString.equals("true"))
      {
          retval = COSBoolean.TRUE;
      }
      else
      {
          throw new IOException("expected true actual='" + trueString + "' " + this.seqSource + "' at offset " + this.seqSource.getPosition());
      }
      


      break;
    case 'f': 
        String falseString = new String(this.seqSource.readFully(5), Charsets.ISO_8859_1);
        if (falseString.equals("false"))
      {
          retval = COSBoolean.FALSE;
      }
      else
      {
          throw new IOException("expected false actual='" + falseString + "' " + this.seqSource + "' at offset " + this.seqSource.getPosition());
      }
      

      break;
    case 'R': 
        this.seqSource.read();
        retval = new COSObject(null);
        break;
    case '?': 
        return null;
    
    default: 
        if ((Character.isDigit(c)) || (c == '-') || (c == '+') || (c == '.'))
      {
          StringBuilder buf = new StringBuilder();
          int ic = this.seqSource.read();
          c = (char)ic;
          while ((Character.isDigit(c)) || (c == '-') || (c == '+') || (c == '.') || (c == 'E') || (c == 'e'))
        {





            buf.append(c);
            ic = this.seqSource.read();
            c = (char)ic;
        }
          if (ic != -1)
        {
            this.seqSource.unread(ic);
        }
          retval = COSNumber.get(buf.toString());


      }
      else
      {

          String badString = readString();
          if ((badString == null) || (badString.length() == 0))
        {
            int peek = this.seqSource.peek();
          
            throw new IOException("Unknown dir object c='" + c + "' cInt=" + c + " peek='" + (char)peek + "' peekInt=" + peek + " at offset " + this.seqSource.getPosition());
        }
        



          if (("endobj".equals(badString)) || ("endstream".equals(badString)))
        {
            this.seqSource.unread(badString.getBytes(Charsets.ISO_8859_1)); }
      }
      break;
    }
    
      return retval;
  }
  






  protected String readString()
    throws IOException
  {
      skipSpaces();
      StringBuilder buffer = new StringBuilder();
      int c = this.seqSource.read();
      while ((!isEndOfName((char)c)) && (c != -1))
    {
        buffer.append((char)c);
        c = this.seqSource.read();
    }
      if (c != -1)
    {
        this.seqSource.unread(c);
    }
      return buffer.toString();
  }
  






  protected void readExpectedString(String expectedString)
    throws IOException
  {
     readExpectedString(expectedString.toCharArray(), false);
  }
  






  protected final void readExpectedString(char[] expectedString, boolean skipSpaces)
    throws IOException
  {
      skipSpaces();
      for (char c : expectedString)
    {
        if (this.seqSource.read() != c)
      {
          throw new IOException("Expected string '" + new String(expectedString) + "' but missed at character '" + c + "' at offset " + this.seqSource.getPosition());
      }
    }
    

      skipSpaces();
  }
  






  protected void readExpectedChar(char ec)
    throws IOException
  {
      char c = (char)this.seqSource.read();
      if (c != ec)
    {
        throw new IOException("expected='" + ec + "' actual='" + c + "' at offset " + this.seqSource.getPosition());
    }
  }
  








  protected String readString(int length)
    throws IOException
  {
      skipSpaces();
    
      int c = this.seqSource.read();
    


      StringBuilder buffer = new StringBuilder(length);
      while ((!isWhitespace(c)) && (!isClosing(c)) && (c != -1) && (buffer.length() < length) && (c != 91) && (c != 60) && (c != 40) && (c != 47))
    {




        buffer.append((char)c);
        c = this.seqSource.read();
    }
      if (c != -1)
    {
        this.seqSource.unread(c);
    }
      return buffer.toString();
  }
  






  protected boolean isClosing()
    throws IOException
  {
      return isClosing(this.seqSource.peek());
  }
  






  protected boolean isClosing(int c)
  {
      return c == 93;
  }
  








  protected String readLine()
    throws IOException
  {
      if (this.seqSource.isEOF())
    {
        throw new IOException("Error: End-of-File, expected line");
    }
    
      StringBuilder buffer = new StringBuilder(11);
    
    int c;
      while ((c = this.seqSource.read()) != -1)
    {

        if (isEOL(c)) {
        break;
      }
      
        buffer.append((char)c);
    }
    
      if ((isCR(c)) && (isLF(this.seqSource.peek())))
    {
        this.seqSource.read();
    }
      return buffer.toString();
  }
  






  protected boolean isEOL()
    throws IOException
  {
      return isEOL(this.seqSource.peek());
  }
  






  protected boolean isEOL(int c)
  {
      return (isLF(c)) || (isCR(c));
  }
  
  private boolean isLF(int c)
  {
      return 10 == c;
  }
  
  private boolean isCR(int c)
  {
      return 13 == c;
  }
  






  protected boolean isWhitespace()
    throws IOException
  {
      return isWhitespace(this.seqSource.peek());
  }
  






  protected boolean isWhitespace(int c)
  {
      return (c == 0) || (c == 9) || (c == 12) || (c == 10) || (c == 13) || (c == 32);
  }
  







  protected boolean isSpace()
    throws IOException
  {
      return isSpace(this.seqSource.peek());
  }
  






  protected boolean isSpace(int c)
  {
      return 32 == c;
  }
  






  protected boolean isDigit()
    throws IOException
  {
      return isDigit(this.seqSource.peek());
  }
  






  protected static boolean isDigit(int c)
  {
      return (c >= 48) && (c <= 57);
  }
  




  protected void skipSpaces()
    throws IOException
  {
      int c = this.seqSource.read();
    
      while ((isWhitespace(c)) || (c == 37))
    {
        if (c == 37)
      {

          c = this.seqSource.read();
          while ((!isEOL(c)) && (c != -1))
        {
            c = this.seqSource.read();
        }
      }
      else
      {
          c = this.seqSource.read();
      }
    }
      if (c != -1)
    {
        this.seqSource.unread(c);
    }
  }
  







  protected long readObjectNumber()
    throws IOException
  {
     long retval = readLong();
     if ((retval < 0L) || (retval >= 10000000000L))
    {
       throw new IOException("Object Number '" + retval + "' has more than 10 digits or is negative");
    }
     return retval;
  }
  





  protected int readGenerationNumber()
    throws IOException
  {
     int retval = readInt();
     if ((retval < 0) || (retval > 65535L))
    {
       throw new IOException("Generation Number '" + retval + "' has more than 5 digits");
    }
     return retval;
  }
  






  protected int readInt()
    throws IOException
  {
     skipSpaces();
     int retval = 0;
    
     StringBuilder intBuffer = readStringNumber();
    
    try
    {
       retval = Integer.parseInt(intBuffer.toString());
    }
    catch (NumberFormatException e)
    {
       this.seqSource.unread(intBuffer.toString().getBytes(Charsets.ISO_8859_1));
       throw new IOException("Error: Expected an integer type at offset " + this.seqSource.getPosition(), e);
    }
     return retval;
  }
  







  protected long readLong()
    throws IOException
  {
     skipSpaces();
     long retval = 0L;
    
     StringBuilder longBuffer = readStringNumber();
    
    try
    {
       retval = Long.parseLong(longBuffer.toString());
    }
    catch (NumberFormatException e)
    {
       this.seqSource.unread(longBuffer.toString().getBytes(Charsets.ISO_8859_1));
       throw new IOException("Error: Expected a long type at offset " + this.seqSource.getPosition() + ", instead got '" + longBuffer + "'", e);
    }
    
     return retval;
  }


  protected final StringBuilder readStringNumber()
    throws IOException
  {
     int lastByte = 0;
     StringBuilder buffer = new StringBuilder();
     while (((lastByte = this.seqSource.read()) != 32) && (lastByte != 10) && (lastByte != 13) && (lastByte != 60) && (lastByte != 91) && (lastByte != 40) && (lastByte != 0) && (lastByte != -1))
    {
 

       buffer.append((char)lastByte);
       if (buffer.length() > MAX_LENGTH_LONG)
      {
         throw new IOException("Number '" + buffer + "' is getting too long, stop reading at offset " + this.seqSource.getPosition());
      }
    }
    
     if (lastByte != -1)
    {
       this.seqSource.unread(lastByte);
    }
    return buffer;
  }
}


