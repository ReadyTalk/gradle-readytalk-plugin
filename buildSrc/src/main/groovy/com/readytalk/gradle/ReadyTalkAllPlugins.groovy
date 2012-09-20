package com.readytalk.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class ReadyTalkAllPlugins implements Plugin<Project> {
  Project project

  void apply(Project project) {
    this.project = project

    project.apply {
      plugin 'properties'
    }

    if(project.has('artifactory_username') &&
       project.has('artifactory_password') &&
       project.has('artifactory_root') &&
       project.has('artifactory_main') &&
       project.has('local_root')) {

      def repos = new ReadyTalkReposPlugin()
      repos.apply(project)

      def release = new ReadyTalkReleasePlugin()
      release.apply(project)

    } else {
      project.logger.warn "Artifactory credentials not specified. Continuing without ReadyTalk repo/release support..."
    }
  }

}