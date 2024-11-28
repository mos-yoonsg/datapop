package com.example.datapoppluginsintellij;

import com.intellij.ui.jcef.JBCefBrowser;

import javax.swing.*;

public class MyToolWindow {

    private final JPanel content;

    public MyToolWindow() {
        // JBCefBrowser 생성
        JBCefBrowser browser = new JBCefBrowser();

        // CefRequestHandler 설정
        browser.getJBCefClient().addRequestHandler(new MyRequestHandler(), browser.getCefBrowser());

        // 브라우저에서 로컬 서버 주소로 로드
        browser.loadURL("http://my-plugin.local/index.html");

        // 패널에 WebView 추가
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(browser.getComponent());
    }

    public JComponent getContent() {
        return content;
    }

}
