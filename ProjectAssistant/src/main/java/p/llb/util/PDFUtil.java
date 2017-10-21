package p.llb.util;

import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDInlineImage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Autowired;

public class PDFUtil {
	public static void main(String[] args) throws Exception {
		String pdfDir = PDFUtil.class.getResource("/").getPath()+ "/pdf";
		String test_out=pdfDir+"/test.pdf";
		String txt_out=pdfDir+"/txt.pdf";
		String table_out=pdfDir+"/table.pdf";
		String img_out=pdfDir+"/img.pdf";
		String path = PDFUtil.class.getResource("/").getPath()
				+ "/pdf/软件需求分析.pdf";
		//createpdf(test_out);
		//printPDF(txt_out);
		//creatTextPdf(txt_out);
		//createTablePdf(table_out);
		convertToDoc(table_out,"");
		
	}
	
	private static void createTablePdf(String file) {
		// TODO Auto-generated method stub
		PDDocument doc = new PDDocument();
		
        try
        {
        	
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream content=new PDPageContentStream(doc,page);
            Rectangle rec=new Rectangle(10,750,20,8);
            //content.fillRect(10,750,20,8);//填充方块
            content.setLineWidth(0.5f);
            content.moveTo(10, 738);
            content.lineTo(60, 738);
            content.lineTo(60, 748);
            content.lineTo(10, 748);
            content.lineTo(10, 738);
            content.stroke();
            content.addRect(10,750, 50, 0.5f);
            content.fill();
            content.beginText();
            content.newLineAtOffset(12, 740);
            
            content.setFont(createChineseFont(doc,null), 8);
            content.showText("表格测试");
            content.endText();
            content.close();
            doc.save(file);
        }catch(IOException e){
        	e.printStackTrace();
        }
        finally
        {
            try {
				doc.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	public static void printPDF(String path) throws IOException{
		InputStream in=new FileInputStream(path);
		while(true){
			int c=in.read();
			if(c==-1){
				break;
			}
			System.out.print((char)c);
			
		}
		
		in.close();
	}
	
	public static void printDoc(String file) throws IOException{
		
		
	}
	
	public static void creatTextPdf(String path) throws IOException{
		PDDocument txt_doc=new PDDocument();
	      PDFont font = createChineseFont(txt_doc,null);
	      PDPage page = new PDPage();
	      txt_doc.addPage(page);
	      PDPageContentStream contents = new PDPageContentStream(txt_doc, page);
	      contents.addRect(20, 50, 100, 200);
	      contents.beginText();
	      contents.setFont(font, 12);
	     
	      contents.newLineAtOffset(10, 200);
	      contents.showText("hello,中国");
	  
	      contents.endText();
	      contents.close();
	      txt_doc.save(path);
	      txt_doc.close();
	      
	}
	
	public static void createpdf(String file) {
		
		
		PDDocument doc = new PDDocument();
		String path = PDFUtil.class.getResource("/").getPath()
				+ "/pdf/软件需求分析.pdf";
		PDDocument res_doc=null;
        try
        {
        	 res_doc=PDDocument.load(new File(path));
            PDPage page = res_doc.getPage(0);
            doc.addPage(page);

            
            doc.save(file);
        }catch(IOException e){
        	e.printStackTrace();
        }
        finally
        {
            try {
				doc.close();
				res_doc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		//OutputStream img_out=new FileOutputStream(pdfDir+"/img.pdf");
		
	}
	
	private static PDFont createChineseFont(PDDocument doc,String ttf) throws IOException{
		if(ttf==null||"".equals(ttf.trim())){
			
			ttf=System.getenv("SystemRoot")+"/fonts/simkai.ttf";
		}
		return PDType0Font.load(doc, new File(ttf));
		
	}
	
	public static void convertToDoc(String in_pdf,String out_doc) {
	
		PDDocument doc=null;
		OutputStream out=null;
		try {
			doc = PDDocument.load(new File(in_pdf));
			//createChineseFont(doc,null);
			PDFTextStripper textStripper = new PDFTextStripper();
			
			//System.out.println(getPageText(doc,0,1));
			// System.out.println(textStripper.getText(doc));
			out=null;//new FileOutputStream(out_doc);
			writePDDocument( doc, out);
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				if(out!=null)
					out.close();
				if(doc!=null)
					doc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	private static void writePDDocument(PDDocument doc,OutputStream out) throws IOException{
		PDPageTree pTree=doc.getPages();
		writePDPageTree(pTree,out);
	}

	private static void writePDPageTree(PDPageTree pTree, OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		Iterator<PDPage> page_i=pTree.iterator();
		while(page_i.hasNext()){
			writePDPage(page_i.next(),out);
		}
	}
	
	static void toBinary( int num){
        int r;
        r = num%2;//最后一位要输出的，即使参数=1，还是要计算到这里结束，只取出余数就ok了。然后顺次返回上一级主调函数，继续执行剩下的……
                  //如果商 1 / 2 = 0，计算就可以终止了，不需要再算
        if(num>=2){         
             //精华，联系10进制转2进制的算法，每次除以2，取出余数，然后用新的商继续除以2，取出新余数……直到商为0，余数逆序输出即可           
            toBinary(num/2);//把新的商作为参数递归调用
        }
        //在递归语句之后输出，这样就是倒叙输出
        System.out.print(r);         
    }
	
	static void toBinary( byte[] nums){
       for(int n:nums){
    	   toBinary(n);
    	   System.out.print(" "); 
       }
       
       System.out.println(); 
    }

	@Autowired
	private static void writePDPage(PDPage page,OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		
		PDRectangle artBox=page.getArtBox();
		
		InputStream in=page.getContents();
		
		String content=printInputStream(in);
		//System.out.println(content);
		
		 Iterator<PDStream> iterator=page.getContentStreams();
		 while(iterator.hasNext()){
			 PDStream stream=iterator.next();
			 PDFStreamParser parser = new PDFStreamParser(stream);
			 parser.parse();
			 List tokens = parser.getTokens();
			 PDFont font=null;
			 COSName fontName=null;
			 List<COSBase> cosList=new ArrayList();
			 for(int i=0;i<tokens.size();i++){
				 Object token=tokens.get(i);
				if(token instanceof Operator){
					Operator op=(Operator)token;
					
					if("Tf".equals(op.getName())){
						fontName=(COSName)tokens.get(i-2);
					}else if("Tj".equals(op.getName())){
						COSString cos_s=(COSString)tokens.get(i-1);
						printCOSString( cos_s, fontName, page);
					}else if("TJ".equals(op.getName())){
						COSArray cos_a=(COSArray)tokens.get(i-1);
						Iterator<COSBase> items=cos_a.iterator();
						while(items.hasNext()){
							COSBase base=items.next();
							if(base instanceof COSString){
								printCOSString( (COSString)base, fontName, page);
							}
						}
					}
					System.out.println(op.getName());
					
				}else if(token instanceof COSObject){
					System.out.println(token);
				}else if(token instanceof COSString){
					
					
				}
				else{
					System.out.println(token);
					
				}
			 }
		 }
		
	}
	
	
	
	
	private static void printCOSString(COSString cos_s,COSName fontName,PDPage page) throws IOException {
		// TODO Auto-generated method stub
		if(fontName==null){
			System.out.println(cos_s.getString());
		}else{
			//COSName name=page.getResources().getFontNames().iterator().next();
			PDFont font=page.getResources().getFont(fontName);
			
			byte[] bytes=cos_s.getBytes();
			InputStream bIn=new ByteArrayInputStream(bytes);
			int len=bIn.available()/2;
			
			for(int j=0;j<len;j++){
				int c=font.readCode(bIn);
				
				System.out.print(font.toUnicode(c));
			}
			fontName=null;
		}
	}

	public static String getText(PDPage page) throws IOException{
		InputStream in=page.getContents();
		return "";
	}

	private static String printInputStream(InputStream in) throws IOException {
		// TODO Auto-generated method stub
		int buffSize=1024;
		byte[] bytes=new byte[1024];
		StringBuffer sb=new StringBuffer();
		int len=0;
		while(true){
			len=in.read(bytes);
			if(len==-1){
				break;
			}
			//print( bytes);
			sb.append(new String(bytes,0,len));
		}
		return sb.toString();
	}
	
	public static void print(byte[] bytes){
		for(byte b:bytes){
			System.out.print(b+" ");
			if(b==60||b==62){
				System.out.println();
			}
		}
	}

	private static void printTable(PDPage page) throws IOException {
		// TODO Auto-generated method stub
		 PDFTextStripperByArea stripper = new PDFTextStripperByArea();
         stripper.setSortByPosition( true );
         PDRectangle pdfRec=page.getBBox();
         int x=new BigDecimal(pdfRec.getLowerLeftX()).intValue();
         int y=new BigDecimal(pdfRec.getLowerLeftY()).intValue();
         int w=new BigDecimal(pdfRec.getWidth()).intValue();
         int h=new BigDecimal(pdfRec.getHeight()).intValue();
         Rectangle rect = new Rectangle(x,y,w,h);
         stripper.addRegion( "r_1", rect );
        
         stripper.extractRegions( page );
         System.out.println( "Text in the area:" + rect );
         System.out.println( stripper.getTextForRegion( "r_1" ) );
	}

	private static String getPageText(PDDocument doc ,int startPage,int endPage) throws IOException {
		// TODO Auto-generated method stub
		
		PDFTextStripper stripper=new PDFTextStripper();
		stripper.setStartPage(startPage);
		stripper.setEndPage(endPage);
		return stripper.getText(doc);
	}
	

}
