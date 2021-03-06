package com.groupdocs.signature.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import com.groupdocs.signature.config.SignatureConfig;
import com.groupdocs.signature.domain.FileDescription;
import com.groupdocs.signature.domain.SearchResult;
import com.groupdocs.signature.domain.VerificationResult;
import com.groupdocs.signature.domain.enums.HorizontalAlignment;
import com.groupdocs.signature.domain.enums.MeasureType;
import com.groupdocs.signature.domain.enums.VerticalAlignment;
import com.groupdocs.signature.domain.signatures.BaseSignature;
import com.groupdocs.signature.domain.signatures.digital.CellsDigitalSignature;
import com.groupdocs.signature.domain.signatures.digital.PDFDigitalSignature;
import com.groupdocs.signature.domain.signatures.digital.WordsDigitalSignature;
import com.groupdocs.signature.handler.SignatureHandler;
import com.groupdocs.signature.options.OutputType;
import com.groupdocs.signature.options.VerifyOptions;
import com.groupdocs.signature.options.VerifyOptionsCollection;
import com.groupdocs.signature.options.digitalsearch.CellsSearchDigitalOptions;
import com.groupdocs.signature.options.digitalsearch.PdfSearchDigitalOptions;
import com.groupdocs.signature.options.digitalsearch.WordsSearchDigitalOptions;
import com.groupdocs.signature.options.digitalsignature.CellsSignDigitalOptions;
import com.groupdocs.signature.options.digitalsignature.PdfSignDigitalOptions;
import com.groupdocs.signature.options.digitalsignature.WordsSignDigitalOptions;
import com.groupdocs.signature.options.digitalverification.CellsVerifyDigitalOptions;
import com.groupdocs.signature.options.digitalverification.PDFVerifyDigitalOptions;
import com.groupdocs.signature.options.digitalverification.WordsVerifyDigitalOptions;
import com.groupdocs.signature.options.imagesignature.CellsSignImageOptions;
import com.groupdocs.signature.options.saveoptions.SaveOptions;

public class DigitalSignature {

	public static void signCellDocsWithDigitalSignature(String fileName) throws Throwable{
		//ExStart:signCellDocsWithDigitalSignature
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		// setup digital signature options
		InputStream certificateStream = new FileInputStream(CommonUtilities.getCertificatePath("acer.pfx"));
		CellsSignDigitalOptions signOptions = new CellsSignDigitalOptions(certificateStream);
		signOptions.getSignature().setComments("Test comment");
		signOptions.getSignature().setSignTime(new Date(2017, 1, 25, 10, 41, 54));
		signOptions.setPassword("1234567890");
		final SaveOptions saveOptions = new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output.xlsx");
		// sign document
		String signedPath = handler.<String>sign(CommonUtilities.getStoragePath(fileName), signOptions, saveOptions);
		//File stream must be closed after signing
		certificateStream.close();
		System.out.println("Signed file path is: " + signedPath);
		//ExEnd:signCellDocsWithDigitalSignature
	}
	
	public static void signPdfDocsWithDigitalSignature(String fileName) throws Throwable{
		//ExStart:signPdfDocsWithDigitalSignature
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		// setup digital signature options
		//PdfSignDigitalOptions signOptions = new PdfSignDigitalOptions(new FileDescription(CommonUtilities.getCertificatePath("acer.pfx")), CommonUtilities.getImagesPath("sign.png"));
		PdfSignDigitalOptions signOptions = new PdfSignDigitalOptions(CommonUtilities.getCertificatePath("acer.pfx"), CommonUtilities.getImagesPath("sign.png"));
		signOptions.setReason("Test reason");
		signOptions.setContact("Test contact");
		signOptions.setLocation("Test location");
		//signOptions.setPassword("1234567890");
		// image position
		signOptions.setLeft(100);
		signOptions.setTop(100);
		signOptions.setWidth(100);
		signOptions.setHeight(100);
		signOptions.setDocumentPageNumber(1);
		final SaveOptions saveOptions = new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output.pdf");
		// sign document
		String signedPath = handler.<String>sign(CommonUtilities.getStoragePath(fileName), signOptions, saveOptions);
		System.out.println("Signed file path is: " + signedPath);
		//ExEnd:signPdfDocsWithDigitalSignature
	}

