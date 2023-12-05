package com.webull.gold.util;

import com.webull.gold.model.NoteVO;

import com.openhtmltopdf.bidi.support.ICUBidiReorderer;
import com.openhtmltopdf.bidi.support.ICUBidiSplitter;
import com.openhtmltopdf.latexsupport.LaTeXDOMMutator;
import com.openhtmltopdf.mathmlsupport.MathMLDrawer;
import com.openhtmltopdf.objects.StandardObjectDrawerFactory;
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder.TextDirection;
import com.openhtmltopdf.pdfboxout.PdfBoxRenderer;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.svgsupport.BatikSVGDrawer;
import com.openhtmltopdf.swing.NaiveUserAgent.DefaultUriResolver;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.Locale;

public class PDFHelper {

    /**
     * html的根路径，用来找html中的图片等
     */
    private static final String BASE_DOCUMENT_URI = "/templates";

    public static final String TEMPLATE_NAME = "NoteStatement.ftl";

    /**
     * 最大字节
     */
    private static final long MAX_MAIN_MEMORY_BYTES = 5 * 1024L * 1024L;

    /**
     * 生成 PDF 文件
     *
     * @param noteVO
     */
    public static byte[] buildContractNotePdf(NoteVO noteVO) {
        String html = PDFHelper.generateHtml(TEMPLATE_NAME, noteVO);
        return generatePDF(html, null);
    }

    /**
     * 生成pdf
     *
     * @param html html
     * @return byte[]
     */
    private static byte[] generatePDF(String html, String pwd) {
        html = html.replaceAll("[\\n\\t\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
        PdfRendererBuilder builder = getPdfRendererBuilder();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            builder.toStream(outputStream);
            builder.withHtmlContent(html, BASE_DOCUMENT_URI);
            try (PdfBoxRenderer pdfBoxRenderer = builder.buildPdfRenderer()) {
                if (StringUtils.isNotBlank(pwd)) {
                    AccessPermission permission = new AccessPermission();
                    permission.setCanAssembleDocument(false);
                    permission.setCanExtractContent(true);
                    permission.setCanExtractForAccessibility(true);
                    permission.setCanFillInForm(true);
                    permission.setCanModify(false);
                    permission.setCanModifyAnnotations(true);
                    permission.setCanPrint(true);
                    permission.setCanPrintDegraded(true);
                    permission.setReadOnly();
                    PDDocument doc = pdfBoxRenderer.getPdfDocument();
                    doc.protect(new StandardProtectionPolicy(pwd, pwd, permission));
                }
                pdfBoxRenderer.layout();
                pdfBoxRenderer.createPDF();
            }
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建PdfRendererBuilder
     *
     * @return PdfRendererBuilder
     */
    private static PdfRendererBuilder getPdfRendererBuilder() {
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useUnicodeBidiSplitter(new ICUBidiSplitter.ICUBidiSplitterFactory());
        builder.useUnicodeBidiReorderer(new ICUBidiReorderer());
        builder.defaultTextDirection(TextDirection.LTR);
        builder.useSVGDrawer(new BatikSVGDrawer());
        builder.useMathMLDrawer(new MathMLDrawer());
        builder.addDOMMutator(LaTeXDOMMutator.INSTANCE);
        builder.usePDDocument(new PDDocument(MemoryUsageSetting.setupMixed(MAX_MAIN_MEMORY_BYTES)));
        builder.useUriResolver(new DefaultUriResolver() {
            @Override
            public String resolveURI(String baseUri, String uri) {
                if (!uri.startsWith(File.separator)) {
                    // Classpath Resource
                    URL resource = PDFHelper.class.getResource(uri);
                    if (resource != null) {
                        return resource.toString();
                    }
                    resource = PDFHelper.class.getResource(baseUri + File.separator + uri);
                    if (resource != null) {
                        return resource.toString();
                    }
                }
                return super.resolveURI(baseUri, uri);
            }
        });
        StandardObjectDrawerFactory objectDrawerFactory = new StandardObjectDrawerFactory();
        builder.useObjectDrawerFactory(objectDrawerFactory);
        return builder;
    }

    private static String generateHtml(String templateName, Object model) {
        try {
            Template template = freemarkerConfiguration().getTemplate(templateName, "UTF-8");
            StringWriter writer = new StringWriter();
            template.process(model, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Configuration freemarkerConfiguration() {
        Version ourVersion = Configuration.VERSION_2_3_28;
        Configuration cfg = new Configuration(ourVersion);
        cfg.setObjectWrapper(new DefaultObjectWrapper(ourVersion));
        cfg.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setOutputEncoding("UTF-8");
        cfg.setLocale(Locale.ENGLISH);
        //允许html变量为null
        cfg.setClassicCompatible(true);
        cfg.setTemplateLoader(new ClassTemplateLoader(PDFHelper.class, "/templates"));
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }

}


