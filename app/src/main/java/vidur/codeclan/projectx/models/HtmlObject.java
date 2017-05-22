package vidur.codeclan.projectx.models;

import android.text.TextPaint;


public class HtmlObject {

    public HtmlObject(String content, int start, int end, float xOffset,
                      TextPaint paint) {
        super();
        this.content = content;
        this.start = start;
        this.end = end;
        this.xOffset = xOffset;
        this.paint = paint;
    }
    public String content;
    public int start;
    public int end;
    public float xOffset;
    public TextPaint paint;
    public boolean recycle = false;
}
