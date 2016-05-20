
package com.thevoxelbox.hdskins.asm;

import com.mumfrey.liteloader.transformers.event.Event;
import com.mumfrey.liteloader.transformers.event.EventInjectionTransformer;
import com.mumfrey.liteloader.transformers.event.MethodInfo;
import com.mumfrey.liteloader.transformers.event.inject.BeforeInvoke;

public class HDSkinsEventTransformer
        extends EventInjectionTransformer {
    protected void addEvents() {
        String eName = "onDownloadSkin";
        this.addEvent(Event.getOrCreate(eName, true), new MethodInfo(Obf.tdid, Obf.dlskin, Void.TYPE), new BeforeInvoke("start")).addListener(new MethodInfo(Obf.mgr, eName));
    }
}

