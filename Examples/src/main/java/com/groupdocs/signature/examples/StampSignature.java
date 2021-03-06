package com.groupdocs.signature.examples;

import java.awt.Color;

import com.groupdocs.signature.config.SignatureConfig;
import com.groupdocs.signature.domain.SearchResult;
import com.groupdocs.signature.domain.barcodes.BarcodeTypes;
import com.groupdocs.signature.domain.enums.ExtendedDashStyle;
import com.groupdocs.signature.domain.enums.HorizontalAlignment;
import com.groupdocs.signature.domain.enums.MeasureType;
import com.groupdocs.signature.domain.enums.TextMatchType;
import com.groupdocs.signature.domain.enums.VerticalAlignment;
import com.groupdocs.signature.domain.helpers.stamp.Corners;
import com.groupdocs.signature.domain.helpers.stamp.SquareBorderLine;
import com.groupdocs.signature.domain.signatures.BaseSignature;
import com.groupdocs.signature.domain.signatures.barcode.BarcodeSignature;
import com.groupdocs.signature.domain.stamps.StampBackgroundCropType;
import com.groupdocs.signature.domain.stamps.StampLine;
import com.groupdocs.signature.domain.stamps.StampTextRepeatType;
import com.groupdocs.signature.domain.stamps.StampTypes;
import com.groupdocs.signature.handler.SignatureHandler;
import com.groupdocs.signature.handler.events.ProcessCompleteEventArgs;
import com.groupdocs.signature.handler.events.ProcessCompleteEventHandler;
import com.groupdocs.signature.handler.events.ProcessProgressEventArgs;
import com.groupdocs.signature.handler.events.ProcessProgressEventHandler;
import com.groupdocs.signature.handler.events.ProcessStartEventArgs;
import com.groupdocs.signature.handler.events.ProcessStartEventHandler;
import com.groupdocs.signature.options.OutputType;
import com.groupdocs.signature.options.barcodesearch.PdfSearchBarcodeOptions;
import com.groupdocs.signature.options.qrcodesignature.CellsQRCodeSignOptions;
import com.groupdocs.signature.options.saveoptions.SaveOptions;
import com.groupdocs.signature.options.stampsignature.CellsStampSignOptions;
import com.groupdocs.signature.options.stampsignature.ImagesStampSignOptions;
import com.groupdocs.signature.options.stampsignature.PdfStampSignOptions;
import com.groupdocs.signature.options.stampsignature.SlidesStampSignOptions;
import com.groupdocs.signature.options.stampsignature.WordsStampSignOptions;

public class StampSignature {

	public static void signCellDocWithStampSignature(String fileName) throws Throwable{
		//ExStart:signCellDocWithStampSignature
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		
		// setup options
		CellsStampSignOptions signOptions = new CellsStampSignOptions();
		signOptions.setHeight(120);
		signOptions.setWidth(300);
		 
	    //Inner square lines
		StampLine line0 = new StampLine();
		line0.setText("John");
		line0.setTextBottomIntent(0);
		line0.setTextColor(Color.PINK);
		line0.getOuterBorder().setColor(Color.BLUE);
		line0.getInnerBorder().setColor(Color.BLUE);
		line0.getInnerBorder().setStyle(ExtendedDashStyle.Dash);
		line0.getFont().setFontSize(20);
		line0.getFont().setBold(true);
		line0.setHeight(40);
		signOptions.getInnerLines().add(line0);
		 
		StampLine line1 = new StampLine();
		line1.setText("Smith");
		line1.setTextBottomIntent(0);
		line1.setTextColor(Color.PINK);
		line1.getInnerBorder().setColor(Color.BLUE);
		line1.getFont().setFontSize(20);
		line1.getFont().setBold(true);
		line1.setHeight(40);
		signOptions.getInnerLines().add(line1);
		 
	    // if you need to sign all sheets set it to true
		signOptions.setSignAllPages(true);
		 
		SaveOptions saveOptions =  new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		 
	    // sign document
		String signedPath = handler.<String>sign(fileName, signOptions, saveOptions);
		System.out.println("Signed file path is: "+signedPath);
		//ExEnd:signCellDocWithStampSignature
	}
	