	public static void signSlideDocsWithDigitalSignature(String fileName){
	//ExStart:signSlideDocsWithDigitalSignature
	//This feature will be added in future release
	//ExEnd:signSlideDocsWithDigitalSignature
	}

	public static void signWordDocsWithDigitalSignature(String fileName) throws Exception{
	//ExStart:signWordDocsWithDigitalSignature
	// setup Signature configuration
	SignatureConfig signConfig = CommonUtilities.getConfiguration();
	// instantiating the conversion handler
	SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
	// setup digital signature options
	WordsSignDigitalOptions signOptions = new WordsSignDigitalOptions(CommonUtilities.getCertificatePath("acer.pfx"));
	signOptions.getSignature().setComments("Test comment");
	signOptions.getSignature().setSignTime(new Date(2017, 1, 25, 10, 41, 54));
	//signOptions.setPassword("1234567890");
	final SaveOptions saveOptions = new SaveOptions();
	saveOptions.setOutputType(OutputType.String);
	saveOptions.setOutputFileName("signed_output.docx");
	// sign document
	String signedPath = handler.<String>sign(CommonUtilities.getStoragePath(fileName), signOptions, saveOptions);
	System.out.println("Signed file path is: " + signedPath);
	//ExEnd:signWordDocsWithDigitalSignature
	}
	
	public static void signCellDocsWithCertificateContainer(String fileName) throws Exception{
	//ExStart:signCellDocsWithDigitalSignature
	// setup Signature configuration
	SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
	// instantiating the conversion handler
	SignatureHandler handler = new SignatureHandler(signConfig);
	// setup digital verification options
	CellsVerifyDigitalOptions verifyOptions = new CellsVerifyDigitalOptions(CommonUtilities.getCertificatePath("certificate.cer"));
	verifyOptions.setComments("Test1");
	verifyOptions.setSignDateTimeFrom(new Date(2017, 1, 26, 14, 55, 7));
	verifyOptions.setSignDateTimeTo(new Date(2017, 1, 26, 14, 55, 9));
	//verify document
	VerificationResult result = handler.verify(CommonUtilities.getStoragePath("pie chart.xlsx"), verifyOptions);
	System.out.println("Signed file verification result: " + result.isValid());
	//ExStart:signCellDocsWithDigitalSignature
	}
	
	public static void signCellDocsWithPfxCertificateContainer(String fileName) throws Exception{
		//ExStart:signCellDocsWithPfxCertificateContainer
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler handler = new SignatureHandler(signConfig);
		// setup digital verification options
		CellsVerifyDigitalOptions verifyOptions1 = new CellsVerifyDigitalOptions(CommonUtilities.getCertificatePath("acer.pfx"));
		//password is needed to open .pfx certificate
		verifyOptions1.setPassword("1234567890");
		CellsVerifyDigitalOptions verifyOptions2 = new CellsVerifyDigitalOptions(CommonUtilities.getCertificatePath("certificate.cer"));
		//VerifyOptionsCollection verifyOptionsCollection = new VerifyOptionsCollection(verifyOptions1, verifyOptions2);
		List<VerifyOptions> listOptions = new ArrayList<VerifyOptions>();
		listOptions.add(verifyOptions1);
		listOptions.add(verifyOptions2);
	    VerifyOptionsCollection verifyOptionsCollection =
	        new VerifyOptionsCollection(listOptions);
		//verify document
		VerificationResult result = handler.verify(CommonUtilities.getStoragePath(fileName), verifyOptionsCollection);
		System.out.println("Signed file verification result: " + result.isValid());
		//ExEnd:signCellDocsWithPfxCertificateContainer
	}

	public static void signPdfDocsWithCertificateContainer(String fileName) throws Exception{
		//ExStart:signPdfDocsWithCertificateContainer
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler handler = new SignatureHandler(signConfig);
		// setup digital verification options
		PDFVerifyDigitalOptions verifyOptions = new PDFVerifyDigitalOptions(CommonUtilities.getCertificatePath("certificate.cer"));
		verifyOptions.setReason("Test reason");
		verifyOptions.setContact("Test contact");
		verifyOptions.setLocation("Test location");
		//verify document
		VerificationResult result = handler.verify(CommonUtilities.getStoragePath(fileName), verifyOptions);
		System.out.println("Signed file verification result: " + result.isValid());
		//ExEnd:signPdfDocsWithCertificateContainer
	}

