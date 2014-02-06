package org.restful.barcode;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:org/restful/barcode/controller-test-context.xml"})
@WebAppConfiguration
public class BarcodeControllerTest {

	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	@Test
	public void testGeneratedBarcodeCode128Decoding() throws Exception {
		String barcodeValue = "AA1234566";
		MvcResult mvcResult = mockMvc.perform(get("/barcode/code128/" + barcodeValue))
		.andExpect(status().isOk()).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		byte[] barcodeBytes = response.getContentAsByteArray();
	    
	    InputStream is = new ByteArrayInputStream(barcodeBytes);
	    Map<DecodeHintType, Boolean> hintMap = new HashMap<DecodeHintType, Boolean>();
	    hintMap.put(DecodeHintType.TRY_HARDER, true);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(is))));
		Result barcodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		assertEquals(barcodeValue, barcodeResult.getText());
	}
}