	public static void signPDFDocWithStampSignature(String fileName) throws Throwable{
		//ExStart:signPDFDocWithStampSignature
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		
		// setup options
		PdfStampSignOptions signOptions = new PdfStampSignOptions();
		signOptions.setHeight(300);
		signOptions.setWidth(300);
		 
	    //Outer round lines
		StampLine line0 = new StampLine();
		line0.setText(" * European Union * European Union  * European Union  * European Union  * European Union  * ");
		line0.getFont().setFontSize(12);
		line0.setHeight(22);
		line0.setTextBottomIntent(6);
		line0.setTextColor(Color.LIGHT_GRAY);
		line0.setBackgroundColor(Color.BLUE);
		signOptions.getOuterLines().add(line0);
		 
		StampLine line1 = new StampLine();
		line1.setHeight(2);
		line1.setBackgroundColor(Color.WHITE);
		signOptions.getOuterLines().add(line1);
		 
		StampLine line2 = new StampLine();
		line2.setText("* Entrepreneur * Entrepreneur ** Entrepreneur * Entrepreneur *");
		line2.setTextColor(Color.BLUE);
		line2.getFont().setFontSize(15);
		line2.setHeight(30);
		line2.setTextBottomIntent(8);
		line2.getInnerBorder().setColor(Color.BLUE);
		line2.getOuterBorder().setColor(Color.BLUE);
		line2.getInnerBorder().setStyle(ExtendedDashStyle.Dot);
		signOptions.getOuterLines().add(line2);
		 
	    //Inner square lines
		StampLine line3 = new StampLine();
		line3.setText("John");
		line3.setTextColor(Color.RED);
		line3.getFont().setFontSize(20);
		line3.getFont().setBold(true);
		line3.setHeight(40);
		signOptions.getInnerLines().add(line3);
		 
		StampLine line4 = new StampLine();
		line4.setText("Smith");
		line4.setTextColor(Color.PINK);
		line4.getFont().setFontSize(20);
		line4.getFont().setBold(true);
		line4.setHeight(40);
		signOptions.getInnerLines().add(line4);
		 
		StampLine line5 = new StampLine();
		line5.setText("SSN 1230242424");
		line5.setTextColor(Color.MAGENTA);
		line5.getFont().setFontSize(12);
		line5.getFont().setBold(true);
		line5.setHeight(40);
		signOptions.getInnerLines().add(line5);
		 
	    // if you need to sign all sheets set it to true
		signOptions.setSignAllPages(true);
		 
		SaveOptions saveOptions =  new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		 
	    // sign document
		String signedPath = handler.<String>sign(fileName, signOptions, saveOptions);
		System.out.println("Signed file path is: "+signedPath);
		//ExEnd:signPDFDocWithStampSignature
	}
	
	public static void signSlidesDocWithStampSignature(String fileName) throws Throwable{
		//ExStart:signSlidesDocWithStampSignature
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		
		// setup options
		SlidesStampSignOptions signOptions = new SlidesStampSignOptions();
		signOptions.setHeight(200);
		signOptions.setWidth(400);
		 
	    //Outer round lines
		StampLine line0 = new StampLine();
		line0.setText(" * John * Smith  * John * Smith  * John * Smith  * John * Smith  * John * Smith * John * Smith *  John * Smith * ");
		line0.getFont().setFontSize(12);
		line0.setHeight(22);
		line0.setTextBottomIntent(6);
		line0.setTextColor(Color.LIGHT_GRAY);
		line0.setBackgroundColor(Color.BLUE);
		signOptions.getOuterLines().add(line0);
		 
	    //Inner square lines
		StampLine line1 = new StampLine();
		line1.setText("John Smith");
		line1.setTextColor(Color.MAGENTA);
		line1.getFont().setFontSize(24);
		line1.getFont().setBold(true);
		line1.setHeight(100);
		signOptions.getInnerLines().add(line1);
		 
	    // if you need to sign all sheets set it to true
		signOptions.setSignAllPages(true);
		signOptions.setOpacity(0.8);
		 
		SaveOptions saveOptions =  new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		 
	    // sign document
		String signedPath = handler.<String>sign(fileName, signOptions, saveOptions);
		System.out.println("Signed file path is: "+signedPath);
		//ExEnd:signSlidesDocWithStampSignature
	}
	
