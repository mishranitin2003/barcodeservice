package org.restful.barcode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BarcodeController {

	private static final Logger logger = LoggerFactory.getLogger(BarcodeController.class);

	private static Map<String, BarcodeFormat> FORMAT_MAP = 
			new HashMap<String, BarcodeFormat>(){
		{
			put("code128", BarcodeFormat.CODE_128);
			put("qrcode", BarcodeFormat.QR_CODE);
			put("code39", BarcodeFormat.CODE_39);
		}
	};
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/{format}/{data}", method = RequestMethod.GET)
	public ModelAndView generateBarcode(@PathVariable("format") String format,
			@PathVariable("data") String data, HttpServletRequest request) {
		BarcodeFormat barcodeFormat = FORMAT_MAP.get(format);
		if(barcodeFormat == null) {
			throw new RuntimeException("Barcode format " + format + " is incorrect");
		}

		ModelAndView mv = new ModelAndView("barcode");
		ModelMap model = new ModelMap();
		
		model.put("barcodeUrl", "barcode/"+format+"/"+data);
		model.put("barcodeText", data);
		mv.addAllObjects(model);
		
		return mv;
	}
	

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/barcode/{format}/{data}", method = RequestMethod.GET)
	@ResponseBody
	public void barcode(@PathVariable("format") String format,
			@PathVariable("data") String data, HttpServletResponse response) {
		BarcodeFormat barcodeFormat = FORMAT_MAP.get(format);

		try {
			BitMatrix matrix = new MultiFormatWriter().encode(data, barcodeFormat, 300, 300);

			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write( bufferedImage  , "png", byteArrayOutputStream);

			response.setContentType("image/png");
			response.getOutputStream().write(byteArrayOutputStream.toByteArray());
		} catch (Exception e) {
			logger.error("Error occurred while translating to barcode", e);
			throw new RuntimeException(e);
		}

	}
	
	@ExceptionHandler(Exception.class)
	public String handleIOException(Exception ex, HttpServletRequest request) {
		return "error";
	}

}
