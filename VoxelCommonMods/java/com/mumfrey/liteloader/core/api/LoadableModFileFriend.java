package com.mumfrey.liteloader.core.api;

import java.io.File;

import com.mumfrey.liteloader.interfaces.LoadableMod;

public class LoadableModFileFriend
{
	public static LoadableMod<?> getLoadableModFile(File jarFile)
	{
		return new LoadableModFile(jarFile, null);
	}
}