	public static void signWordsDocWithStampSignature(String fileName) throws Throwable{
		//ExStart:signWordsDocWithStampSignature
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		
		// setup options
		WordsStampSignOptions signOptions = new WordsStampSignOptions();
		signOptions.setHeight(300);
		signOptions.setWidth(300);
		 
		signOptions.setImageGuid(CommonUtilities.getImagesPath("sign.PNG"));
		signOptions.setBackgroundColor(Color.CYAN);
		 
	    //Inner square lines
		StampLine line0 = new StampLine();
		line0.setText("John");
		line0.setTextColor(Color.PINK);
		line0.getFont().setFontSize(20);
		line0.getFont().setBold(true);
		line0.setHeight(40);
		signOptions.getInnerLines().add(line0);
		 
		StampLine line1 = new StampLine();
		line1.setText("Smith");
		line1.setTextColor(Color.MAGENTA);
		line1.getFont().setFontSize(20);
		line1.getFont().setBold(true);
		line1.setHeight(40);
		signOptions.getInnerLines().add(line1);
		 
	    // if you need to sign all sheets set it to true
		signOptions.setSignAllPages(true);
		 
		SaveOptions saveOptions =  new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		 
	    // sign document
		String signedPath = handler.<String>sign(fileName, signOptions, saveOptions);
		System.out.println("Signed file path is: "+signedPath);
		//ExEnd:signWordsDocWithStampSignature
	}
	
	public static void signImageDocWithStampSignature(String fileName) throws Throwable{
		//ExStart:signImageDocWithStampSignature
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		
		// setup options
		ImagesStampSignOptions signOptions = new ImagesStampSignOptions();
		signOptions.setHeight(300);
		signOptions.setWidth(300);
		  
		signOptions.setBackgroundColor(Color.ORANGE);
		signOptions.setBackgroundColorCropType(StampBackgroundCropType.OuterArea); //This feature is supported starting from version 17.08
		signOptions.setImageGuid(CommonUtilities.getImagesPath("sign.PNG"));
		signOptions.setBackgroundImageCropType(StampBackgroundCropType.InnerArea); //This feature is supported starting from version 17.08
		  
		  //Outer round lines
		StampLine line0 = new StampLine();
		line0.setText("* European Union *");
		line0.setTextRepeatType(StampTextRepeatType.FullTextRepeat); //This feature is supported starting from version 17.08
		line0.getFont().setFontSize(12);
		line0.setHeight(22);
		line0.setTextBottomIntent(6);
		line0.setTextColor(Color.GRAY);
		line0.setBackgroundColor(Color.BLUE);
		signOptions.getOuterLines().add(line0);
		  
		StampLine line1 = new StampLine();
		line1.setHeight(2);
		line1.setBackgroundColor(Color.WHITE);
		signOptions.getOuterLines().add(line1);
		  
		StampLine line2 = new StampLine();
		line2.setText("* Entrepreneur *");
		line2.setTextRepeatType(StampTextRepeatType.FullTextRepeat); //This feature is supported starting from version 17.08
		line2.setTextColor(Color.BLUE);
		line2.getFont().setFontSize(15);
		line2.setHeight(30);
		line2.setTextBottomIntent(8);
		line2.getInnerBorder().setColor(Color.BLUE);
		line2.getOuterBorder().setColor(Color.BLUE);
		line2.getInnerBorder().setStyle(ExtendedDashStyle.Dot);
		signOptions.getOuterLines().add(line2);
		  
		  //Inner square lines
		StampLine line3 = new StampLine();
		line3.setText("John");
		line3.setTextColor(Color.RED);
		line3.getFont().setFontSize(20);
		line3.getFont().setBold(true);
		line3.setHeight(40);
		signOptions.getInnerLines().add(line3);
		  
		StampLine line4 = new StampLine();
		line4.setText("Smith");
		line4.setTextColor(Color.RED);
		line4.getFont().setFontSize(20);
		line4.getFont().setBold(true);
		line4.setHeight(40);
		signOptions.getInnerLines().add(line4);
		  
		StampLine line5 = new StampLine();
		line5.setText("SSN 1230242424");
		line5.setTextColor(Color.RED);
		line5.getFont().setFontSize(12);
		line5.getFont().setBold(true);
		line5.setHeight(40);
		signOptions.getInnerLines().add(line5);
		 
		SaveOptions saveOptions =  new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		 
	    // sign document
		String signedPath = handler.<String>sign(fileName, signOptions, saveOptions);
		System.out.println("Signed file path is: "+signedPath);
		//ExEnd:signImageDocWithStampSignature
	}
	
