package com.readytalk.gradle

import org.gradle.api.Project
import org.gradle.api.Plugin
import com.readytalk.gradle.publishers.ReadyTalkPublisherPlugin
import com.readytalk.gradle.publishers.OpenSourcePublisherPlugin
import com.readytalk.gradle.publishers.LocalPublisherPlugin

class AllPlugins implements Plugin<Project> {
  Project project

  void apply(Project project) {
    this.project = project

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
    project.apply {
      plugin LocalPublisherPlugin
    }

    if(project.has('readytalk_repo') &&
      project.'readytalk_repo') {

      project.apply {
        plugin ReadyTalkPublisherPlugin
      }



    } else {
      project.apply {
        plugin OpenSourcePublisherPlugin
      }
    }



  }

}