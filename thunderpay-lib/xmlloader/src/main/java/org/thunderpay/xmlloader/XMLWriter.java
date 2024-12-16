/**
 * @file XMLWriter
 * @author Krisna Pranav
 * @brief XML Writer
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.xmlloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import static java.nio.charset.StandardCharsets.UTF_8;
public class XMLWriter<T> {
    private static final int MAX_XML_SIZE_IN_BYTES = 100000;

    public static<T> String writeXML(final T object, final Class<T> type) throws IOException {
        final JAXBContext context = JAXBContext.newInstance(type);
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final ByteArrayOutputStream output = new ByteArrayOutputStream(MAX_XML_SIZE_IN_BYTES);

        marshaller.marshal(object, output);

        return new String(output.toByteArray(), UTF_8);
    }

}