	public static void signImageWithStampSignatureUsingStampType(String fileName) throws Throwable{
		//ExStart:signImageWithStampSignatureUsingStampType
		// For complete examples and data files, please go to https://github.com/groupdocs-signature/GroupDocs.Signature-for-Java
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);		
		// setup options
		ImagesStampSignOptions signOptions = new ImagesStampSignOptions();
	    // setup stamp type
		signOptions.setStampType(StampTypes.Square); //This feature is supported starting from version 18.6.		  
		// setup other properties
		signOptions.setTop(300);
		signOptions.setLeft(255);
		signOptions.setHeight(150);
		signOptions.setWidth(250);
		signOptions.setStampType(StampTypes.Round);		  
		signOptions.setImageGuid(CommonUtilities.getImagesPath("sign.PNG"));
		  
		    //Outer lines
		StampLine line00 = new StampLine();
		line00.setHeight(18);
		line00.getOuterBorder().setWeight(6);
		line00.getOuterBorder().setColor(Color.BLUE);
		line00.getInnerBorder().setWeight(6);
		line00.getInnerBorder().setColor(Color.CYAN);
		line00.setBackgroundColor(Color.GRAY);
		signOptions.getOuterLines().add(line00);
		  
		StampLine line01 = new StampLine();
		line01.setHeight(20);
		line01.setText("INTERNATIONAL AIRPORT");
		line01.setTextColor(Color.BLUE);
		line01.getFont().setFontSize(10);
		line01.setTextBottomIntent(5);
		line01.getInnerBorder().setWeight(1);
		line01.getInnerBorder().setColor(Color.CYAN);
		signOptions.getOuterLines().add(line01);
		  
		    //Inner lines
		StampLine line02 = new StampLine();
		line02.setText("DEPARTURE");
		line02.setTextColor(Color.RED);
		line02.getFont().setFontSize(14);
		line02.setTextBottomIntent(10);
		line02.getFont().setBold(true);
		line02.setHeight(30);
		signOptions.getInnerLines().add(line02);
		  
		StampLine line03 = new StampLine();
		line03.setText("03.03.2003");
		line03.setTextColor(Color.GRAY);
		line03.getFont().setFontSize(12);
		line03.getFont().setBold(true);
		line03.setHeight(20);
		signOptions.getInnerLines().add(line03);
		  
		SaveOptions saveOptions =  new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		  
		    // sign document with round stamp
		String signedPath = (String)handler.<String>sign(fileName, signOptions,saveOptions);
		System.out.println("Signed file path is: "+signedPath);
		//change stamp type
		signOptions.setStampType(StampTypes.Square);
		  
