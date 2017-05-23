package vidur.codeclan.projectx.models;

import android.text.TextPaint;


public class HtmlLink extends HtmlObject {
    public HtmlLink(String content, int start, int end, float xOffset,
                    TextPaint paint, String url) {
        super(content, start, end, xOffset, paint);
        this.url = url;
    }
    public float width;
    public float height;
    public float yOffset;
    public String url;
}