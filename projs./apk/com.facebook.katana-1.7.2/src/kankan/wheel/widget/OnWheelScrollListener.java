// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OnWheelScrollListener.java

package kankan.wheel.widget;


// Referenced classes of package kankan.wheel.widget:
//            WheelView

public interface OnWheelScrollListener
{

    public abstract void onScrollingFinished(WheelView wheelview);

    public abstract void onScrollingStarted(WheelView wheelview);
}
