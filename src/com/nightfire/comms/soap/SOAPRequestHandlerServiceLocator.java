/**
 * SOAPRequestHandlerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.nightfire.comms.soap;

public class SOAPRequestHandlerServiceLocator extends org.apache.axis.client.Service implements com.nightfire.comms.soap.SOAPRequestHandlerService {

    // Use to get a proxy class for SOAPRequestHandler
    private final java.lang.String SOAPRequestHandler_address = "http://localhost:8080/axis/services/SOAPRequestHandler";

    public java.lang.String getSOAPRequestHandlerAddress() {
        return SOAPRequestHandler_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SOAPRequestHandlerWSDDServiceName = "SOAPRequestHandler";

    public java.lang.String getSOAPRequestHandlerWSDDServiceName() {
        return SOAPRequestHandlerWSDDServiceName;
    }

    public void setSOAPRequestHandlerWSDDServiceName(java.lang.String name) {
        SOAPRequestHandlerWSDDServiceName = name;
    }

    public com.nightfire.comms.soap.SOAPRequestHandler getSOAPRequestHandler() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SOAPRequestHandler_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSOAPRequestHandler(endpoint);
    }

    public com.nightfire.comms.soap.SOAPRequestHandler getSOAPRequestHandler(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.nightfire.comms.soap.SOAPRequestHandlerSoapBindingStub _stub = new com.nightfire.comms.soap.SOAPRequestHandlerSoapBindingStub(portAddress, this);
            _stub.setPortName(getSOAPRequestHandlerWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.nightfire.comms.soap.SOAPRequestHandler.class.isAssignableFrom(serviceEndpointInterface)) {
                com.nightfire.comms.soap.SOAPRequestHandlerSoapBindingStub _stub = new com.nightfire.comms.soap.SOAPRequestHandlerSoapBindingStub(new java.net.URL(SOAPRequestHandler_address), this);
                _stub.setPortName(getSOAPRequestHandlerWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("SOAPRequestHandler".equals(inputPortName)) {
            return getSOAPRequestHandler();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.neustar.biz/clearinghouse/SOAPRequestHandler/1.0", "SOAPRequestHandlerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("SOAPRequestHandler"));
        }
        return ports.iterator();
    }

}
