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

public class XMLSchemaGenerator {
    private static final int MAX_SCHEMA_SIZE_IN_BYTES = 100000;

    public static void main(final String[] args) throws IOException {
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
    
}
