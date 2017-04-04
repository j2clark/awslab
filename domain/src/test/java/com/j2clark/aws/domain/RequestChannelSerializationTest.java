package com.j2clark.aws.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class RequestChannelSerializationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSmsChannel() throws IOException {

        String phoneNumber = "16507876009";
        RequestChannel.SMSChannel sms = new RequestChannel.SMSChannel(phoneNumber);

        String json = objectMapper.writeValueAsString(sms);
        System.out.println(json);

        RequestChannel.SMSChannel o1 = objectMapper.readValue(json, RequestChannel.SMSChannel.class);
        RequestChannel o2 = objectMapper.readValue(json, RequestChannel.class);
        Assert.assertTrue(o2 instanceof RequestChannel.SMSChannel);
        Assert.assertEquals(phoneNumber, ((RequestChannel.SMSChannel) o2).getPhoneNumber());
    }

    @Test
    public void testEmailChannel() throws IOException {

        String email = "jclark@suhtterfly.com";
        RequestChannel.EmailChannel sms = new RequestChannel.EmailChannel(email);

        String json = objectMapper.writeValueAsString(sms);
        System.out.println(json);

        RequestChannel.EmailChannel o1 = objectMapper.readValue(json, RequestChannel.EmailChannel.class);
        RequestChannel o2 = objectMapper.readValue(json, RequestChannel.class);
        Assert.assertTrue(o2 instanceof RequestChannel.EmailChannel);
        Assert.assertEquals(email, ((RequestChannel.EmailChannel) o2).getEmail());
    }

    @Test
    public void testWelcomeRequest() throws IOException {

        String phoneNumber = "16507876009";
        String email = "xyz@shutterfly.com";
        Request.Welcome welcome = new WelcomeRequestBuilder()
            .withTransactionId(UUID.randomUUID())
            .withRequestDateTime(DateTime.now())
            .withUid(UUID.randomUUID().toString())
            .withChannel(new RequestChannel.SMSChannel(phoneNumber))
            .withChannel(new RequestChannel.EmailChannel(email))
            .build();



        String json = objectMapper.writeValueAsString(welcome);
        System.out.println(json);

        /*String json = "{\n"
                      + "    \"transactionId\": \"d383e288-1186-4696-9132-0ec476d477a5\",\n"
                      + "    \"clientId\": {\n"
                      + "        \"present\": false\n"
                      + "    },\n"
                      + "    \"uid\": \"cf748b16-5bf5-442a-b3fc-8354957bcf10\",\n"
                      + "    \"firstName\": null,\n"
                      + "    \"lastName\": null,\n"
                      + "    \"requestTimestamp\": 1491339187116\n"
                      + "}";
*/
        Request.Welcome
            dw = objectMapper.readValue(json, WelcomeRequestBuilder.WelcomeImpl.class);

        Set<RequestChannel> channels = dw.getChannels();
        Assert.assertNotNull(channels);
        Assert.assertEquals(2, channels.size());


        Request r = objectMapper.readValue(json, Request.class);

        /*Asset a = new Asset();
        a.setProperties(new DocumentAssetProperties());

        String json = objectMapper.writeValueAsString(a);
        System.out.println(json);*/
    }


    class Asset {

        @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "type")
        @JsonSubTypes({
            @JsonSubTypes.Type(value = ImageAssetProperties.class, name = "image"),
            @JsonSubTypes.Type(value = DocumentAssetProperties.class, name = "document") })
        private AssetProperties properties;

        public AssetProperties getProperties() {
            return properties;
        }

        public void setProperties(AssetProperties properties) {
            this.properties = properties;
        }

        @Override
        public String toString() {
            return "Asset [properties("+properties.getClass().getSimpleName()+")=" + properties + "]";
        }
    }

    public interface AssetProperties {
        String getSource();
        AssetProperties setSource(String source);
        String getProxy();
        AssetProperties setProxy(String proxy);
    }

    public class DocumentAssetProperties implements AssetProperties {
        private String source;
        private String proxy;

        public String getSource() {
            return source;
        }

        public DocumentAssetProperties setSource(String source) {
            this.source = source;
            return this;
        }

        public String getProxy() {
            return proxy;
        }

        public DocumentAssetProperties setProxy(String proxy) {
            this.proxy = proxy;
            return this;
        }
    }

    public class ImageAssetProperties implements AssetProperties {
        private String source;
        private String proxy;
        private Integer height;
        private Integer width;

        public String getSource() {
            return source;
        }

        public ImageAssetProperties setSource(String source) {
            this.source = source;
            return this;
        }

        public String getProxy() {
            return proxy;
        }

        public ImageAssetProperties setProxy(String proxy) {
            this.proxy = proxy;
            return this;
        }

        public Integer getHeight() {
            return height;
        }

        public ImageAssetProperties setHeight(Integer height) {
            this.height = height;
            return this;
        }

        public Integer getWidth() {
            return width;
        }

        public ImageAssetProperties setWidth(Integer width) {
            this.width = width;
            return this;
        }
    }
}
