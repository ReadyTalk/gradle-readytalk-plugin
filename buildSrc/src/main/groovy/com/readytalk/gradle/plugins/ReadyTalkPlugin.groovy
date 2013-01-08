package com.readytalk.gradle.plugins

import org.gradle.api.Project
import org.gradle.api.Plugin

import com.readytalk.gradle.extensions.ReadyTalkExtension

class ReadyTalkPlugin implements Plugin<Project> {

  ReadyTalkExtension readyTalkExtension

  void apply(Project project) {
    ReadyTalkExtension readyTalkExtension = project.extensions.create('readytalk', ReadyTalkExtension, project)

    project.afterEvaluate {
      if(readyTalkExtension.type == 'internal') {
        project.apply(plugin: 'readytalk-internal-artifactory')
      } else if(readyTalkExtension.type == 'external') {
        project.apply(plugin: 'readytalk-external-sonatype')
      }
    }

  }

}