package com.example.mainservice.exceptions;

import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.namespace.QName;

public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

    private static final QName CODE = new QName("code");
    private static final QName DESCRIPTION = new QName("description");
    private static final QName TIME = new QName("time");

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        logger.warn("Exception processed ", ex);
        if (ex instanceof ServiceFaultException) {
            ServiceFault serviceFault = ((ServiceFaultException) ex).getServiceFault();
            SoapFaultDetail detail = fault.addFaultDetail();
            detail.addFaultDetailElement(CODE).addText(serviceFault.getCode());
            detail.addFaultDetailElement(DESCRIPTION).addText(serviceFault.getMessage());
            detail.addFaultDetailElement(TIME).addText(serviceFault.getTime());
        }
    }

}