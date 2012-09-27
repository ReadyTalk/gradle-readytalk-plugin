package com.readytalk.gradle

import com.readytalk.gradle.util.PluginApplicator

class ReadyTalkAllPlugins extends PluginApplicator {
  Project project

  def internalFactory = [
                          repos: ReadyTalkReposPlugin,
                          publishers: ArtifactoryPublisherPlugin
                        ]

  def publicFactory =   [
                          repos: OpenSourceReposPlugin,
                          publishers: OpenSourcePublisherPlugin
                        ]

  void apply(Project project) {
    super.apply(project)

    //extensions.create("readytalk", ReadyTalkClosure, project)

    initPropertiesPlugin()
    initPlugins()
  }

  void initPropertiesPlugin() {
    if(project.has('load_properties') &&
       project.'load_properties') {
      
      project.apply {
        plugin 'properties'
      }
    }
  }

  void initPlugins() {
    if(project.has('readytalk_repo') &&
       project.'readytalk_repo') {

      PluginFactory.factory = internalFactory

    } else {

      PluginFactory.factory = publicFactory

    }
  }

}