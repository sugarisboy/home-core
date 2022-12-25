package ru.sugarisboy.home.core.station.api;

import ru.sugarisboy.home.core.common.api.utils.PropertiesUtils;
import ru.sugarisboy.home.core.station.api.dto.in.StationCommand;
import ru.sugarisboy.home.core.station.api.dto.in.StationSocketInfo;

import com.nimbusds.jose.util.X509CertUtils;
import lombok.RequiredArgsConstructor;
import lombok.val;
import java.net.URI;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Properties;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

@RequiredArgsConstructor
class StationWebSocketClient {

    private final StationSocketInfo stationSocketInfo;

    public StationWsConnection createConnection() throws Exception {
        /* Get authorization token */
        val token = stationSocketInfo.getToken();

        /* Init connection */
        val address = stationSocketInfo.getWsAddress();
        val controller = new StationWsConnection(new URI(address), token);

        /* Configure connection */
        val cert = stationSocketInfo.getCaCert();
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

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{new X509WithoutTrustManager()}, new java.security.SecureRandom());

        SSLSocketFactory factory = sslContext.getSocketFactory();


        HttpsURLConnection.setDefaultSSLSocketFactory(factory);

        return factory;
    }
}
