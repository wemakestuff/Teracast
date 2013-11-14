package com.wemakestuff.podstuff.module;

import com.wemakestuff.podstuff.service.MediaService;

import dagger.Module;

/**
 * Dagger module for setting up provides statements. Register all of your entry points below.
 */
@Module(complete = false, injects = {MediaService.class}, library = true)
public class ServiceModule {
}