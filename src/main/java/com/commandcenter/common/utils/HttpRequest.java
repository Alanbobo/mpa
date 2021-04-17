package com.commandcenter.common.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author r25437
 * @create 2018-08-03 9:18
 * @desc HTTP请求工具类
 **/
public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param parameters
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, String> parameters) {
        //返回的结果
        String result = "";
        // 读取响应输入流
        BufferedReader in = null;
        // 编码之后的参数
        String params = "";
        try {
            params = dealParam(parameters);
            String full_url = url + "?" + params;
            System.out.println(full_url);
            // 创建URL对象
            URL connURL = new URL(full_url);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
            // 设置通用属性
            httpConn.setConnectTimeout(60000);
            httpConn.setReadTimeout(60000);
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           finallyClose(in,null);
        }
        return result;
    }

    /**
     * 发送POST请求 3. * 4. * @param url 5. * 目的地址 6. * @param parameters 7. *
     * 请求参数，Map类型。 8. * @return 远程响应结果 9.
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        // 返回的结果
        String result = "";
        // 读取响应输入流
        BufferedReader in = null;
        PrintWriter out = null;
        // 编码之后的参数
        String params = "";
        try {
            params = dealParam(parameters);
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
            httpConn.setRequestMethod("POST");
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            finallyClose(in,out);
        }
        return result;
    }

    /**
     * 发送POST请求
     * @param url
     * @param jsonStr
     * @return
     */
    public static String sendPost(String url, String jsonStr) {
        // 返回的结果
        String result = "";
        // 读取响应输入流
        BufferedReader in = null;
        PrintWriter out = null;
        // 编码之后的参数
        String params = jsonStr;
        try {
//            params = java.net.URLEncoder.encode(jsonStr, "UTF-8");
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
            // 设置POST方式
            httpConn.setRequestMethod("POST");
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置文件类型:
            httpConn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            finallyClose(in,out);
        }
        return result;
    }

    /**
     * 发送日志POST请求到日志平台
     * @param url
     * @param jsonStr
     * @return
     */
    public static String sendPostToLogSys(String url, String jsonStr, String key) {
        // 返回的结果
        String result = "";
        // 读取响应输入流
        BufferedReader in = null;
        PrintWriter out = null;
        // 编码之后的参数
        String params = jsonStr;
        try {
//            params = java.net.URLEncoder.encode(jsonStr, "UTF-8");
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
            // 设置POST方式
            httpConn.setRequestMethod("POST");
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "application/json");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件类型:
            httpConn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            httpConn.setConnectTimeout(10000);
            httpConn.setReadTimeout(10000);
            httpConn.setRequestProperty("Authorization",key);
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            finallyClose(in,out);
        }
        return result;
    }

    private static void finallyClose(BufferedReader in,PrintWriter out){
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String dealParam(Map<String, String> parameters){
        // 存储参数
        StringBuffer sb = new StringBuffer();

        // 编码之后的参数
        String params = "";
        try {
            if (parameters != null) {
                if (parameters.size() == 1) {
                    for (String name : parameters.keySet()) {
                        sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
                    }
                    params = sb.toString();
                } else {
                    for (String name : parameters.keySet()) {
                        sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"))
                                .append("&");
                    }
                    String temp_params = sb.toString();
                    params = temp_params.substring(0, temp_params.length() - 1);
                }
            }
            return params;
        }catch (Exception e) {
            throw e;
        }finally {
            return params;
        }
    }

    public static void sss() {
        HttpURLConnection conn = null;
        String BOUNDARY = "---------------------------123821742118716";
        // boundary就是request头和上传文件内容的分隔符
        try {
            File f = new File("c:\\psu.jpg");
            URL url = new URL("http://localhost:8080/fcls/media/save?token=1");
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(50000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
            conn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            byte[] fb = new byte[(int) f.length()];
            FileInputStream fis = new FileInputStream(f);
            fis.read(fb);
            StringBuffer strBuf = new StringBuffer();
            String inputName = "uploadfile";
            String filename = "1.jpg";
            String contentType = "image/jpeg";
            strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
            strBuf.append(
                    "Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");
            strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
            out.write(strBuf.toString().getBytes());
            out.flush();
            out.write(fb);
            out.flush();
            out.write(("\r\n--"+BOUNDARY+"--\r\n").getBytes());

            out.flush();
            out.close();
            fis.close();
            strBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            System.out.println(strBuf.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] str){
        HttpRequest.sss();
    }
}
