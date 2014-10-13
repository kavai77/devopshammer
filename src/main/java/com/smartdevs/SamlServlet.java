package com.smartdevs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.smartdevs.engine.SamlEngine;
import com.smartdevs.entity.SamlResponse;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.PrintWriter;

@Singleton
public class SamlServlet extends HttpServlet {
        @Inject SamlEngine samlEngine;

        @Override protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
                String saml = IOUtils.toString( req.getReader() );
                SamlResponse samlResponse = samlEngine.decodeOrEncode( saml );
                encodeResponse( resp, samlResponse );
        }

        private void encodeResponse( HttpServletResponse resp, SamlResponse samlResponse ) throws IOException {
                resp.setContentType( MediaType.APPLICATION_JSON );
                ObjectMapper mapper = new ObjectMapper();
                PrintWriter writer = resp.getWriter();
                mapper.writeValue( writer, samlResponse );
                writer.close();
        }
}
