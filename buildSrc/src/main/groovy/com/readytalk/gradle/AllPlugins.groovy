package com.readytalk.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin
import com.readytalk.gradle.repos.ReadyTalkReposPlugin
import com.readytalk.gradle.repos.OpenSourceReposPlugin
import com.readytalk.gradle.publishers.ReadyTalkPublisherPlugin
import com.readytalk.gradle.publishers.OpenSourcePublisherPlugin

class AllPlugins implements Plugin<Project> {
  Project project

  void apply(Project project) {
    this.project = project

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

      project.apply {
        plugin ReadyTalkReposPlugin
        plugin ReadyTalkPublisherPlugin
      }

    } else {
      project.apply {
        plugin OpenSourceReposPlugin
        plugin OpenSourcePublisherPlugin
      }
    }



  }

}