package ru.sugarisboy.home.core.station.api;

import ru.sugarisboy.home.core.common.api.utils.PropertiesUtils;
import ru.sugarisboy.home.core.station.api.dto.in.StationCommand;

import com.nimbusds.jose.util.X509CertUtils;
import lombok.val;
import java.net.URI;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

class StationWebSocketClient {

    private static final String PROPERTY_ADDRESS = "station.address";
    private static final String PROPERTY_CERT = "station.secure.cert";
    private static final String PROPERTY_TOKEN = "station.secure.token";

    public StationWsConnection createConnection() throws Exception {
        Properties properties = PropertiesUtils.read("prop.properties");
        Properties secureProps = PropertiesUtils.read("secure.properties");

        /* Get authorization token */
        val token = secureProps.getProperty(PROPERTY_TOKEN);

        /* Init connection */
        val address = properties.getProperty(PROPERTY_ADDRESS);
        val controller = new StationWsConnection(new URI(address), token);

        /* Configure connection */
        val cert = secureProps.getProperty(PROPERTY_CERT);
        controller.setSocketFactory(configureSslSocketFactory(cert));
        controller.connectBlocking();

        StationCommand.build(controller)
                .command()
                .ping()
                .send();

        return controller;
    }

    private SSLSocketFactory configureSslSocketFactory(String cert) throws Exception {
        X509Certificate caCert = X509CertUtils.parse(cert);

        TrustManagerFactory tmf = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());

        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null);
        ks.setCertificateEntry("caCert", caCert);

        tmf.init(ks);

        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
        sslContext.init(null, tmf.getTrustManagers(), null);
        SSLSocketFactory factory = sslContext.getSocketFactory();


        sslContext.init(null, new TrustManager[]{new X509WithoutTrustManager()}, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(factory);

        return factory;
    }
}
