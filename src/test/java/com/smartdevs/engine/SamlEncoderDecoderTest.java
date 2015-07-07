package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SamlEncoderDecoderTest {
    private SamlEncoder samlEncoder = new SamlEncoder();
    private SamlDecoder samlDecoder = new SamlDecoder();

    private String decoded;
    private String postEncoded;
    private String redirectEncoded;

    @Parameterized.Parameters
    public static Collection<String[]> data() {
        return Arrays.asList(new String[][]{
                {"<xml/>", "PHhtbC8+", "s6nIzdG3AwA%3D"},
                {"<xml></xml>", "PHhtbD48L3htbD4=", "s6nIzbGz0QeRAA%3D%3D"},
                {"   <xml>   </xml>    ", "ICAgPHhtbD4gICA8L3htbD4gICAg", "U1BQsKnIzbFTANL6UIYCAA%3D%3D"},
                {"<xml attr=\"\"/>", "PHhtbCBhdHRyPSIiLz4=", "s6nIzVFILCkpslVS0rcDAA%3D%3D"},
                {"<root><bla/><bla2>text</bla2></root>", "PHJvb3Q+PGJsYS8+PGJsYTI+dGV4dDwvYmxhMj48L3Jvb3Q+", "synKzy%2Bxs0nKSdQHk0Z2JakVJTb6YKaNPlgWAA%3D%3D"},
                {"<?xml version=\"1.0\" encoding=\"UTF-8\"?><samlp:AuthnRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" ID=\"agdobjcfikneommfjamdclenjcpcjmgdgbmpgjmo\" Version=\"2.0\" IssueInstant=\"2007-04-26T13:51:56Z\" ProtocolBinding=\"urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST\" ProviderName=\"google.com\" AssertionConsumerServiceURL=\"https://www.google.com/a/solweb.no/acs\" IsPassive=\"true\"><saml:Issuer xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">google.com</saml:Issuer><samlp:NameIDPolicy AllowCreate=\"true\" Format=\"urn:oasis:names:tc:SAML:2.0:nameid-format:unspecified\" /></samlp:AuthnRequest>", "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48c2FtbHA6QXV0aG5SZXF1ZXN0IHhtbG5zOnNhbWxwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiIElEPSJhZ2RvYmpjZmlrbmVvbW1mamFtZGNsZW5qY3Bjam1nZGdibXBnam1vIiBWZXJzaW9uPSIyLjAiIElzc3VlSW5zdGFudD0iMjAwNy0wNC0yNlQxMzo1MTo1NloiIFByb3RvY29sQmluZGluZz0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmJpbmRpbmdzOkhUVFAtUE9TVCIgUHJvdmlkZXJOYW1lPSJnb29nbGUuY29tIiBBc3NlcnRpb25Db25zdW1lclNlcnZpY2VVUkw9Imh0dHBzOi8vd3d3Lmdvb2dsZS5jb20vYS9zb2x3ZWIubm8vYWNzIiBJc1Bhc3NpdmU9InRydWUiPjxzYW1sOklzc3VlciB4bWxuczpzYW1sPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj5nb29nbGUuY29tPC9zYW1sOklzc3Vlcj48c2FtbHA6TmFtZUlEUG9saWN5IEFsbG93Q3JlYXRlPSJ0cnVlIiBGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpuYW1laWQtZm9ybWF0OnVuc3BlY2lmaWVkIiAvPjwvc2FtbHA6QXV0aG5SZXF1ZXN0Pg==", "fZJbU8IwEIX%2FSibvvYCiTobioAwjM146Un3wLaRLTU2yNZtS%2FfcWkEFfeN3snvOd3Yyvv6xhG%2FCk0WV8EKecgVNYaldl%2FKWYR1f8ejImaU0jpm14d8%2Fw2QIF1s85EruHjLfeCZSkSThpgURQYjl9uBfDOBWNx4AKDWeLWcZlVeKqVmv94QCtXdfSlsqAq1WjaluV1co2VW2Rs9cD1HALtSBqYeEoSBf6UppeRul5NLwoBmdiNBCjizfO8l%2BnG%2B32%2FKewVvsmEndFkUf507LYCWx0Cf6x7854hVgZiBVazqZE4EOPc4uOWgt%2BCX6jFbw832f8PYSGRJJ0XRcfhxKZEJoOVrHDRCraZsglkd702sG3wPd7Fbto%2Fs9CT4PLAwqfHM3GyR%2Bpw722MRazHI1W32xqDHa3HmQ42LM5eivDabdtRZfRetcqWkcNKL3WUHKWTPau%2Fz%2FG5Ac%3D"}
        });
    }

    public SamlEncoderDecoderTest(String decoded, String postEncoded, String redirectEncoded) {
        this.decoded = decoded;
        this.postEncoded = postEncoded;
        this.redirectEncoded = redirectEncoded;
    }

    @Test
    public void testRedirectDecode() throws Exception {
        SamlResponse samlResponse = samlDecoder.decodeSamlRequest( redirectEncoded, SamlResponse.BindingFormat.REDIRECT );
        assertEquals( SamlResponse.CodingType.DECODED, samlResponse.getCodingType() );
        assertEquals( SamlResponse.BindingFormat.REDIRECT, samlResponse.getBindingFormat() );
        assertEquals( decoded, samlResponse.getResult() );
    }

    @Test
    public void testPostDecode() throws Exception {
        SamlResponse samlResponse = samlDecoder.decodeSamlRequest( postEncoded, SamlResponse.BindingFormat.POST );
        assertEquals( SamlResponse.CodingType.DECODED, samlResponse.getCodingType() );
        assertEquals( SamlResponse.BindingFormat.POST, samlResponse.getBindingFormat() );
        assertEquals( decoded, samlResponse.getResult() );
    }

    @Test
    public void testPostEncodingSamlRequest() throws Exception {
        SamlResponse samlResponse = samlEncoder.encodeSamlRequest(decoded, SamlResponse.BindingFormat.POST);
        assertEquals(SamlResponse.CodingType.ENCODED, samlResponse.getCodingType());
        assertEquals(SamlResponse.BindingFormat.POST, samlResponse.getBindingFormat());
        assertEquals( postEncoded, samlResponse.getResult() );
    }

    @Test
    public void testRedirectEncodingSamlRequest() throws Exception {
        SamlResponse samlResponse = samlEncoder.encodeSamlRequest(decoded, SamlResponse.BindingFormat.REDIRECT);
        assertEquals(SamlResponse.CodingType.ENCODED, samlResponse.getCodingType());
        assertEquals(SamlResponse.BindingFormat.REDIRECT, samlResponse.getBindingFormat());
        assertEquals( redirectEncoded, samlResponse.getResult() );
    }
}
