package com.wemakestuff.teracast.module;

import com.wemakestuff.teracast.service.MediaService;

import dagger.Module;

/**
 * Dagger module for setting up provides statements. Register all of your entry points below.
 */
@Module(complete = false, injects = {MediaService.class}, library = true)
public class ServiceModule {
}