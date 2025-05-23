package com.pj4;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import org.json.JSONObject;

/**
 * WebView에서 JavaScript로부터 메시지를 받아서 처리하는 클래스
 * JavaScript와 Android 네이티브 코드 간의 상호작용을 관리
 */
public class WebAppInterface {
    private final Context context;
    private final WebViewManager webViewManager;

    /**
     * 생성자
     * @param context      현재 컨텍스트 (액티비티)
     * @param manager      WebView 관리 객체
     */
    public WebAppInterface(Context context, WebViewManager manager) {
        this.context = context;
        this.webViewManager = manager;
    }

    /**
     * JavaScript에서 호출될 수 있는 메서드
     * 웹 페이지에서 특정 메시지를 수신하고 이를 처리하여 UI 상태를 업데이트함
     *
     * @param message  JSON 형식의 메시지
     */
    @JavascriptInterface
    public String receiveMessage(String message) {
        try {
            // 메시지를 JSON 객체로 파싱
            JSONObject obj = new JSONObject(message);

            String type = obj.optString("type");   // 메시지 유형
            Log.d("WebAppInterface", "type: " + type);

            // 메시지 타입이 "TEST"일 경우 처리
            if ("TEST".equals(type)) {

                String path = obj.optString("path");           // 현재 경로

                Log.d("WebAppInterface", "path: " + path);

                return "TEST";


            }
        } catch (Exception e) {
            // 예외 발생 시 로그 출력 및 사용자에게 메시지 표시
            e.printStackTrace();
            Log.e("WebAppInterface", "e message: " + e.getMessage());
            //Toast.makeText(context.getApplicationContext(), "예외 발생: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return "";
    }

}
