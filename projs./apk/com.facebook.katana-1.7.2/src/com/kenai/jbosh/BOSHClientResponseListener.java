// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BOSHClientResponseListener.java

package com.kenai.jbosh;


// Referenced classes of package com.kenai.jbosh:
//            BOSHMessageEvent

public interface BOSHClientResponseListener
{

    public abstract void responseReceived(BOSHMessageEvent boshmessageevent);
}