	public static void signPdfDocsWithPfxCertificateContainer(String fileName) throws Exception{
		//ExStart:signPdfDocsWithPfxCertificateContainer
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler handler = new SignatureHandler(signConfig);
		// setup digital verification options
		PDFVerifyDigitalOptions verifyOptions1 = new PDFVerifyDigitalOptions(CommonUtilities.getCertificatePath("acer.pfx"));
		//password is needed to open .pfx certificate
		verifyOptions1.setPassword("1234567890");
		PDFVerifyDigitalOptions verifyOptions2 = new PDFVerifyDigitalOptions(CommonUtilities.getCertificatePath("certificate.cer"));
		
		List<VerifyOptions> listOptions = new ArrayList<VerifyOptions>();
		listOptions.add(verifyOptions1);
		listOptions.add(verifyOptions2);
	    VerifyOptionsCollection verifyOptionsCollection =
	        new VerifyOptionsCollection(listOptions);
		//verify document
		VerificationResult result = handler.verify(CommonUtilities.getStoragePath(fileName), verifyOptionsCollection);
		System.out.println("Signed file verification result: " + result.isValid());
		//ExEnd:signPdfDocsWithPfxCertificateContainer
	}

	public static void digitalVerificationOfSlideDoc(){
		//Feature will be available in new release of the API
	}

	public static void digitalVerificationOfWordDocWithCertificateContainer(String fileName) throws Exception{
		//ExStart:digitalVerificationOfWordDocWithCertificateContainer
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler handler = new SignatureHandler(signConfig);
		 
		VerifyOptionsCollection verifyOptionsCollection = new VerifyOptionsCollection();
		// setup digital verification options
		WordsVerifyDigitalOptions verifyOptions = new WordsVerifyDigitalOptions(CommonUtilities.getCertificatePath("certificate.cer"));
		verifyOptions.setComments("Test1");
		verifyOptions.setSignDateTimeFrom(new Date(2017, 1, 26, 14, 55, 57));
		verifyOptions.setSignDateTimeTo(new Date(2017, 1, 26, 14, 55, 59));
		
		//verify document
		VerificationResult result = handler.verify(CommonUtilities.getStoragePath(fileName), verifyOptions);
		System.out.println("Signed file verification result: " + result.isValid());
		//ExEnd:digitalVerificationOfWordDocWithCertificateContainer
	}

	public static void digitalVerificationOfWordDocWithPfcCertificateContainer(String fileName) throws Exception{
		//ExStart:digitalVerificationOfWordDocWithPfcCertificateContainer
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler handler = new SignatureHandler(signConfig);
		// setup digital verification options
		WordsVerifyDigitalOptions verifyOptions1 = new WordsVerifyDigitalOptions(CommonUtilities.getCertificatePath("acer"));
		//password is needed to open .pfx certificate
		verifyOptions1.setPassword("1234567890");
		WordsVerifyDigitalOptions verifyOptions2 = new WordsVerifyDigitalOptions(CommonUtilities.getCertificatePath("certificate.cer"));
		
		List<VerifyOptions> listOptions = new ArrayList<VerifyOptions>();
		listOptions.add(verifyOptions1);
		listOptions.add(verifyOptions2);
	    VerifyOptionsCollection verifyOptionsCollection =
	        new VerifyOptionsCollection(listOptions);
		//verify document
		VerificationResult result = handler.verify(CommonUtilities.getStoragePath(fileName), verifyOptionsCollection);
		System.out.println("Signed file verification result: " + result.isValid());
		//ExEnd:digitalVerificationOfWordDocWithPfcCertificateContainer
	}
	
	public static void searchDigitalSignatureInPDFDocuments(String fileName) throws Exception{
		//ExStart:searchDigitalSignatureInPDFDocuments
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the signature handler          
		SignatureHandler handler = new SignatureHandler(signConfig);		  
		// setup options with text of signature
		PdfSearchDigitalOptions searchOptions = new PdfSearchDigitalOptions();
		// Search Document for Signatures
		SearchResult searchResult = handler.search(fileName, searchOptions);
		System.out.println("Source file " +fileName+ " contains "+searchResult.getSignatures().size()+" digital signature(s)" );
		for(BaseSignature signature : searchResult.getSignatures())
		{
		    PDFDigitalSignature pdfSign = (PDFDigitalSignature)signature;
		    if (pdfSign != null) {
		        System.out.println("\t >> Digital signature from "+pdfSign.getSignTime()+". Contact: "+pdfSign.getContactInfo()+". Valid "+pdfSign.isValid());
		    }
		}
		//ExEnd:searchDigitalSignatureInPDFDocuments
	}
	
