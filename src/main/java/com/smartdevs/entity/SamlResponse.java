package com.smartdevs.entity;

public class SamlResponse extends Response {
        private String result;
        private CodingType codingType;
        private BindingFormat bindingFormat;
        public SamlResponse() {
        }

        public static SamlResponse encoded( String result, BindingFormat bindingFormat ) {
                SamlResponse samlResponse = new SamlResponse();
                samlResponse.setCodingType( CodingType.ENCODED );
                samlResponse.setBindingFormat( bindingFormat );
                samlResponse.setResult( result );
                return samlResponse;
        }

        public static SamlResponse decoded( String result, BindingFormat bindingFormat ) {
                SamlResponse samlResponse = new SamlResponse();
                samlResponse.setCodingType( CodingType.DECODED );
                samlResponse.setBindingFormat( bindingFormat );
                samlResponse.setResult( result );
                return samlResponse;
        }

        public static SamlResponse error( String errorMessage ) {
                SamlResponse samlResponse = new SamlResponse();
                samlResponse.setCodingType(CodingType.ERROR);
                samlResponse.setMessage(errorMessage);
                return samlResponse;
        }

        public String getResult() {
                return result;
        }

        public void setResult( String result ) {
                this.result = result;
        }

        public CodingType getCodingType() {
                return codingType;
        }

        public void setCodingType( CodingType codingType ) {
                this.codingType = codingType;
        }

        public BindingFormat getBindingFormat() {
                return bindingFormat;
        }

        public void setBindingFormat( BindingFormat bindingFormat ) {
                this.bindingFormat = bindingFormat;
        }

        public enum CodingType {
                ENCODED, DECODED, ERROR
        }

        public enum BindingFormat {
                REDIRECT, POST
        }
}
