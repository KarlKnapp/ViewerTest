package viewertest;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;

@Named
@SessionScoped
public class Viewer implements Serializable {

    private static final long serialVersionUID = 1L;

    private StreamedContent content, contentA, contentB;

    @PostConstruct
    public void init() {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            for (int i = 0; i < 40; i++) {
                document.add(new Paragraph("Content A"));
            }
            document.close();
            contentA = new ByteArrayContent(out.toByteArray(), "application/pdf");

            out = new ByteArrayOutputStream();
            document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            for (int i = 0; i < 40; i++) {
                document.add(new Paragraph("Content B"));
            }
            document.close();
            contentB = new ByteArrayContent(out.toByteArray(), "application/pdf");

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void selectContentA() {
        content = contentA;
    }

    public void selectContentB() {
        content = contentB;
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

}