	public static void searchDigitalSignatureInCellDocuments(String fileName) throws Exception{
		//ExStart:searchDigitalSignatureInCellDocuments
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the signature handler          
		SignatureHandler handler = new SignatureHandler(signConfig);		  
		// setup options with text of signature
		CellsSearchDigitalOptions  searchOptions = new CellsSearchDigitalOptions ();
		// Search Document for Signatures
		SearchResult searchResult = handler.search(fileName, searchOptions);
		System.out.println("Source file " +fileName+ " contains "+searchResult.getSignatures().size()+" digital signature(s)" );
		for(BaseSignature signature : searchResult.getSignatures())
		{
		    CellsDigitalSignature cellsSign = (CellsDigitalSignature )signature;
		    if (cellsSign != null) {
		        System.out.println("\t >> Digital signature from "+cellsSign.getSignTime()+". Comments: "+cellsSign.getComments()+". Valid "+cellsSign.isValid());
		    }
		}
		//ExEnd:searchDigitalSignatureInCellDocuments
	}
	
	public static void searchDigitalSignatureInWordDocuments(String fileName) throws Exception{
		//ExStart:searchDigitalSignatureInWordDocuments
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the signature handler          
		SignatureHandler handler = new SignatureHandler(signConfig);		  
		// setup options with text of signature
		WordsSearchDigitalOptions searchOptions = new WordsSearchDigitalOptions();
		// Search Document for Signatures
		SearchResult searchResult = handler.search(fileName, searchOptions);
		System.out.println("Source file " +fileName+ " contains "+searchResult.getSignatures().size()+" digital signature(s)" );
		for(BaseSignature signature : searchResult.getSignatures())
		{
		    WordsDigitalSignature WordsSign = (WordsDigitalSignature)signature;
		    if (WordsSign != null) {
		        System.out.println("\t >> Digital signature from "+WordsSign.getSignTime()+". Comments: "+WordsSign.getComments()+". Valid "+WordsSign.isValid());
		    }
		}
		//ExEnd:searchDigitalSignatureInWordDocuments
	}
	
	public static void searchDigitalSignatureInSystem() throws Exception{
		//ExStart:searchDigitalSignatureInSystem
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// load Digital Signature registered in system
		List<com.groupdocs.signature.domain.signatures.digital.DigitalSignature> signatures = com.groupdocs.signature.domain.signatures.digital.DigitalSignature.loadDigitalSignatures();
		for (com.groupdocs.signature.domain.signatures.digital.DigitalSignature signature : signatures)
		{
		    if (signature.getCertificate() != null)
		    {
		  
		        KeyStore keyStore = signature.getCertificate();
		        for (Enumeration l = keyStore.aliases(); l.hasMoreElements();) {
		            String al = (String) l.nextElement();
		            Certificate cert = keyStore.getCertificate(al);
		  
		            System.out.println("\nCertificate: " + cert.toString());
		  
		        }   
		    }
		}
		//ExEnd:searchDigitalSignatureInSystem
	}
	
	public static void setDigitalSignaturePositionOnCells(String fileName) throws Throwable{
		//ExStart:setDigitalSignaturePositionOnCells
		// setup Signature configuration 
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		// Specify Signature Options 
		CellsSignDigitalOptions signOptions = new CellsSignDigitalOptions(CommonUtilities.getCertificatePath("acer.pfx"), CommonUtilities.getImagesPath("sign.png"));
		//signOptions.setPassword("1234567890");
		signOptions.setWidth(200);
		signOptions.setHeight(200);
		signOptions.setTop(15);
		signOptions.setLeft(22);
		// specify save options
		SaveOptions saveOptions = new SaveOptions();
		saveOptions.setOutputType(OutputType.String);
		saveOptions.setOutputFileName("signed_output");
		// sign document
		String signedPath = handler.sign(fileName, signOptions, saveOptions);
		System.out.println("Signed file path is: " + signedPath);
		//ExEnd:setDigitalSignaturePositionOnCells
	}
	
