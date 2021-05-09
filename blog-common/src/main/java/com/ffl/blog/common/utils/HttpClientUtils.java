package com.ffl.blog.common.utils;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;

/**
 * commons-httpclient(停更)与httpclient(继续升级中)
 * <p>
 * HTTP连接池请求,支持http和https请求,
 * <p>
 * 基于org.apache.httpcomponents.httpcore<version>4.4.10</version>
 * </p>
 * <p>
 * 基于org.apache.httpcomponents.httpclient<version>4.5.6</version>
 * <p>
 * https://www.cnblogs.com/zhc-hnust/p/12006923.html
 */
public class HttpClientUtils {

    private static final String ENCODING = "UTF-8";
    private static final int DEFAULT_CONNECT_TIMEOUT = 6000;
    private static final int DEFAULT_READ_TIMEOUT = 6000;
    private static final int DEFAULT_CONNECT_REQUEST_TIMEOUT = 6000;
    private static final int MAX_TOTAL = 64;
    private static final int MAX_PER_ROUTE = 32;
    private static final RequestConfig requestConfig;
    private static final PoolingHttpClientConnectionManager connectionManager;
    private static final HttpClientBuilder httpBuilder;
    private static final CloseableHttpClient httpClient;
    private static final CloseableHttpClient httpsClient;
    private static SSLContext sslContext;

    static {
        try {
            sslContext = SSLContext.getInstance("TLS");

            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslContext.init(null, new TrustManager[]{tm}, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(DEFAULT_READ_TIMEOUT)
                .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(DEFAULT_CONNECT_REQUEST_TIMEOUT).build();
        @SuppressWarnings("deprecation")
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER))
                .build();
        connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(MAX_TOTAL);
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        httpBuilder = HttpClientBuilder.create();
        httpBuilder.setDefaultRequestConfig(requestConfig);
        httpBuilder.setConnectionManager(connectionManager);
        httpClient = httpBuilder.build();
        httpsClient = httpBuilder.build();
    }

    /**
     * GET
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static Result doGet(String url) throws Exception {
        return doGet(url, false);
    }

    /**
     * GET
     *
     * @param url
     * @param https
     * @return
     * @throws Exception
     */
    public static Result doGet(String url, boolean https) throws Exception {
        return doGet(url, null, null, https);
    }

    /**
     * GET
     *
     * @param url
     * @param params
     * @param https
     * @return
     * @throws Exception
     */
    public static Result doGet(String url, Map<String, String> params, boolean https) throws Exception {
        return doGet(url, null, params, https);
    }

    /**
     * GET
     *
     * @param url
     * @param headers
     * @param params
     * @param https
     * @return
     * @throws Exception
     */
    public static Result doGet(String url, Map<String, String> headers, Map<String, String> params, boolean https) throws URISyntaxException, IOException {
        // 创建访问的地址
        URIBuilder uriBuilder = new URIBuilder(url);
        if (params != null) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue());
            }
        }
        // 创建HTTP对象
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setConfig(requestConfig);
        // 设置请求头
        setHeader(headers, httpGet);
        return executeRequest(httpGet, https);
    }

    /**
     * POST不带参数
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static Result doPost(String url) throws Exception {
        return doPost(url, Boolean.FALSE);
    }

    /**
     * @param url
     * @param https
     * @return
     * @throws Exception
     */
    public static Result doPost(String url, boolean https) throws Exception {
        return doPost(url, null, (Map<String, String>) null, https);
    }

    /**
     * 带请求参数
     *
     * @param url
     * @param params
     * @param https
     * @return
     * @throws Exception
     */
    public static Result doPost(String url, Map<String, String> params, boolean https) throws Exception {
        return doPost(url, null, params, https);
    }

    /**
     * POST
     *
     * @param url
     * @param headers
     * @param params
     * @param https
     * @return
     * @throws Exception
     */
    public static Result doPost(String url, Map<String, String> headers, Map<String, String> params, boolean https) throws Exception {
        // 创建HTTP对象
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        // 设置请求头
        setHeader(headers, httpPost);
        // 封装请求参数
        setParam(params, httpPost);
        return executeRequest(httpPost, https);
    }

    /**
     * POST请求JSON
     *
     * @param url
     * @param headers
     * @param json
     * @param https
     * @return
     * @throws Exception
     */
    public static Result doPost(String url, Map<String, String> headers, String json, boolean https) throws Exception {
        // 创建HTTP对象
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        // 设置请求头
        setHeader(headers, httpPost);
        StringEntity stringEntity = new StringEntity(json, ENCODING);
        stringEntity.setContentEncoding(ENCODING);
        httpPost.setEntity(stringEntity);
        return executeRequest(httpPost, https);
    }


    /**
     * 发送put请求；不带请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public static Result doPut(String url) throws Exception {
        return doPut(url, null);
    }

    /**
     * 发送put请求；带请求参数
     *
     * @param url    请求地址
     * @param params 参数集合
     * @return
     * @throws Exception
     */
    public static Result doPut(String url, Map<String, String> params)
            throws Exception {
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        // RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpPut.setConfig(requestConfig);
        setParam(params, httpPut);
        return executeRequest(httpPut, false);
    }

    /**
     * 不带请求参数
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static Result doDelete(String url) throws Exception {
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        // RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpDelete.setConfig(requestConfig);
        return executeRequest(httpDelete, false);
    }

    /**
     * 带请求参数
     *
     * @param url
     * @param params
     * @param https
     * @return
     * @throws Exception
     */
    public static Result doDelete(String url, Map<String, String> params, boolean https) throws Exception {
        if (params == null) {
            params = new HashMap<String, String>();
        }
        params.put("_method", "delete");
        return doPost(url, params, https);
    }

    /**
     * 设置封装请求头
     *
     * @param params
     * @param httpMethod
     */
    private static void setHeader(Map<String, String> params, HttpRequestBase httpMethod) {
        // 封装请求头
        if (params != null && !params.isEmpty()) {
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                // 设置到请求头到HttpRequestBase对象中
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 封装请求参数
     *
     * @param params
     * @param httpMethod
     * @throws UnsupportedEncodingException
     */
    private static void setParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod) throws UnsupportedEncodingException {
        // 封装请求参数
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> nvps = new ArrayList<>();
            Set<Entry<String, String>> entrySet = params.entrySet();
            for (Entry<String, String> entry : entrySet) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            // 设置到请求的http对象中
            httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
        }
    }

    private static Result executeRequest(HttpRequestBase request, boolean https) throws IOException {
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = https ? httpsClient.execute(request) : httpClient.execute(request);
            if (httpResponse != null && httpResponse.getStatusLine() != null) {
                String message = null;
                if (httpResponse.getEntity() != null) {
                    message = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
                }
                return Result.of(httpResponse.getStatusLine().getStatusCode(), message);
            } else {
                return Result.error(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            }
        } finally {
            // 释放资源
            request.releaseConnection();
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
    }
}