		SaveOptions saveOptions2 =  new SaveOptions();
		saveOptions2.setOutputType(OutputType.String);
		saveOptions2.setOutputFileName("signed_output");
		    // sign document with square stamp
		String signedPath2 = (String)handler.<String>sign(fileName, signOptions, saveOptions2);
		//ExEnd:signImageWithStampSignatureUsingStampType
	}
	
	public static void setStampSignaturePositionOnCells(String fileName) throws Throwable{
		//ExStart:setStampSignaturePositionOnCells
		// setup Signature configuration 
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		// setup options with text of signature
	     CellsStampSignOptions signOptions = new CellsStampSignOptions();
	    signOptions.setHeight(120);
	    signOptions.setWidth(300);
	    signOptions.setTop(15);
	    signOptions.setLeft(22);
	  
	    //Inner square lines
	    StampLine line0 = new StampLine();
	    line0.setText("John");
	    line0.setTextBottomIntent(0);
	    line0.setTextColor(Color.RED);
	    line0.getOuterBorder().setColor(Color.BLUE);
	    line0.getInnerBorder().setColor(Color.BLUE);
	    line0.getInnerBorder().setStyle(ExtendedDashStyle.Dash);
	    line0.getFont().setFontSize(20);
	    line0.getFont().setBold(true);
	    line0.setHeight(40);
	    signOptions.getInnerLines().add(line0);
	  
	    StampLine line1 = new StampLine();
	    line1.setText("Smith");
	    line1.setTextBottomIntent(0);
	    line1.setTextColor(Color.RED);
	    line1.getInnerBorder().setColor(Color.BLUE);
	    line1.getFont().setFontSize(20);
	    line1.getFont().setBold(true);
	    line1.setHeight(40);
	    signOptions.getInnerLines().add(line1);
		// specify save options
		SaveOptions saveOptions = new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		// sign document
		String signedPath = handler.sign(fileName, signOptions, saveOptions);
		System.out.println("Signed file path is: " + signedPath);
		//ExEnd:setStampSignaturePositionOnCells
	}
	
	public static void signCellsWithStampMeasure(String fileName) throws Throwable{
		//ExStart:signCellsWithStampMeasure
		// setup Signature configuration 
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		CellsStampSignOptions signOptions = new CellsStampSignOptions();
	    signOptions.setWidth(150);
	    signOptions.setHeight(150);
	    signOptions.setHorizontalAlignment(HorizontalAlignment.Left);
	    signOptions.setVerticalAlignment(VerticalAlignment.Top);
	    signOptions.setBackgroundColor(java.awt.Color.BLUE);
	    signOptions.setBackgroundColorCropType(StampBackgroundCropType.None);
	    StampLine line1 = new StampLine();
	    line1.setText("John");
	    line1.setBackgroundColor(Color.BLUE);
	    line1.setTextColor(Color.CYAN);
	    signOptions.getOuterLines().add(line1);
	    // size
	    signOptions.setSizeMeasureType(MeasureType.Percents);
	    signOptions.setWidth(10);
	    signOptions.setHeight(10);	  
	    // position
	    // alignment
	    signOptions.setHorizontalAlignment(HorizontalAlignment.Center);
	    signOptions.setVerticalAlignment(VerticalAlignment.Top);	  
	    // margin
	    signOptions.setMarginMeasureType(MeasureType.Percents);
	    signOptions.getMargin().setTop(25);
	     
		// specify save options
		SaveOptions saveOptions = new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		// sign document
		String signedPath = handler.sign(fileName, signOptions, saveOptions);
		System.out.println("Signed file path is: " + signedPath);
		//ExEnd:signCellsWithStampMeasure
	}
	
	public static void signImagesWithRoundedCornersStampSignature(String fileName) throws Throwable{
		//ExStart:signImagesWithRoundedCornersStampSignature
		// setup Signature configuration 
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		// setup options
		ImagesStampSignOptions signOptions = new ImagesStampSignOptions();
		signOptions.setHeight(100);
		signOptions.setWidth(300);
		  
		signOptions.setBackgroundColorCropType(StampBackgroundCropType.OuterArea);
		  
		//Inner square lines
		StampLine line0 = new StampLine();
		line0.setText("PAID");
		line0.setTextColor(Color.WHITE);
		line0.getFont().setFontSize(32);
		line0.getFont().setBold(true);
		line0.setHeight(100);
		//Set radius of square corner
		line0.setOuterBorder(new SquareBorderLine(new Corners(25))); //This type is supported starting from version 19.5
		line0.getOuterBorder().setColor(Color.GRAY);
		line0.getOuterBorder().setWeight(2);
		line0.setBackgroundColor(Color.GRAY);
		signOptions.getInnerLines().add(line0);
		
		SaveOptions saveOptions = new  SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		// sign document
		String signedPath = handler.sign(fileName, signOptions, saveOptions);
		System.out.println("Signed file path is: " + signedPath);
		//ExEnd:signImagesWithRoundedCornersStampSignature
	}
}