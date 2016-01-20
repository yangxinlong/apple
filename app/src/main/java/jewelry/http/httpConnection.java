package jewelry.http;

import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.os.Handler;

/**
 * Created by yang on 16-1-20.
 */
//提示这里并不建议使用Thread 来实现线程,建议实现Runnable接口,
// 相对于扩展Thread类来说，具有无可比拟的优势。
// 这种方式不仅有利于程序的健壮性，使代码能够被多个线程共享，
// 而且代码和数据资源相对独立，从而特别适合多个具有相同代码的线程去处理同一资源的情况。
// 这样一来，线程、代码和数据资源三者有效分离，很好地体现了面向对象程序设计的思想。
// 因此，几乎所有的多线程程序都是通过实现Runnable接口的方式来完成的。
public class httpConnection implements Runnable {
    private String url;
    private WebView webView;
    private Handler handler;

    public httpConnection(String url, Handler handler, WebView webView) {
        this.url = url;
        this.webView = webView;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            try {
                HttpURLConnection httpconn = (HttpURLConnection) httpUrl.openConnection();
//                设置链接属性
                String requestString = "客服端要以以流方式发送到服务端的数据...";
                // 这个地方到底是怎么回事刚刚还是好好的.
                byte[] requestStringBytes = "ENCODING_UTF_8".getBytes(requestString);
                httpconn.setDoOutput(true);//使用URL进行链接输出
                httpconn.setDoInput(true);//使用URL进行链接输入
                httpconn.setReadTimeout(5000);//设置超时时间
                httpconn.setUseCaches(false);//设置不用缓存
                httpconn.setRequestMethod("POST");//设置传输方式
                httpconn.setRequestProperty("Content-Type", "application/octet-stream");
                httpconn.setRequestProperty("Connection", "Keep-Alive");
                httpconn.setRequestProperty("Charset", "UTF-8");
                String name = URLEncoder.encode("杨欣龙", "utf-8");
                httpconn.setRequestProperty("NAME", name);
                OutputStream outputStream = httpconn.getOutputStream();//建立输出流
                outputStream.write(requestStringBytes);//写入输出流
                outputStream.close();//关闭
//                获得响应状态
                int responseCode = httpconn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
//                  当正确响应时处理数据
                    StringBuffer sb = new StringBuffer();
                    String readline;
                    BufferedReader responseReader;
                    responseReader = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));
                    while ((readline = responseReader.readLine()) != null) {
                        sb.append(readline).append("\n");
                    }
                    responseReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}