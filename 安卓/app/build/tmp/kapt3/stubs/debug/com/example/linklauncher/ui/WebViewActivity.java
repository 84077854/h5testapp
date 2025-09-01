package com.example.linklauncher.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u0018\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000bH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/linklauncher/ui/WebViewActivity;", "Landroidx/activity/ComponentActivity;", "()V", "binding", "Lcom/example/linklauncher/databinding/ActivityWebviewBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "saveHistory", "url", "", "ua", "setupWebView", "webView", "Landroid/webkit/WebView;", "Companion", "app_debug"})
public final class WebViewActivity extends androidx.activity.ComponentActivity {
    private com.example.linklauncher.databinding.ActivityWebviewBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_URL = "extra_url";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_UA = "extra_ua";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.linklauncher.ui.WebViewActivity.Companion Companion = null;
    
    public WebViewActivity() {
        super(0);
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    private final void setupWebView(android.webkit.WebView webView, java.lang.String ua) {
    }
    
    private final void saveHistory(java.lang.String url, java.lang.String ua) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/linklauncher/ui/WebViewActivity$Companion;", "", "()V", "EXTRA_UA", "", "EXTRA_URL", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}