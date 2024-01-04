package blockchain.entrepreneur.cuisine.pdf.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

@Service
public class PdfGenerationService {
	
	private TemplateEngine templateEngine;

	public PdfGenerationService(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
	
	
    public void generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName) {
        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process(templateName, context);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Mathieu\\Desktop\\Cuisine\\" + pdfFileName);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(fileOutputStream, false);
            renderer.finishPDF();
            System.out.println("fin génération pdf");
        } catch (FileNotFoundException e) {System.out.println("file not found");
        } catch (DocumentException e) {System.out.println("Document exception");
        }
    }
}