	public static void searchDigitalSignatureInWordsWithExtendedOption(String fileName) throws Exception{
		//ExStart:searchDigitalSignatureInWordsWithExtendedOption
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the signature handler          
		SignatureHandler handler = new SignatureHandler(signConfig);		  
		// setup options with text of signature
		WordsSearchDigitalOptions searchOptions = new WordsSearchDigitalOptions();
		// setup additional search criteria
		searchOptions.setComments("test comments");
		searchOptions.setSignDateTimeFrom(new Date(new Date().getYear(), 1, 1));
		searchOptions.setIssuerName("John");
		// Search Document for Signatures
		SearchResult searchResult = handler.search(fileName, searchOptions);
		System.out.print("Source file "+fileName+" contains "+searchResult.getSignatures().size()+" digital signature(s)");
		for(BaseSignature signature : searchResult.getSignatures())
		{
		 WordsDigitalSignature WordsSign = (WordsDigitalSignature)signature ;
		    if (WordsSign != null)
		    {
		        System.out.print("\t >> Digital signature from "+ WordsSign.getSignTime()+". Comments: "+WordsSign.getComments()+". Valid "+WordsSign.isValid() );
		    }
		}
		//ExEnd:searchDigitalSignatureInWordsWithExtendedOption
	}
	
	public static void searchDigitalSignatureInCellsWithExtendedOption(String fileName) throws Exception{
		//ExStart:searchDigitalSignatureInCellsWithExtendedOption
		// setup Signature configuration
		SignatureConfig signConfig = CommonUtilities.getConfiguration();
		// instantiating the signature handler          
		SignatureHandler handler = new SignatureHandler(signConfig);		  
		// setup options with text of signature
		CellsSearchDigitalOptions searchOptions = new CellsSearchDigitalOptions();
		// setup additional options
		searchOptions.setComments("test comment");            
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date dateFrom = sdf.parse("26/1/2017 14:55:07");        
		searchOptions.setSignDateTimeFrom(dateFrom);
		// Search Document for Signatures
		SearchResult searchResult = handler.search(fileName, searchOptions);
		System.out.print("Source file "+ fileName+" contains "+searchResult.getSignatures().size()+" digital signature(s)" );
		for(BaseSignature signature : searchResult.getSignatures())
		{
		    CellsDigitalSignature cellsSign = (CellsDigitalSignature)signature ;
		    if (cellsSign != null)
		    {
		        System.out.print("\t >> Digital signature from "+cellsSign.getSignTime()+". Comments: "+cellsSign.getComments()+". Valid " + cellsSign.isValid() );
		    }
		}
		//ExEnd:searchDigitalSignatureInCellsWithExtendedOption
	}
	
	public static void signCellsWithDigitalSignatureMeasure(String fileName) throws Throwable{
		//ExStart:signCellsWithDigitalSignatureMeasure
		// setup Signature configuration 
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
		// Specify Signature Options 
		CellsSignDigitalOptions signOptions = new CellsSignDigitalOptions(CommonUtilities.getCertificatePath("acer.pfx"), CommonUtilities.getImagesPath("sign.png"));
		//signOptions.setPassword("1234567890");
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
		//ExEnd:signCellsWithDigitalSignatureMeasure
	}
	
	public static void digitalVerificationOfWordsDocumentWithExtendedProperties(String fileName) throws Throwable{
		//ExStart:digitalVerificationOfWordsDocumentWithExtendedProperties
		// setup Signature configuration 
		SignatureConfig signConfig = CommonUtilities.getConfiguration(); 
		// instantiating the conversion handler
		SignatureHandler<String> handler = new SignatureHandler<String>(signConfig);
	    // setup digital verification options
		WordsVerifyDigitalOptions verifyOptions = new WordsVerifyDigitalOptions("SherlockHolmes.cer");
		verifyOptions.setComments("Test1");
		verifyOptions.setSubjectName("Signature");
		verifyOptions.setIssuerName("GroupDocs");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date dateFrom = sdf.parse("26/1/2017 16:55:57");
		Date dateTo = sdf.parse("26/1/2017 16:55:59");
		
		verifyOptions.setSignDateTimeFrom(dateFrom);
		verifyOptions.setSignDateTimeTo(dateTo);
		//verify document
		VerificationResult result = handler.verify(fileName, verifyOptions);
		System.out.print("Signed file verification result: " + result.isValid());
		//ExEnd:digitalVerificationOfWordsDocumentWithExtendedProperties
	}
}