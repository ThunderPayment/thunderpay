/**
 * @file XMLSchemaGenerator
 * @author Krisna Pranav
 * @brief XML Schema Generator
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.xmlloader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import static java.nio.charset.StandardCharsets.UTF_8;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.SchemaOutputResolver;

public class XMLSchemaGenerator {
    private static final int MAX_SCHEMA_SIZE_IN_BYTES = 100000;

    public static void main(final String[] args) throws IOException, TransformerException, JAXBException, ClassNotFoundException {
        if (args.length != 2) {
            printUsage();
            System.exit(0);
        }
        final Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(args[1]);

        final JAXBContext context = JAXBContext.newInstance(clazz);
        String xsdFileName = "Schema.xsd";
        xsdFileName = args[0] + "/" + xsdFileName;
        final FileOutputStream s = new FileOutputStream(xsdFileName);
        pojoToXSD(context, s);
    }

    private static void printUsage() {
        System.out.println(XMLSchemaGenerator.class.getName() + " <file> <class1>");

    }

    public static String xmlSchemaAsString(final Class<?> clazz) throws IOException, TransformerException, JAXBException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream(MAX_SCHEMA_SIZE_IN_BYTES);
        final JAXBContext context = JAXBContext.newInstance(clazz);
        pojoToXSD(context, output);
        return new String(output.toByteArray(), UTF_8);
    }

    public static InputStream xmlSchema(final Class<?> clazz) throws IOException, TransformerException, JAXBException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream(MAX_SCHEMA_SIZE_IN_BYTES);
        final JAXBContext context = JAXBContext.newInstance(clazz);
        pojoToXSD(context, output);
        return new ByteArrayInputStream(output.toByteArray());
    }

    public static void pojoToXSD(final JAXBContext context, final OutputStream out)
            throws IOException, TransformerException {
        final List<DOMResult> results = new ArrayList<DOMResult>();

        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(final String ns, final String file)
                    throws IOException {
                final DOMResult result = new DOMResult();
                result.setSystemId(file);
                results.add(result);
                return result;
            }
        });

        final DOMResult domResult = results.get(0);
        final Document doc = (Document) domResult.getNode();

        final TransformerFactory tFactory = TransformerFactory.newInstance();
        final Transformer transformer = tFactory.newTransformer();

        final DOMSource source = new DOMSource(doc);
        final StreamResult result = new StreamResult(out);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

}