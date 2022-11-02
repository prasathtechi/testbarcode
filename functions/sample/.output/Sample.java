import java.util.logging.Logger;
import java.io.OutputStream;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.catalyst.advanced.CatalystAdvancedIOHandler;
import com.zc.common.ZCProject;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class Sample implements CatalystAdvancedIOHandler {
	private static final Logger LOGGER = Logger.getLogger(Sample.class.getName());
	
	@Override
    public void runner(HttpServletRequest request, HttpServletResponse response) throws Exception {

		LOGGER.info("starting");
		ZCProject.initProject();
		
		if(request.getParameter("code-block")!=null)
		{
			LOGGER.info("code block not null");
			String text=request.getParameter("code-block");
			response.setContentType("image/jpeg");
			OutputStream os =response.getOutputStream(); 
			
			if(request.getParameter("type").equalsIgnoreCase("code128"))
			{
				LOGGER.info("code128 starting");
			 Barcode barcode=BarcodeFactory.createCode128(text);
			 barcode.setPreferredBarHeight(100); 
			 barcode.setLabel(text);
			 BarcodeImageHandler.writeJPEG(barcode, os);
			 LOGGER.info("code128 Barcode created");
			}
			
			  os.close(); 
		 
	  }
	}
	
}