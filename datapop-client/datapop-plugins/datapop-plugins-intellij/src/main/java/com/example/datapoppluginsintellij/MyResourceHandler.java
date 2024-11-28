package com.example.datapoppluginsintellij;

import org.cef.callback.CefCallback;
import org.cef.handler.CefResourceHandlerAdapter;
import org.cef.misc.IntRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;

import java.io.InputStream;
import java.net.URL;

public class MyResourceHandler extends CefResourceHandlerAdapter {

    private final String resourcePath;

    private InputStream inputStream;

    public MyResourceHandler(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public boolean processRequest(CefRequest request, CefCallback callback) {
        try {
            // 리소스를 클래스패스에서 읽어 InputStream으로 변환
            URL resourceUrl = getClass().getResource(resourcePath);
            if (resourceUrl != null) {
                inputStream = resourceUrl.openStream();
            }
            // 리소스가 성공적으로 열렸으면 응답을 처리하도록 설정하고, 콜백 실행
            if (inputStream != null) {
                callback.Continue();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 실패 시 false 반환
        return false;
    }

    @Override
    public void getResponseHeaders(CefResponse response, IntRef responseLength, StringRef redirectUrl) {
        // 파일 타입에 따라 MIME 타입 설정
        if (resourcePath.endsWith(".html")) {
            response.setMimeType("text/html");
        } else if (resourcePath.endsWith(".css")) {
            response.setMimeType("text/css");
        } else if (resourcePath.endsWith(".js")) {
            response.setMimeType("application/javascript");
        } else if (resourcePath.endsWith(".svg")) {
            response.setMimeType("image/svg+xml");
        } else {
            response.setMimeType("application/octet-stream");
        }
        response.setStatus(200);
        responseLength.set(-1); // 스트리밍 응답
    }

    @Override
    public boolean readResponse(byte[] dataOut, int bytesToRead, IntRef bytesRead, CefCallback callback) {
        try {
            if (inputStream != null) {
                int bytesActuallyRead = inputStream.read(dataOut, 0, bytesToRead);
                if (bytesActuallyRead > 0) {
                    bytesRead.set(bytesActuallyRead);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        bytesRead.set(0);
        return false;
    }

    @Override
    public void cancel() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
