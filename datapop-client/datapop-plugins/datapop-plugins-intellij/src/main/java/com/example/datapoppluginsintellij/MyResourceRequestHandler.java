package com.example.datapoppluginsintellij;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefResourceHandler;
import org.cef.handler.CefResourceRequestHandlerAdapter;
import org.cef.network.CefRequest;

public class MyResourceRequestHandler extends CefResourceRequestHandlerAdapter {

    @Override
    public CefResourceHandler getResourceHandler(CefBrowser browser, CefFrame frame, CefRequest request) {
        String url = request.getURL();

        // HTML, CSS, JS 파일 경로 처리
        if (url.startsWith("http://my-plugin.local/")) {
            // URL의 리소스 경로를 가져와서 매핑
            String resourcePath = url.replace("http://my-plugin.local/", "/react-app/");
            return new MyResourceHandler(resourcePath);
        }
        return null;
    }

}
